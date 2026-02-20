package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.data.remote.model.ApiResponse
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.model.Symbols
import com.paliy_dmitriy.domain.usecase.NoParamsFlowUseCase
import com.paliy_dmitriy.domain.usecase.currency.GetQuotesUseCase
import com.paliy_dmitriy.domain.usecase.currency.GetSymbolsUseCase
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
  private val getQuotesUseCase: GetQuotesUseCase,
  private val getSymbolsUseCase: GetSymbolsUseCase,
) : BaseViewModel() {
  private val _quotes = MutableStateFlow(Currencie("", emptyList()))
  private val _symbols = MutableStateFlow(Symbols(emptyList()))

  val quotes = _quotes.asStateFlow()
  val symbols = _symbols.asStateFlow()

  init {
    loadQuotes()
    loadSymbols()
  }

  fun loadQuotes() {
    performUseCase(
      useCase = getQuotesUseCase::invoke,
      onSuccess = { result ->
        _quotes.value = result
        _error.value = null
      },
      onError = { errorMessage ->
        _error.value = errorMessage
        _quotes.value = Currencie("", emptyList())
      }
    )
  }

  fun loadSymbols() {
    performUseCase(
      useCase = getSymbolsUseCase::invoke,
      onSuccess = { result ->
        _symbols.value = result
        _error.value = null
      },
      onError = { errorMessage ->
        _error.value = errorMessage
        _symbols.value = Symbols(emptyList())
      }
    )
  }

  fun retry() {
    loadQuotes()
    loadSymbols()
  }
}