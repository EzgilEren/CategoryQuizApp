package com.ezgieren.categoryquizapp.utils

import com.ezgieren.categoryquizapp.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY

    const val ENDPOINT_GET_CATEGORIES = "getCategories"
    const val ENDPOINT_GET_QUESTIONS = "getQuestions"

    // Error messages
    const val API_ERROR_MESSAGE = "Something went wrong"
    const val DEFAULT_ERROR_MESSAGE = "Unknown error occurred"
    const val NETWORK_ERROR_MESSAGE = "Check your internet connection"
    const val NO_DETAILS = "No details available"
}
