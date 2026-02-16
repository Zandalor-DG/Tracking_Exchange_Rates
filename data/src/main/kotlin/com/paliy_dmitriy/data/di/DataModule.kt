package com.paliy_dmitriy.data.di

import com.paliy_dmitriy.data.mapper.QuoteMapper
import com.paliy_dmitriy.data.remote.api.ExchangeRatesDataApiService
import com.paliy_dmitriy.data.repository.quotes.CurrencyRepositoryImpl
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Binds
  abstract fun bindCurrencyRepository(
    impl: CurrencyRepositoryImpl
  ): CurrencyRepository

  companion object {

    @Provides
    @Singleton
    fun provideApiService(
      retrofit: Retrofit
    ): ExchangeRatesDataApiService {
      return retrofit.create(ExchangeRatesDataApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteMapper(): QuoteMapper {
      return QuoteMapper()
    }
  }
}