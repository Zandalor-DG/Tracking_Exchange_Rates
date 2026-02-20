package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.favorites

import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.FavoriteQuote
import com.paliy_dmitriy.domain.usecase.favorite.GetFavoritesUseCase
import com.paliy_dmitriy.domain.usecase.favorite.ObserveFavoritesUseCase
import com.paliy_dmitriy.domain.usecase.favorite.AddFavoriteUseCase
import com.paliy_dmitriy.domain.usecase.favorite.RemoveFavoriteUseCase
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
  private val getFavoritesUseCase: GetFavoritesUseCase,
  private val observeFavoritesUseCase: ObserveFavoritesUseCase,
  private val addFavoriteUseCase: AddFavoriteUseCase,
  private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : BaseViewModel() {

  private val _favorites = MutableStateFlow<List<FavoriteQuote>>(emptyList())
  val favorites = _favorites.asStateFlow()

  init {
    observeFavorites()
  }

  private fun observeFavorites() {
    viewModelScope.launch {
      observeFavoritesUseCase()
        .onStart {
          _isLoading.value = true
          _error.value = null
        }
        .catch { exception ->
          _isLoading.value = false
          _error.value = exception.message ?: "Unknown error"
        }
        .collect { result ->
          when (result) {
            is Result.Loading -> {
              _isLoading.value = result.isLoading
            }
            is Result.Success -> {
              _isLoading.value = false
              _favorites.value = result.data
            }
            is Result.Error -> {
              _isLoading.value = false
              _error.value = result.message
            }
          }
        }
    }
  }

  fun loadFavorites() {
    viewModelScope.launch {
      _isLoading.value = true
      _error.value = null

      val result = getFavoritesUseCase()
      when (result) {
        is Result.Success -> {
          _favorites.value = result.data
          _isLoading.value = false
        }
        is Result.Error -> {
          _error.value = result.message
          _isLoading.value = false
        }
        is Result.Loading -> {
          // Игнорируем
        }
      }
    }
  }

  fun addToFavorites(quoteId: String, customName: String? = null) {
    viewModelScope.launch {
      val result = addFavoriteUseCase(quoteId, customName)
      when (result) {
        is Result.Success -> {
          // Успешно добавлено, данные обновятся через observeFavorites
        }
        is Result.Error -> {
          _error.value = result.message
        }
        is Result.Loading -> {
          // Игнорируем
        }
      }
    }
  }

  fun removeFromFavorites(quoteId: String) {
    viewModelScope.launch {
      val result = removeFavoriteUseCase(quoteId)
      when (result) {
        is Result.Success -> {
          // Успешно удалено, данные обновятся через observeFavorites
        }
        is Result.Error -> {
          _error.value = result.message
        }
        is Result.Loading -> {
          // Игнорируем
        }
      }
    }
  }

  fun retry() {
    loadFavorites()
  }
}