package com.paliy_dmitriy.data.di

import com.paliy_dmitriy.data.di.qualifiers.MockApi
import com.paliy_dmitriy.data.di.qualifiers.RealApi
import com.paliy_dmitriy.data.mapper.CurrencyMapper
import com.paliy_dmitriy.data.mapper.FavoriteMapper
import com.paliy_dmitriy.data.remote.api.ExchangeRatesDataApiService
import com.paliy_dmitriy.data.remote.api.mock.MockExchangeRatesDataApiService
import com.paliy_dmitriy.data.remote.factories.ExchangeRatesApiFactory
import com.paliy_dmitriy.data.repository.favorite.FavoriteRepositoryImpl
import com.paliy_dmitriy.data.repository.quotes.CurrencyRepositoryImpl
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import com.paliy_dmitriy.domain.repository.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

  @Binds
  @Singleton
  abstract fun bindCurrencyRepository(
    currencyRepositoryImpl: CurrencyRepositoryImpl
  ): CurrencyRepository

  @Binds
  @Singleton
  abstract fun bindFavoriteRepository(
    favoriteRepositoryImpl: FavoriteRepositoryImpl
  ): FavoriteRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataModuleProviders {

  @RealApi
  @Provides
  @Singleton
  fun provideRealApiService(retrofit: Retrofit): ExchangeRatesDataApiService =
    retrofit.create(ExchangeRatesDataApiService::class.java)

  @MockApi
  @Provides
  @Singleton
  fun provideMockApiService(): ExchangeRatesDataApiService =
    MockExchangeRatesDataApiService()

  @Provides
  @Singleton
  fun provideCurrencyMapper(): CurrencyMapper = CurrencyMapper()

  @Provides
  @Singleton
  fun provideFavoriteMapper(): FavoriteMapper = FavoriteMapper()

  @Provides
  @Singleton
  fun provideExchangeRatesApiFactory(
    @RealApi realApiService: ExchangeRatesDataApiService,
    @MockApi mockApiService: ExchangeRatesDataApiService
  ): ExchangeRatesApiFactory {
    return ExchangeRatesApiFactory(realApiService, mockApiService)
  }
}