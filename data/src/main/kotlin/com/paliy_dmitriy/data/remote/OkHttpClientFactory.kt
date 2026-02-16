package com.paliy_dmitriy.data.remote

import android.content.Context
import com.paliy_dmitriy.data.remote.interceptors.ApiKeyInterceptor
import com.paliy_dmitriy.data.remote.interceptors.LoggingInterceptor
import com.paliy_dmitriy.tracking_exchange_rates.data.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

class OkHttpClientFactory(
  private val context: Context,
  private val apiKeyInterceptor: ApiKeyInterceptor,
  private val loggingInterceptor: LoggingInterceptor
) {

  companion object {
    private const val CACHE_SIZE = 10 * 1024 * 1024L // 10 MB
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private const val CACHE_MAX_AGE = 5 * 60 // 5 минут в секундах
  }

  fun create(): OkHttpClient {
    val cacheDir = File(context.cacheDir, "http_cache")
    val cache = Cache(cacheDir, CACHE_SIZE)

    val clientBuilder = OkHttpClient.Builder()
      .cache(cache)
      .addInterceptor(apiKeyInterceptor)
      .addInterceptor(loggingInterceptor)
      .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
      .retryOnConnectionFailure(true)

    if (!BuildConfig.DEBUG) {
      clientBuilder.addNetworkInterceptor { chain ->
        val response = chain.proceed(chain.request())
        response.newBuilder()
          .header("Cache-Control", "public, max-age=$CACHE_MAX_AGE")
          .build()
      }
    }

    return clientBuilder.build()
  }
}