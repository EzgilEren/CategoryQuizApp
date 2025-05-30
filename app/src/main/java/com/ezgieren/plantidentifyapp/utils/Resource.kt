package com.ezgieren.plantidentifyapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val detailedMessage: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(
        message: String,
        detailedMessage: String? = null,
        data: T? = null
    ) : Resource<T>(data, message, detailedMessage)
    class Loading<T> : Resource<T>()
}