package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AuthRepository

class CheckLoginStatusUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Boolean {
        return authRepository.isLoggedIn()
    }
}

