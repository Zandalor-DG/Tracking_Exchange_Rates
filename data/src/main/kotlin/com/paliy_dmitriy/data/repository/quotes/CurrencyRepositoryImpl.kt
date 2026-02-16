package com.paliy_dmitriy.data.repository.quotes

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.data.mapper.QuoteMapper
import com.paliy_dmitriy.data.remote.api.ExchangeRatesDataApiService
import com.paliy_dmitriy.domain.exception.MissingApiKeyException
import com.paliy_dmitriy.domain.exception.NetworkTimeoutException
import com.paliy_dmitriy.domain.exception.NetworkUnreachableException
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
  private val exchangeRatesDataApiService: ExchangeRatesDataApiService,
  private val quoteMapper: QuoteMapper,
) : CurrencyRepository {

  override fun getQuotes(): Flow<Result<Currencie>> = flow {
    emit(Result.loading())

    try {
      val response = exchangeRatesDataApiService.getQuotes(getCurrentDate())

      when {
        response.isSuccessful && response.body()?.success == true -> {
          val dto = response.body()!!
          val domainQuote = quoteMapper.mapToDomainList(dto)
          emit(Result.Success(domainQuote))
        }

        response.code() == 401 -> {
          emit(Result.Error("Invalid or missing API key"))
        }

        else -> {
          emit(Result.Error("HTTP Error ${response.code()}: ${response.message()}"))
        }
      }
    } catch (e: Exception) {
      println("IS AHTUNG!!! ${e.message}")
      emit(Result.error(mapExceptionToMessage(e)))
    }
  }

  private fun mapExceptionToMessage(e: Exception): String {
    return when (e) {
      is SocketTimeoutException -> "Request timeout. Please try again"
      is ConnectException -> "No internet connection"
      is UnknownHostException -> "Cannot reach server. Check your connection"
      is retrofit2.HttpException -> "HTTP error: ${e.code()}"
      is MissingApiKeyException -> "API key is not configured"
      is NetworkTimeoutException -> "Network timeout: ${e.message}"
      is NetworkUnreachableException -> "Network unreachable: ${e.message}"
      is IllegalArgumentException -> "Invalid parameters: ${e.message}"
      else -> "Unexpected error: ${e.localizedMessage ?: "Please try again"}"
    }
  }

  fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return String.format(Locale.ROOT, "%04d-%02d-%02d", year, month, day)
  }
}