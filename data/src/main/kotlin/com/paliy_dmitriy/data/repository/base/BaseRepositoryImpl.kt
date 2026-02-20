package com.paliy_dmitriy.data.repository.base

import com.paliy_dmitriy.core.common.Result
import com.paliy_dmitriy.data.remote.model.ApiResponse
import com.paliy_dmitriy.domain.exception.MissingApiKeyException
import com.paliy_dmitriy.domain.exception.NetworkTimeoutException
import com.paliy_dmitriy.domain.exception.NetworkUnreachableException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepositoryImpl {

    protected suspend fun <T : ApiResponse, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        mapResponse: (T) -> R
    ): Result<R> {
        return try {
            val response = apiCall()

            when {
                response.isSuccessful && response.body()?.success == true -> {
                    val dto = response.body()!!
                    Result.Success(mapResponse(dto))
                }

                response.code() == 401 -> {
                    Result.Error("Invalid or missing API key")
                }

                else -> {
                    Result.Error("HTTP Error ${response.code()}: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            Result.error(mapExceptionToMessage(e))
        }
    }

    protected fun <T : ApiResponse, R> performApiCall(
        apiCall: suspend () -> Response<T>,
        mapResponse: (T) -> R
    ): Flow<Result<R>> = flow {
        emit(Result.loading())
        val result = safeApiCall(apiCall, mapResponse)
        emit(result)
    }

    protected fun mapExceptionToMessage(e: Exception): String {
        return when (e) {
            is SocketTimeoutException -> "Request timeout. Please try again"
            is ConnectException -> "No internet connection"
            is UnknownHostException -> "Cannot reach server. Check your connection"
            is retrofit2.HttpException -> "HTTP error: ${e.code()}"
            is MissingApiKeyException -> "API key is not configured"
            is NetworkTimeoutException -> "Network timeout: ${e.message}"
            is NetworkUnreachableException -> "Network unreachable: ${e.message}"
            is IllegalArgumentException -> "Invalid parameters: ${e.message}"
            else -> "Unexpected error: ${e.localizedMessage ?: "Please try again"}"
        }
    }

    protected fun mapThrowableToMessage(t: Throwable): String {
        return when (t) {
            is Exception -> mapExceptionToMessage(t)
            else -> "Unexpected error: ${t.localizedMessage ?: "Please try again"}"
        }
    }
}