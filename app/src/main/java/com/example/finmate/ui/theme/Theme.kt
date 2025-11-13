package com.example.finmate.ui.theme

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

// Palet Warna Gelap (Dark Mode)
private val DarkColorScheme = darkColorScheme(
    primary = AppYellowDark,
    onPrimary = AppDarkText,
    primaryContainer = AppYellow.copy(alpha = 0.2f),
    onPrimaryContainer = AppDarkText,
    secondary = BlueShopping,
    onSecondary = Color.White,
    background = AppDarkBackground,
    onBackground = Color.White,
    surface = AppDarkSurface,
    onSurface = Color.White
)

// Palet Warna Terang (Light Mode) - INI YANG UTAMA
private val LightColorScheme = lightColorScheme(
    primary = AppYellow,                // Kuning Anda sekarang adalah 'primary'
    onPrimary = AppDarkText,            // Teks di atas 'primary' (kontras)
    primaryContainer = AppYellow.copy(alpha = 0.2f), // Untuk chip tanggal
    onPrimaryContainer = AppDarkText,   // Teks di atas chip
    secondary = BlueShopping,           // Warna aksen (opsional)
    onSecondary = Color.White,          // Teks di atas aksen
    background = AppScreenBackground,   // Background abu-abu
    onBackground = AppDarkText,         // Teks di atas background
    surface = AppCardBackground,        // Background Card (putih)
    onSurface = AppDarkText             // Teks di atas Card
)

@Composable
fun FinMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color di-nonaktifkan agar tema kustom kita yang dipakai
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}