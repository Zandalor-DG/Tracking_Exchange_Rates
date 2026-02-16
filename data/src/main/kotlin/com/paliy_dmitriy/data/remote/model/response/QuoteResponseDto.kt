package com.paliy_dmitriy.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponseDto(
  @SerialName("base")
  val base: String,

  @SerialName("date")
  val date: String,

  @SerialName("historical")
  val historical: Boolean? = false,

  @SerialName("rates")
  val rates: Map<String, Double>,

  @SerialName("success")
  val success: Boolean,

  @SerialName("timestamp")
  val timestamp: Long
)
