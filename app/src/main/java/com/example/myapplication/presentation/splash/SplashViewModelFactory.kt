package com.example.myapplication.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.CheckLoginStatusUseCase

class SplashViewModelFactory(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(checkLoginStatusUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

