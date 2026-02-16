package com.paliy_dmitriy.core.common

sealed class Result<out T> {
  data object Loading : Result<Nothing>()
  data class Success<out T>(val data: T) : Result<T>()
  data class Error(val message: String, val throwable: Throwable? = null) : Result<Nothing>()

  companion object {
    fun <T> loading(): Result<T> = Loading
    fun <T> success(data: T): Result<T> = Success(data)
    fun <T> error(message: String, throwable: Throwable? = null): Result<T> = Error(message, throwable)
  }

  val isLoading: Boolean get() = this is Loading
  val isSuccess: Boolean get() = this is Success
  val isError: Boolean get() = this is Error

  fun getOrNull(): T? = when (this) {
    is Success -> data
    else -> null
  }

  fun getOrThrow(): T = when (this) {
    is Success -> data
    is Error -> throw throwable ?: RuntimeException(message)
    Loading -> throw IllegalStateException("Result is loading")
  }

  fun onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) action(data)
    return this
  }

  fun onError(action: (String, Throwable?) -> Unit): Result<T> {
    if (this is Error) action(message, throwable)
    return this
  }

  fun onLoading(action: () -> Unit): Result<T> {
    if (this is Loading) action()
    return this
  }
}