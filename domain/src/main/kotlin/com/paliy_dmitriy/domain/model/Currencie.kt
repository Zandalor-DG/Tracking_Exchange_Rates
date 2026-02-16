package com.paliy_dmitriy.domain.model

data class Currencie(
  val base: String,
  val quoteList: List<QuoteItem>,
)