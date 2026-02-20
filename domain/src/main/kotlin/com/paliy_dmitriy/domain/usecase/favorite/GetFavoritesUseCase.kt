package com.paliy_dmitriy.domain.usecase.favorite

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.FavoriteQuote
import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): Result<List<FavoriteQuote>> {
        return repository.getFavorites()
    }
}