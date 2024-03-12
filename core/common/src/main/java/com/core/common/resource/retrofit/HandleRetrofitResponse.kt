package com.core.common.resource.retrofit

<<<<<<< HEAD:core/common/src/main/java/com/core/common/resource/HandleRetrofitResponse.kt

=======
import com.core.common.resource.Resource
>>>>>>> origin/feature_splash:core/common/src/main/java/com/core/common/resource/retrofit/HandleRetrofitResponse.kt
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleRetrofitResponse {
    fun <T : Any> apiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loading(loading = true))
        val response = call()
        if (response.isSuccessful) {
            val test = response.body()
            response.body()?.let {
                emit(Resource.Success(data = it))
            } ?: emit(Resource.Error(errorMessage = "Empty Response"))
        } else {
            val test = response.errorBody().toString()
            emit(Resource.Error(errorMessage = response.errorBody()?.string() ?: "Unknown Error"))
        }
        emit(Resource.Loading(loading = false))
    }
}