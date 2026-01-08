package com.example.myapplication.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    fun isDarkMode(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false)
    }

    fun setDarkMode(isDarkMode: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_DARK_MODE, isDarkMode)
            apply()
        }
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(KEY_LANGUAGE, LANGUAGE_ENGLISH) ?: LANGUAGE_ENGLISH
    }

    fun setLanguage(language: String) {
        sharedPreferences.edit().apply {
            putString(KEY_LANGUAGE, language)
            apply()
        }
    }

    companion object {
        private const val PREF_NAME = "AppPreferences"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_DARK_MODE = "darkMode"
        private const val KEY_LANGUAGE = "language"

        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_INDONESIAN = "in"
    }
}

