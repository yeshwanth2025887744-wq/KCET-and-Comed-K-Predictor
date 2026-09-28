package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SolarOrange,
    onPrimary = Color.Black,
    primaryContainer = SolarOrangeContainerDark,
    onPrimaryContainer = Color(0xFFFFCCBC),
    secondary = SaffronYellow,
    onSecondary = Color.Black,
    secondaryContainer = YellowContainerDark,
    onSecondaryContainer = Color(0xFFFFECB3),
    tertiary = LaserCrimson,
    onTertiary = Color.White,
    tertiaryContainer = CrimsonContainerDark,
    onTertiaryContainer = Color(0xFFFFCDD2),
    background = SurfaceDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceCardDark,
    onSurface = TextPrimaryDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark,
    outlineVariant = BorderSubtleDark
)

private val LightColorScheme = lightColorScheme(
    primary = DeepSolarOrange,
    onPrimary = Color.White,
    primaryContainer = SolarOrangeContainerLight,
    onPrimaryContainer = DeepSolarOrange,
    secondary = SaffronGold,
    onSecondary = Color.Black,
    secondaryContainer = YellowContainerLight,
    onSecondaryContainer = Color(0xFF664D03),
    tertiary = LaserCrimson,
    onTertiary = Color.White,
    tertiaryContainer = CrimsonContainerLight,
    onTertiaryContainer = Color(0xFF5C0011),
    background = SurfaceLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    surfaceVariant = SurfaceCardLight,
    onSurface = TextPrimaryLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = BorderLight,
    outlineVariant = BorderSubtleLight
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


