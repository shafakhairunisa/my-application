package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.local.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.myapplication.domain.usecase.GetDarkModeUseCase
import com.example.myapplication.domain.usecase.SetDarkModeUseCase
import com.example.myapplication.domain.usecase.GetLanguageUseCase
import com.example.myapplication.domain.usecase.SetLanguageUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ): PreferencesManager {
        return PreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideGetDarkModeUseCase(
        preferencesManager: PreferencesManager
    ): GetDarkModeUseCase {
        return GetDarkModeUseCase(preferencesManager)
    }

    @Provides
    @Singleton
    fun provideSetDarkModeUseCase(
        preferencesManager: PreferencesManager
    ): SetDarkModeUseCase {
        return SetDarkModeUseCase(preferencesManager)
    }

    @Provides
    @Singleton
    fun provideGetLanguageUseCase(
        preferencesManager: PreferencesManager
    ): GetLanguageUseCase {
        return GetLanguageUseCase(preferencesManager)
    }

    @Provides
    @Singleton
    fun provideSetLanguageUseCase(
        preferencesManager: PreferencesManager
    ): SetLanguageUseCase {
        return SetLanguageUseCase(preferencesManager)
    }
}