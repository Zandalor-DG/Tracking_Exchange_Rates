package com.paliy_dmitriy.data.remote

import com.paliy_dmitriy.tracking_exchange_rates.data.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitFactory @Inject constructor(
  private val okHttpClient: OkHttpClient
) {

  private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    prettyPrint = BuildConfig.DEBUG
  }

  private val contentType = "application/json".toMediaType()

  fun create(baseUrl: String = BuildConfig.BASE_URL): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl.removeSurrounding("\""))
      .client(okHttpClient)
      .addConverterFactory(json.asConverterFactory(contentType))
      .build()
  }

  fun <T> createService(serviceClass: Class<T>, baseUrl: String = BuildConfig.BASE_URL): T {
    return create(baseUrl).create(serviceClass)
  }
}