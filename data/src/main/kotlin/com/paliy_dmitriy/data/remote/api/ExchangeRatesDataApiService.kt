package com.paliy_dmitriy.data.remote.api

import com.paliy_dmitriy.data.remote.model.response.QuoteResponseDto
import com.paliy_dmitriy.data.remote.model.response.SymbolResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeRatesDataApiService {

  @GET("exchangerates_data/{date}")
  suspend fun getQuotes(
    @Path("date") date: String,
    @Query("base") base: String,
    @Query("symbols") symbols: String? = null
  ): Response<QuoteResponseDto>

  @GET("exchangerates_data/symbols")
  suspend fun getSymbols(): Response<SymbolResponseDto>
}