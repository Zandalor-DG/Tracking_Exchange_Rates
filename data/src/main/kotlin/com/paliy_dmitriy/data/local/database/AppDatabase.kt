package com.paliy_dmitriy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paliy_dmitriy.data.local.database.dao.FavoriteQuoteDao
import com.paliy_dmitriy.data.local.database.entity.FavoriteEntity
import com.paliy_dmitriy.data.local.database.entity.QuoteEntity

@Database(
  entities = [
    FavoriteEntity::class,
    QuoteEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}