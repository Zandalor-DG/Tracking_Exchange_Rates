package com.paliy_dmitriy.domain.repository.currency

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.model.Symbols
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
  fun getQuotes(): Flow<Result<Currencie>>
  fun getSymbols(): Flow<Result<Symbols>>
}