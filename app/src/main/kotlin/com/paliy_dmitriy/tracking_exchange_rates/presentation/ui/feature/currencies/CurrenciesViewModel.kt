package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.usecase.currency.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
  private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {
  private val _isLoading = MutableStateFlow(false)
  private val _quotes = MutableStateFlow<Currencie>(Currencie("", emptyList()))
  private val _error = MutableStateFlow<String?>(null)

  val isLoading = _isLoading.asStateFlow()
  val quotes = _quotes.asStateFlow()
  val error = _error.asStateFlow()

  init {
    loadQuotes()
  }

  fun loadQuotes() {
    viewModelScope.launch {
      _isLoading.value = true
      getQuotesUseCase().collect { result ->
        when (result) {
          is Result.Loading -> {
            _isLoading.value = result.isLoading
          }

          is Result.Success -> {
            _quotes.value = result.data
            _isLoading.value = result.isLoading
          }

          is Result.Error -> {
            _error.value = result.message
            _isLoading.value = result.isLoading
          }
        }
      }
    }
  }

  fun retry() {
    loadQuotes()
  }

  fun clearError() {
    if (!_error.value.isNullOrEmpty()) { // Проверяем что не null и не пустая
      _error.value = null
    }
  }
}