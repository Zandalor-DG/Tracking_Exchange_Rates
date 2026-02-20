package com.paliy_dmitriy.data.di

import android.content.Context
import androidx.room.Room
import com.paliy_dmitriy.data.local.database.AppDatabase
import com.paliy_dmitriy.data.local.database.dao.FavoriteQuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    @ApplicationContext context: Context
  ): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "tracking_exchange_rates.db"
    )
      .fallbackToDestructiveMigration() // Для разработки, в продакшене лучше использовать миграции
      .build()
  }

  @Provides
  @Singleton
  fun provideFavoriteQuoteDao(
    database: AppDatabase
  ): FavoriteQuoteDao {
    return database.favoriteQuoteDao()
  }
}