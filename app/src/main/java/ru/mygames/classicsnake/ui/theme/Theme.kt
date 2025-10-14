package ru.mygames.classicsnake.ui.theme

import android.R.id.primary
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = darkPrimaryColor,
    secondary = darkSecondaryColor,
    primaryContainer = darkPrimaryColor,
    secondaryContainer = darkSecondaryColor,
    tertiaryContainer = darkTertiaryColor,
    onPrimaryContainer = darkOnPrimaryColor,
    onSecondaryContainer = darkOnSecondaryColor,
    onTertiaryContainer = darkOnTertiaryColor,
    background = darkBackgroundColor,
    surface = darkSurfaceColor
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimaryColor,
    secondary = lightSecondaryColor,
    primaryContainer = lightPrimaryColor,
    secondaryContainer = lightOnSurfaceColor,
    tertiaryContainer = lightTertiaryColor,
    onPrimaryContainer = lightOnPrimaryColor,
    onSecondaryContainer = lightOnSecondaryColor,
    onTertiaryContainer = lightOnTertiaryColor,
    background = lightBackgroundColor,
    surface = lightSurfaceColor

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ClassicSnakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}