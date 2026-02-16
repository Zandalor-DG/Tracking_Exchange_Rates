package com.paliy_dmitriy.data.local.data_base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paliy_dmitriy.data.local.data_base.entity.FavoriteEntity

@Dao
interface FavoriteQuoteDao {
  @Insert
  suspend fun insert(favorite: FavoriteEntity)

  @Query("SELECT * FROM favorite_quotes")
  suspend fun getAllFavorites(): List<FavoriteEntity>

}