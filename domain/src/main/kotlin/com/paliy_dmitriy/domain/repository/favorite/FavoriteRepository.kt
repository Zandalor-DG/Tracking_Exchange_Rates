package com.paliy_dmitriy.domain.repository.favorite

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.FavoriteQuote
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

  suspend fun addFavorite(quoteId: String, customName: String? = null): Result<Unit>

  suspend fun removeFavorite(quoteId: String): Result<Unit>

  suspend fun getFavorites(): Result<List<FavoriteQuote>>

  fun observeFavorites(): Flow<Result<List<FavoriteQuote>>>

  suspend fun isFavorite(quoteId: String): Result<Boolean>

  suspend fun updateFavoriteOrder(quoteId: String, newOrderIndex: Int): Result<Unit>
}