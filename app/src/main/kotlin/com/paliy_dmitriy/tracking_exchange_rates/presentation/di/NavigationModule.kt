package com.paliy_dmitriy.tracking_exchange_rates.presentation.di

import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManager
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

  @Binds
  @Singleton
  fun bindNavigationManager(
    impl: NavigationManagerImpl
  ): NavigationManager
}