package com.example.myapplication.data.repository

import com.example.myapplication.data.local.PreferencesManager
import com.example.myapplication.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val preferencesManager: PreferencesManager
) : AuthRepository {

    override fun isLoggedIn(): Boolean {
        return preferencesManager.isLoggedIn()
    }

    override fun login() {
        preferencesManager.setLoggedIn(true)
    }

    override fun logout() {
        preferencesManager.setLoggedIn(false)
    }
}

