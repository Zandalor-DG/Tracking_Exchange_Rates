package com.paliy_dmitriy.data.remote.interceptors

import com.paliy_dmitriy.tracking_exchange_rates.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor constructor() : Interceptor {

  private val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    return httpLoggingInterceptor.intercept(chain)
  }
}