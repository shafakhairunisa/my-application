package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Boolean {
        return authRepository.isLoggedIn()
    }
}

