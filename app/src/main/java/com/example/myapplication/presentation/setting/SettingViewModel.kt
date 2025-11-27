package com.example.myapplication.presentation.setting

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.LogoutUseCase

class SettingViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() {
        logoutUseCase()
    }
}

