package com.example.myapplication.domain.repository

interface AuthRepository {
    fun isLoggedIn(): Boolean
    fun login()
    fun logout()
}

