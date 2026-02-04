package com.paliy_dmitriy.domain.usecase.quotes

import com.paliy_dmitriy.domain.model.quotes.QuoteModel
import com.paliy_dmitriy.domain.repository.quotes.QuotesRepository
import javax.inject.Inject

class LoadQuotesUseCase @Inject constructor(
  private val quotesRepository: QuotesRepository
) {
  suspend fun execute(): List<QuoteModel> {
    return quotesRepository.loadQuotes()
  }
}