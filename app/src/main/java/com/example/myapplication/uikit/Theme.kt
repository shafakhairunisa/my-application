package com.example.myapplication.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF82E180),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF42b67b),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF4285f4),
    onSecondary = Color.White,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF434955),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF434955),
    surfaceVariant = Color(0xfff8f9f9),
    onSurfaceVariant = Color(0xFF82868B),
    error = Color(0xFFEA5455),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF82E180),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF42b67b),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF4285f4),
    onSecondary = Color(0xFF000000),
    background = Color(0xFF202020),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF202020),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF303030),
    onSurfaceVariant = Color(0xFFEEEEEE),
    error = Color(0xFFEA5455),
    onError = Color(0xFF000000)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
