package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.PreferencesManager
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    operator fun invoke(): Boolean {
        return preferencesManager.isDarkMode()
    }
}

