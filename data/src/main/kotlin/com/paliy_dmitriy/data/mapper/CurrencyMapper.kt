package com.paliy_dmitriy.data.mapper

import com.paliy_dmitriy.data.remote.model.response.QuoteResponseDto
import com.paliy_dmitriy.data.remote.model.response.SymbolResponseDto
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.model.QuoteItem
import com.paliy_dmitriy.domain.model.Symbols
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyMapper @Inject constructor() {

  fun mapToQuote(rateName: String, rate: Double?): QuoteItem {
    return QuoteItem(
      rateName = rateName,
      rate = rate,
      isFavorite = false
    )
  }

  fun mapToQuoteList(dto: QuoteResponseDto): Currencie {
    val quoteList = dto.rates.keys.map { mapToQuote(it, dto.rates[it]) }
    return Currencie(
      dto.base,
      quoteList
    )
  }

  fun mapToSymbolList(dto: SymbolResponseDto): Symbols {
    return Symbols(dto.symbols.keys.toList())
  }
}

annotation class Quote
