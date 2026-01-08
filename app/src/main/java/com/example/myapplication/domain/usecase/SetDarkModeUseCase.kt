package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.PreferencesManager
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    operator fun invoke(isDarkMode: Boolean) {
        preferencesManager.setDarkMode(isDarkMode)
    }
}

