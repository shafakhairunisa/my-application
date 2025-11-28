package com.example.myapplication.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.LogoutUseCase

class SettingViewModelFactory(
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingViewModel(logoutUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

