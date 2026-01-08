package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.PreferencesManager
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    operator fun invoke(): String {
        return preferencesManager.getLanguage()
    }
}

