package com.paliy_dmitriy.domain.usecase.currency

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.Symbols
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import com.paliy_dmitriy.domain.usecase.NoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSymbolsUseCase @Inject constructor(
  private val repository: CurrencyRepository
) : NoParamsFlowUseCase<Result<Symbols>>() {
  override fun invoke(): Flow<Result<Symbols>> {
    return repository.getSymbols()
  }
}