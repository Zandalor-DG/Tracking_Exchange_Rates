package com.paliy_dmitriy.data.di

import android.content.Context
import com.paliy_dmitriy.data.remote.OkHttpClientFactory
import com.paliy_dmitriy.data.remote.RetrofitFactory
import com.paliy_dmitriy.data.remote.interceptors.ApiKeyInterceptor
import com.paliy_dmitriy.data.remote.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideApiKeyInterceptor(): ApiKeyInterceptor {
    return ApiKeyInterceptor()
  }

  @Provides
  @Singleton
  fun provideLoggingInterceptor(): LoggingInterceptor {
    return LoggingInterceptor()
  }

  @Provides
  @Singleton
  fun provideOkHttpClientFactory(
    @ApplicationContext context: Context,
    apiKeyInterceptor: ApiKeyInterceptor,
    loggingInterceptor: LoggingInterceptor
  ): OkHttpClientFactory {
    return OkHttpClientFactory(context, apiKeyInterceptor, loggingInterceptor)
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(
    okHttpClientFactory: OkHttpClientFactory
  ): OkHttpClient {
    return okHttpClientFactory.create()
  }

  @Provides
  @Singleton
  fun provideRetrofitFactory(
    okHttpClient: OkHttpClient
  ): RetrofitFactory {
    return RetrofitFactory(okHttpClient)
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    retrofitFactory: RetrofitFactory
  ): Retrofit {
    return retrofitFactory.create()
  }
}