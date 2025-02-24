package com.example.videotesttask.domain

sealed class LoadingState<T>(val data: T? = null, val message: String? = null) {
    class Error<T>(message: String?) : LoadingState<T>(data = null, message)

    class Success<T>(data: T? = null) : LoadingState<T>(data, message = null)

    class Loading<T> : LoadingState<T>()

    class Empty<T> : LoadingState<T>()
}