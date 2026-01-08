package com.example.myapplication.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object BasicColorPalate {
    val LightPrimary get() = Color(0xFFFFFFFF)

    val DarkLightPrimary get() = Color(0xFF202020)

    val GreenPrimary get() = Color(0xFF82E180)
    val GreenSecondary get() = Color(0xff42b67b)
    val GreenLight get() = Color(0xffaae37a)
    val GreenSubtle get() = Color(0xFFF0FAF4)
    val GreenBlueSemiTransparent get() = Color(0x1F2CFFCD)
    val LightSubtle get() = Color(0xfff8f9f9)

    val DarkPrimary = Color(0xFF000000)
    val DarkSecondary = Color(0xFF434955)
    val DarkSemiTransparent = Color(0x47000000)
    val DarkLight = Color(0xFF797E86)
    val DarkSubtle = Color(0xffafb0c8)

    val BluePrimary = Color(0xff4285f4)
    val BlueLight get() = Color(0xff2face2)

    val TextColor = Color(0xFF434955)
    val TextSecondary = Color(0xFF82868B)

    val TextDisabled = Color(0xFFD8D6DE)

    val TextPositive = Color(0xFF28C76F)
    val TextAlert = Color(0xFFEA5455)
    val TextWarning = Color(0xFFFF9F43)
    val TextInfo = Color(0xFF00CFE8)
}

object PandaiColor {
    val textPrimary
        @Composable get() = if (!isSystemInDarkTheme()) BasicColorPalate.TextColor else BasicColorPalate.LightPrimary

    val textLightPrimary
        @Composable get() = if (!isSystemInDarkTheme()) BasicColorPalate.LightPrimary else BasicColorPalate.TextColor

    val textSecondary
        @Composable get() = if (!isSystemInDarkTheme()) BasicColorPalate.TextSecondary else BasicColorPalate.LightSubtle

    val textDisabled
        @Composable get() = BasicColorPalate.TextDisabled

    val textPositive
        @Composable get() = BasicColorPalate.TextPositive

    val textAlert
        @Composable get() = BasicColorPalate.TextAlert

    val textWarning
        @Composable get() = BasicColorPalate.TextWarning

    val textInfo
        @Composable get() = BasicColorPalate.TextInfo

    val primary
        @Composable get() = BasicColorPalate.GreenPrimary

    val primaryDark
        @Composable get() = BasicColorPalate.GreenSecondary

    val darkPrimary
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)

    val darkSecondary
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF434955) else Color(0xFFEEEEEE)

    val darkSemiTransparent
        @Composable get() = if (!isSystemInDarkTheme()) Color(0x47000000) else Color(0xB3FFFFFF)

    val darkLight
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF797E86) else Color(0xFF797E86)

    val darkLightSemiTransparent
        @Composable get() = if (!isSystemInDarkTheme()) Color(0x80797E86) else Color(0x80797E86)

    val darkSubtle
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xffafb0c8) else Color(0xfff8f9f9)

    val lightPrimary
        @Composable get() = if (!isSystemInDarkTheme()) BasicColorPalate.LightPrimary else BasicColorPalate.DarkLightPrimary

    val lightSecondary
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xffd4d5e1) else Color(0xFF434955)

    val lightSecondaryStatic
        @Composable get() = Color(0xffd4d5e1)

    val lightSemiTransparent
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xB3FFFFFF) else Color(0x47000000)

    val lightSubtle
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xfff8f9f9) else Color(0xFF303030)

    val lightSecondaryDark
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xffd4d5e1) else Color(0xff434955)

    val lightDark
        @Composable get() = if (!isSystemInDarkTheme()) greyPrimary else Color(0xFFA5ACC0)

    val greenPrimary
        @Composable get() = Color(0xFF82E180)

    val white
        @Composable get() = Color(0xFFFFFFFF)

    val greenSecondary
        @Composable get() = Color(0xff42b67b)

    val greenSecondarySemiTransparent
        @Composable get() = Color(0x8042b67b)

    val greenLight
        @Composable get() = Color(0xffaae37a)

    val greenSubtle
        @Composable get() = Color(0xFFF0FAF4)

    val greenBlueSemiTransparent
        @Composable get() = Color(0x1F2CFFCD)

    val bluePrimary
        @Composable get() = Color(0xff4285f4)

    val blueLight
        @Composable get() = Color(0xff2face2)

    val greyPrimary
        @Composable get() = Color(0xFFB2B4B6)

    val pinkPrimary
        @Composable get() = Color(0xFFFF5F72)

    val pinkSecondary
        @Composable get() = Color(0xffe63869)

    val OrangePrimary
        @Composable get() = Color(0xFFF97C22)

    val yellowPrimary
        @Composable get() = Color(0xFFEDB219)
    val gold
        @Composable get() = Color(0xFFFABB0A)
    val goldSecondary
        @Composable get() = Color(0xFFFDDC11)
    val silver
        @Composable get() = Color(0xFF878DBB)
    val silverSecondary
        @Composable get() = Color(0xFFBFC7CB)
    val bronze
        @Composable get() = Color(0xFFD47D62)
    val bronzeSecondary
        @Composable get() = Color(0xFFED9D77)

    val colorTransparent
        @Composable get() = Color(0x00FFFFFF)

    val naturalDark1
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFFB8C2CC) else Color(0xFFA5ACC0)

    val neutralGrey1
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF82868B) else Color(0xFFEEEEEE)

    val neutralGrey2
        @Composable get() = Color(0xFFBFC5CC)

    val neutralGrey3
        @Composable get() = Color(0xFFD8D6DE)

    val neutralGrey4
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xffEDEDED) else Color(0xFF434955)

    val neutralGrey5
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFFF8F8F8) else Color(0xFF313131)

    val accent
        @Composable get() = blueLight

    val matterhorn
        @Composable get() = Color(0xFF4B4B4B)

    val alertInfo
        @Composable get() = Color(0xFF00CFE8)

    val alertWarning
        @Composable get() = Color(0xFFFF9F43)

    val alertSuccess
        @Composable get() = Color(0xFF28C76F)

    val transparentOrange
        @Composable get() = Color(0xFD7E1426)

    val lightSubtleVibrant
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFFEFEFEF) else Color(0xFF4B4B4B)

    val neutralDark3
        @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF626262) else Color(0xfff8f9f9)

    val mainGradient
        @Composable get() = listOf(
            greenSecondary,
            greenLight,
        )
}
