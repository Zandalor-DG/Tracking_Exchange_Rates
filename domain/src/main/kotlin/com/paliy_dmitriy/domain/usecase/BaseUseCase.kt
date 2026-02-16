package com.paliy_dmitriy.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<in P, R> {
  abstract suspend operator fun invoke(params: P): R
}

abstract class FlowUseCase<in P, R> {
  abstract operator fun invoke(params: P): Flow<R>
}

abstract class NoParamsUseCase<R> : UseCase<Unit, R>() {
  override suspend operator fun invoke(params: Unit): R = invoke()
  abstract suspend operator fun invoke(): R
}

abstract class NoParamsFlowUseCase<R> : FlowUseCase<Unit, R>() {
  override operator fun invoke(params: Unit): Flow<R> = invoke()
  abstract operator fun invoke(): Flow<R>
}