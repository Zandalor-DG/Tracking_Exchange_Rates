package com.paliy_dmitriy.data.repository

import com.paliy_dmitriy.domain.model.quotes.QuoteModel
import com.paliy_dmitriy.domain.repository.quotes.QuotesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepositoryImpl@Inject constructor(): QuotesRepository {
  override suspend fun loadQuotes(): List<QuoteModel> {
    return listOf(
      QuoteModel(1, "AED", 3.932455, true),
      QuoteModel(2, "USD", 2.768594),
      QuoteModel(3, "BYN", 4.213476),
      QuoteModel(4, "RUB", 1.780987),
    )
  }
}