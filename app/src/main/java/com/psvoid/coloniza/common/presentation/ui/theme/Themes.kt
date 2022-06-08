package com.psvoid.coloniza.common.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val MainLightColorScheme = lightColorScheme(
    primary = Colors.blue40,
    onPrimary = Colors.blue90,
    primaryContainer = Colors.blue80,
    onPrimaryContainer = Colors.blue10,
    inversePrimary = Colors.red50,
    secondary = Colors.azure40,
    onSecondary = Colors.azure90,
    secondaryContainer = Colors.azure80,
    onSecondaryContainer = Colors.azure10,
    tertiary = Colors.purple40,
    onTertiary = Colors.purple90,
    tertiaryContainer = Colors.purple80,
    onTertiaryContainer = Colors.purple10,
    background = Colors.red50,
    onBackground = Colors.red50,
    surface = Colors.red50,
    onSurface = Colors.red50,
    surfaceVariant = Colors.red50,
    onSurfaceVariant = Colors.red50,
    surfaceTint = Colors.red50,
    inverseSurface = Colors.red50,
    inverseOnSurface = Colors.red50,
    error = Colors.red50,
    onError = Colors.red50,
    errorContainer = Colors.red50,
    onErrorContainer = Colors.red50,
    outline = Colors.red50,
)

private val MainDarkColorScheme = darkColorScheme(
    primary = Colors.blue40,
    onPrimary = Colors.blue90,
    primaryContainer = Colors.blue80,
    onPrimaryContainer = Colors.blue10,
    inversePrimary = Colors.red50,
    secondary = Colors.azure40,
    onSecondary = Colors.azure90,
    secondaryContainer = Colors.azure80,
    onSecondaryContainer = Colors.azure10,
    tertiary = Colors.purple40,
    onTertiary = Colors.purple90,
    tertiaryContainer = Colors.purple80,
    onTertiaryContainer = Colors.purple10,
    background = Colors.red50,
    onBackground = Colors.red50,
    surface = Colors.red50,
    onSurface = Colors.red50,
    surfaceVariant = Colors.red50,
    onSurfaceVariant = Colors.red50,
    surfaceTint = Colors.red50,
    inverseSurface = Colors.red50,
    inverseOnSurface = Colors.red50,
    error = Colors.red50,
    onError = Colors.red50,
    errorContainer = Colors.red50,
    onErrorContainer = Colors.red50,
    outline = Colors.red50,
)

@Composable
fun MainTheme(
    isMainTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val myColorScheme = when {
        dynamicColor && isMainTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !isMainTheme -> dynamicLightColorScheme(LocalContext.current)
        isMainTheme -> MainDarkColorScheme
        else -> MainLightColorScheme
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = myColorScheme,
        typography = MainTypography
    ) {
        // TODO (M3): MaterialTheme doesn't provide LocalIndication, remove when it does
        val rippleIndication = rememberRipple()
        CompositionLocalProvider(
            LocalIndication provides rippleIndication,
            content = content
        )
    }
}
