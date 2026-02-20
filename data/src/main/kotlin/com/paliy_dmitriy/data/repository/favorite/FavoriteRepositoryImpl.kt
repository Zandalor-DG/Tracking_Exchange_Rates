package com.paliy_dmitriy.data.repository.favorite

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.data.local.database.dao.FavoriteQuoteDao
import com.paliy_dmitriy.data.local.database.entity.FavoriteEntity
import com.paliy_dmitriy.data.mapper.FavoriteMapper
import com.paliy_dmitriy.data.repository.base.BaseRepositoryImpl
import com.paliy_dmitriy.domain.model.FavoriteQuote
import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(
  private val favoriteDao: FavoriteQuoteDao,
  private val favoriteMapper: FavoriteMapper
) : BaseRepositoryImpl(), FavoriteRepository {

  override suspend fun addFavorite(quoteId: String, customName: String?): Result<Unit> {
    return safeDatabaseCall {
      val existingFavorite = favoriteDao.getFavoriteByQuoteId(quoteId)
      if (existingFavorite == null) {
        val favorites = favoriteDao.getAllFavorites()
        val newOrderIndex = favorites.size

        val favoriteEntity = FavoriteEntity(
          quoteId = quoteId,
          customName = customName,
          addedDate = System.currentTimeMillis(),
          orderIndex = newOrderIndex
        )
        favoriteDao.insert(favoriteEntity)
      }
    }
  }

  override suspend fun removeFavorite(quoteId: String): Result<Unit> {
    return safeDatabaseCall {
      favoriteDao.deleteByQuoteId(quoteId)
    }
  }

  override suspend fun getFavorites(): Result<List<FavoriteQuote>> {
    return safeDatabaseCall {
      val entities = favoriteDao.getAllFavorites()
      favoriteMapper.mapToDomainList(entities)
    }
  }

  override fun observeFavorites(): Flow<Result<List<FavoriteQuote>>> = flow {
    emit(Result.loading())

    favoriteDao.observeAllFavorites()
      .map { entities ->
        Result.Success(favoriteMapper.mapToDomainList(entities))
      }
      .catch { throwable ->
        emit(Result.error(mapThrowableToMessage(throwable)))
      }
      .collect { result ->
        emit(result)
      }
  }

  override suspend fun isFavorite(quoteId: String): Result<Boolean> {
    return safeDatabaseCall {
      favoriteDao.isFavorite(quoteId) > 0
    }
  }

  override suspend fun updateFavoriteOrder(quoteId: String, newOrderIndex: Int): Result<Unit> {
    return safeDatabaseCall {
      favoriteDao.updateOrderIndex(quoteId, newOrderIndex)
    }
  }

  private suspend fun <T> safeDatabaseCall(block: suspend () -> T): Result<T> {
    return try {
      val result = block()
      Result.Success(result)
    } catch (e: Exception) {
      Result.error("Database error: ${e.localizedMessage}")
    }
  }
}