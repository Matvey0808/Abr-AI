package com.example.abr_ai.ui.pages.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightTheme = lightColorScheme(
    primary = Color(0xFFC8E6C9),
    onPrimary = Color(0xFFE8F5E9),
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFE8F5E9),
    onSurface = Color.Black
)

private val DarkTheme = lightColorScheme(
    primary = Color(0xFF121212),
    onPrimary = Color(0xFF1E1E1E),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun AbrTheme(isDark: Boolean = false, content: @Composable () -> Unit) {
    val colorScheme = if (!isDark) LightTheme else DarkTheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}