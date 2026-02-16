package com.paliy_dmitriy.data.remote.interceptors

import com.paliy_dmitriy.data.remote.api.ApiKey
import com.paliy_dmitriy.domain.exception.MissingApiKeyException
import com.paliy_dmitriy.domain.exception.NetworkException
import com.paliy_dmitriy.domain.exception.NetworkTimeoutException
import com.paliy_dmitriy.domain.exception.NetworkUnreachableException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()

    val apiKey = getApiKey() ?: throw MissingApiKeyException("API key is not configured")

    val requestBuilder = originalRequest.newBuilder()
      .addHeader("Content-Type", "application/json")
      .addHeader("Accept", "application/json")
      .addHeader("apikey", apiKey)  // apilayer.com использует заголовок "apikey"

    val request = requestBuilder.build()

    return try {
      chain.proceed(request)
    } catch (e: IOException) {
      throw when {
        e.message?.contains("timeout") == true -> NetworkTimeoutException("Request timeout", e)
        e.message?.contains("unreachable") == true -> NetworkUnreachableException(
          "Network unreachable",
          e
        )

        else -> NetworkException("Network error: ${e.message}", e)
      }
    }
  }

  private fun getApiKey(): String? {
    return try {
      return ApiKey.getApiKey()
    } catch (e: Exception) {
      null
    }
  }
}
