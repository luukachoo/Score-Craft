package com.core.common.resource

sealed class Resource<out T> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val errorMessage: String) : Resource<T>()
}