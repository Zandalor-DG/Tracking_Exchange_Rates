package com.paliy_dmitriy.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
  @PrimaryKey
  val id: String,
  val fromCurrency: String,
  val toCurrency: String,
  val rate: Double,
  val timestamp: Long
)