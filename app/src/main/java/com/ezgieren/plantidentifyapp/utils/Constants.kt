package com.ezgieren.plantidentifyapp.utils

import com.ezgieren.plantidentifyapp.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL

    const val ENDPOINT_GET_CATEGORIES = "getCategories"
    const val ENDPOINT_GET_QUESTIONS = "getQuestions"

    const val EMPTY = ""
    const val NO_CATEGORY = "No category"

    // Onboarding Constants
    const val ONBOARDING_PAGE_COUNT = 2
    const val ONBOARDING_PAGE_ONE = 0
    const val ONBOARDING_PAGE_TWO = 1
    const val INVALID_ONBOARDING_POSITION = "Invalid onboarding position"

    // Preferences
    const val PREF_NAME = "plant_prefs"
    const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

    // Terms Constants
    const val TERMS_PREFIX = "By tapping next, you are agreeing to PlantID "
    const val TERMS_OF_USE = "Terms of Use"
    const val PRIVACY_POLICY = "Privacy Policy."

    // Error messages
    const val API_ERROR_MESSAGE = "Something went wrong"
    const val DEFAULT_ERROR_MESSAGE = "Unknown error occurred"
    const val NETWORK_ERROR_MESSAGE = "Check your internet connection"
    const val NO_DETAILS = "No details available"
}
