package com.paliy_dmitriy.data.remote.factories

import com.paliy_dmitriy.data.di.qualifiers.MockApi
import com.paliy_dmitriy.data.di.qualifiers.RealApi
import com.paliy_dmitriy.data.remote.api.ExchangeRatesDataApiService
import com.paliy_dmitriy.tracking_exchange_rates.data.BuildConfig

class ExchangeRatesApiFactory constructor(
  @param:RealApi private val realApiService: ExchangeRatesDataApiService,
  @param:MockApi private val mockApiService: ExchangeRatesDataApiService,
) {
  init {
    println("AHTUNG: 🏭 ExchangeRatesApiFactory created")
    println("AHTUNG:   USE_MOCK_API = ${BuildConfig.USE_MOCK_API}")
    println("AHTUNG:   RealApi service class: ${realApiService::class.simpleName}")
    println("AHTUNG:   MockApi service class: ${mockApiService::class.simpleName}")
  }

  fun getApiService(): ExchangeRatesDataApiService {
    println("AHTUNG: ")
    val service = if (BuildConfig.USE_MOCK_API) {
      println("AHTUNG:📱 Using MOCK API service")
      mockApiService
    } else {
      println("AHTUNG:🌐 Using REAL API service")
      realApiService
    }
    println("AHTUNG:   Service class: ${service::class.simpleName}")
    return service
  }
//  fun getApiService(): ExchangeRatesDataApiService =
//    if (BuildConfig.USE_MOCK_API) mockApiService else realApiService
}