package com.paliy_dmitriy.data.di

import com.paliy_dmitriy.data.repository.QuotesRepositoryImpl
import com.paliy_dmitriy.domain.repository.quotes.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
  @Provides
  @Singleton
  fun provideQuotesRepository(): QuotesRepository {
    return QuotesRepositoryImpl()
  }
}