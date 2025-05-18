package com.ezgieren.plantidentifyapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager {

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setOnboardingComplete(context: Context, isComplete: Boolean) {
        getPreferences(context).edit().putBoolean(Constants.KEY_ONBOARDING_COMPLETED, isComplete).apply()
    }

    fun isOnboardingComplete(context: Context): Boolean {
        return getPreferences(context).getBoolean(Constants.KEY_ONBOARDING_COMPLETED, false)
    }
}