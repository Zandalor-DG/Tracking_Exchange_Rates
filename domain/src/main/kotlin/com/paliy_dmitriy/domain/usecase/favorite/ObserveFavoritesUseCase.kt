package com.paliy_dmitriy.domain.usecase.favorite

import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
  private val repository: FavoriteRepository
) {
  operator fun invoke() = repository.observeFavorites()
}