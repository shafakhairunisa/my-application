package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke() {
        authRepository.logout()
    }
}

