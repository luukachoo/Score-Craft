package com.core.common.resource

sealed class Resource<out D : Any> {
    data class Success<out D : Any>(val data: D) : Resource<D>()
    data class Error<out D : Any>(val errorMessage: String) : Resource<D>()
    data class Loading<Nothing : Any>(val loading: Boolean) : Resource<Nothing>()
}

fun <D : Any> Resource<D>.takeIfSuccess(onSuccess: (D) -> Unit): Resource<D> {
    if (this is Resource.Success) {
        onSuccess(this.data)
    }
    return this
}

fun <D : Any> Resource<D>.takeIfError(onError: (String) -> Unit): Resource<D> {
    if (this is Resource.Error) {
        onError(this.errorMessage)
    }
    return this
}

fun <D : Any> Resource<D>.takeIfLoading(onLoading: (Boolean) -> Unit): Resource<D> {
    if (this is Resource.Loading) {
        onLoading(this.loading)
    }
    return this
}