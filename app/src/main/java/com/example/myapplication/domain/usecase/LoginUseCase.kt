package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() {
        authRepository.login()
    }
}

