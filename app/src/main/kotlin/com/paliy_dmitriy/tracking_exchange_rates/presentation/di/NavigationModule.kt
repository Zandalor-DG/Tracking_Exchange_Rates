package com.paliy_dmitriy.tracking_exchange_rates.presentation.di

import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManager
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManagerImpl
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationStateHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewComponent::class)
abstract class NavigationModule {

  @Binds
  abstract fun bindNavigationManager(
    impl: NavigationManagerImpl
  ): NavigationManager

  companion object {
    @Provides
    @Singleton
    fun provideNavigationStateHolder(): NavigationStateHolder {
      return NavigationStateHolder()
    }
  }
}