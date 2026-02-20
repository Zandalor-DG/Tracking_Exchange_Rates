package com.paliy_dmitriy.data.remote.model.response

import com.paliy_dmitriy.data.remote.model.ApiResponse

data class SymbolResponseDto(
  override val success: Boolean,
  val symbols: Map<String, String>,
) : ApiResponse