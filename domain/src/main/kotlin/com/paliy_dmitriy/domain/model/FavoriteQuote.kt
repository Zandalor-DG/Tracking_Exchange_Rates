package com.paliy_dmitriy.domain.model

data class FavoriteQuote(
    val favoriteId: Long = 0,
    val quoteId: String,
    val customName: String? = null,
    val addedDate: Long = System.currentTimeMillis(),
    val orderIndex: Int = 0
)