package com.core.common.resource.retrofit

import com.core.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class HandleRetrofitResponse {
    fun <T : Any> apiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    emit(Resource.Success(body))
                } ?: emit(Resource.Error("No data received"))
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                emit(Resource.Error("Error ${response.code()}: $errorBody"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(handleException(e)))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    private fun handleException(e: Throwable): String = when (e) {
        is HttpException -> "Network error: ${e.code()} ${e.message}"
        is SocketTimeoutException -> "Connection timeout"
        is IOException -> "Network error - IO Exception"
        else -> "Unknown error: ${e.message}"
    }
}
