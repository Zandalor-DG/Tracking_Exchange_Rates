package com.paliy_dmitriy.domain.usecase.currency

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.domain.model.Currencie
import com.paliy_dmitriy.domain.repository.currency.CurrencyRepository
import com.paliy_dmitriy.domain.usecase.NoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
  private val repository: CurrencyRepository
) : NoParamsFlowUseCase<Result<Currencie>>() {
  override fun invoke(): Flow<Result<Currencie>> {
    return repository.getQuotes()
  }
}