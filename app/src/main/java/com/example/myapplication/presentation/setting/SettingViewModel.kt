package com.example.myapplication.presentation.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetDarkModeUseCase
import com.example.myapplication.domain.usecase.GetLanguageUseCase
import com.example.myapplication.domain.usecase.LogoutUseCase
import com.example.myapplication.domain.usecase.SetDarkModeUseCase
import com.example.myapplication.domain.usecase.SetLanguageUseCase
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(getDarkModeUseCase())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _currentLanguage = MutableStateFlow(getLanguageUseCase())
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    private val _recreateActivityEvent = MutableSharedFlow<Unit>()
    val recreateActivityEvent: SharedFlow<Unit> = _recreateActivityEvent.asSharedFlow()

    fun toggleDarkMode() {
        val newValue = !_isDarkMode.value
        setDarkModeUseCase(newValue)
        _isDarkMode.value = newValue
    }

    fun setLanguage(language: String, context: Context) {
        viewModelScope.launch {
            // Save language preference
            setLanguageUseCase(language)

            // Update Lingver locale
            Lingver.getInstance().setLocale(context, language)

            // Update state
            _currentLanguage.value = language

            // Trigger activity recreation
            _recreateActivityEvent.emit(Unit)
        }
    }

    fun logout() {
        logoutUseCase()
        viewModelScope.launch {
            _logoutEvent.emit(Unit)
        }
    }
}