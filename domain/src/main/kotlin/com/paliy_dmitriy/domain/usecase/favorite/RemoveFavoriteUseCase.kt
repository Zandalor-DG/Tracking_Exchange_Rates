package com.paliy_dmitriy.domain.usecase.favorite

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
  private val repository: FavoriteRepository
) {
  suspend operator fun invoke(quoteId: String): Result<Unit> {
    return repository.removeFavorite(quoteId)
  }
}