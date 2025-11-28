package com.example.myapplication.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.CheckLoginStatusUseCase

class SplashViewModel(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase
) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return checkLoginStatusUseCase()
    }
}

