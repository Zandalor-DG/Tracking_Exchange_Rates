package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.core.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

  protected val _isLoading = MutableStateFlow(false)
  val isLoading = _isLoading.asStateFlow()

  protected val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()

  protected fun <T> performUseCase(
    useCase: suspend () -> Flow<Result<T>>,
    onSuccess: (T) -> Unit,
    onError: ((String) -> Unit)? = null
  ) {
    viewModelScope.launch {
      useCase()
        .onStart {
          _isLoading.value = true
          _error.value = null
        }
        .catch { exception ->
          _isLoading.value = false
          val errorMessage = exception.message ?: "Unknown error"
          _error.value = errorMessage
          onError?.invoke(errorMessage)
        }
        .collect { result ->
          when (result) {
            is Result.Loading -> {
              _isLoading.value = result.isLoading
            }

            is Result.Success -> {
              _isLoading.value = false
              onSuccess(result.data)
            }

            is Result.Error -> {
              _isLoading.value = false
              _error.value = result.message
              onError?.invoke(result.message)
            }
          }
        }
    }
  }

  fun clearError() {
    if (!_error.value.isNullOrEmpty()) {
      _error.value = null
    }
  }
}