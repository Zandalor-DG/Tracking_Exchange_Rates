package com.paliy_dmitriy.domain.model.quotes

data class QuoteModel(
  val id: Int,
  val title: String,
  val price: Double,
  val isFavorite: Boolean = false,
)