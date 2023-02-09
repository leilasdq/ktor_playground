package com.example.ktor_playground.common

sealed class ResponseResult<T> {
    data class SUCCESS<T>(val data: T? = null) : ResponseResult<T>()

    data class LOADING<T>(
        val isLoading: Boolean
    ) : ResponseResult<T>()

    data class ERROR<T>(val data: T? = null, val message: Throwable) : ResponseResult<T>()
}