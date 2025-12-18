package com.example.myapplication.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.CheckLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase
) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return checkLoginStatusUseCase()
    }
}

