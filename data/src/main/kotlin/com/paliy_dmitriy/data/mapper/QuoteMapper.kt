package com.paliy_dmitriy.data.mapper

import com.paliy_dmitriy.data.remote.model.response.QuoteResponseDto
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.model.QuoteItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteMapper @Inject constructor() {

  fun mapToDomain(rateName: String, rate: Double?): QuoteItem {
    return QuoteItem(
      rateName = rateName,
      rate = rate,
      isFavorite = false
    )
  }

  fun mapToDomainList(dto: QuoteResponseDto): Currencie {
    val quoteList = dto.rates.keys.map { mapToDomain(it, dto.rates[it]) }
    return Currencie(
      dto.base,
      quoteList
    )
  }
}

annotation class Quote
