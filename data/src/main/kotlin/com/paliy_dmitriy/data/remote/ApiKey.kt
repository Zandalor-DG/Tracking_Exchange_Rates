package com.paliy_dmitriy.data.remote.api

import com.paliy_dmitriy.tracking_exchange_rates.data.BuildConfig

object ApiKey {
  fun getApiKey(): String {
    return BuildConfig.API_KEY
  }
}