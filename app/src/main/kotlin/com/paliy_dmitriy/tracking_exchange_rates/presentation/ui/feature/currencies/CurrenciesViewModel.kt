package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.domain.model.quotes.QuoteModel
import com.paliy_dmitriy.domain.usecase.quotes.LoadQuotesUseCase
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
  private val navigationManager: NavigationManager,
  private val loadQuotesUseCase: LoadQuotesUseCase
) : ViewModel() {
  private val _isLoading = MutableStateFlow(false)
  private val _quotes = MutableStateFlow<List<QuoteModel>>(emptyList())
  private val _error = MutableStateFlow<String?>(null)

  val isLoading = _isLoading.asStateFlow()
  val quotes = _quotes.asStateFlow()
  val error = _error.asStateFlow()

  init {
    loadQuotes()
  }

  fun loadQuotes() {
    viewModelScope.launch {
      try {
        _isLoading.value = true
        val result = withContext(Dispatchers.IO) {
           loadQuotesUseCase.execute()
        }
        _quotes.value = result
      } catch (e: Exception) {
        _error.value = e.message ?: ""
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun retry() {
    loadQuotes()
  }

  fun clearError() {
    _error.value = null
  }

  fun onQuoteClicked(quoteId: String) {

  }

  fun onRefresh() {
    loadQuotes()
  }

  suspend fun navigateToFilters() {
    viewModelScope.launch {
      navigationManager.navigateTo(Destinations.Filters)
    }
  }
}