package com.example.myapplication.presentation.login

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login() {
        loginUseCase()
    }
}

