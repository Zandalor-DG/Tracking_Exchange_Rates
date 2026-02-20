package com.paliy_dmitriy.domain.usecase.favorite

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
  private val repository: FavoriteRepository
) {
  suspend operator fun invoke(quoteId: String, customName: String? = null): Result<Unit> {
    return repository.addFavorite(quoteId, customName)
  }
}
