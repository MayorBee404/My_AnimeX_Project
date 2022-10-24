package com.dicoding.core.data

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T) : com.dicoding.core.data.Resource<T>(data)
    class Loading<out T>(data: T? = null) : com.dicoding.core.data.Resource<T>(data)
    class Error<out T>(message: String, data: T? = null) : com.dicoding.core.data.Resource<T>(data, message)
}
