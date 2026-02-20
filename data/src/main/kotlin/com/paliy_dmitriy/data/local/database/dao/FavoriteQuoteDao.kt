package com.paliy_dmitriy.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.paliy_dmitriy.data.local.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(favorite: FavoriteEntity)

  @Delete
  suspend fun delete(favorite: FavoriteEntity)

  @Query("DELETE FROM favorites WHERE quoteId = :quoteId")
  suspend fun deleteByQuoteId(quoteId: String)

  @Query("SELECT * FROM favorites ORDER BY orderIndex ASC")
  suspend fun getAllFavorites(): List<FavoriteEntity>

  @Query("SELECT * FROM favorites ORDER BY orderIndex ASC")
  fun observeAllFavorites(): Flow<List<FavoriteEntity>>

  @Query("SELECT * FROM favorites WHERE quoteId = :quoteId")
  suspend fun getFavoriteByQuoteId(quoteId: String): FavoriteEntity?

  @Query("SELECT COUNT(*) FROM favorites WHERE quoteId = :quoteId")
  suspend fun isFavorite(quoteId: String): Int

  @Update
  suspend fun update(favorite: FavoriteEntity)

  @Query("UPDATE favorites SET orderIndex = :orderIndex WHERE quoteId = :quoteId")
  suspend fun updateOrderIndex(quoteId: String, orderIndex: Int)

  @Query("DELETE FROM favorites")
  suspend fun deleteAll()
}