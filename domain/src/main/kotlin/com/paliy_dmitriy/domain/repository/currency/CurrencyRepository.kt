package com.paliy_dmitriy.domain.repository.currency

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.Currencie
import kotlinx.coroutines.flow.Flow

fun interface CurrencyRepository {
  fun getQuotes(): Flow<Result<Currencie>>
}