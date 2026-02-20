package com.paliy_dmitriy.data.repository.quotes

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.core.utils.getCurrentDate
import com.paliy_dmitriy.data.mapper.CurrencyMapper
import com.paliy_dmitriy.data.remote.factories.ExchangeRatesApiFactory
import com.paliy_dmitriy.data.remote.model.ApiResponse
import com.paliy_dmitriy.data.repository.base.BaseRepositoryImpl
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.model.Symbols
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
  private val apiFactory: ExchangeRatesApiFactory,
  private val currencyMapper: CurrencyMapper,
) : BaseRepositoryImpl(), CurrencyRepository {

  private val apiService = apiFactory.getApiService()

  override fun getQuotes(): Flow<Result<Currencie>> {
    return performApiCall(
      apiCall = { apiService.getQuotes(getCurrentDate(), "USD") },
      mapResponse = { response -> currencyMapper.mapToQuoteList(response) }
    )
  }

  override fun getSymbols(): Flow<Result<Symbols>> =
    performApiCall(
      apiCall = { apiService.getSymbols() },
      mapResponse = { response -> currencyMapper.mapToSymbolList(response) }
    )
}