package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.PreferencesManager
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    operator fun invoke(language: String) {
        preferencesManager.setLanguage(language)
    }
}

