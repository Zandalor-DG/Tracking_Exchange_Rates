package com.paliy_dmitriy.domain.repository.quotes

import com.paliy_dmitriy.domain.model.quotes.QuoteModel

interface QuotesRepository {
  suspend fun loadQuotes(): List<QuoteModel>
}