package com.indianservers.krishna4u.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val SacredColors = darkColorScheme(
    primary = AntiqueGold, onPrimary = CosmicMidnight,
    secondary = CelestialCyan, background = CosmicMidnight,
    surface = DeepIndigo, onBackground = SoftWhite,
    onSurface = SoftWhite, outline = WarmGold
)

@Composable
fun Krishna4UTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = CosmicMidnight.copy(alpha = .35f).toArgb()
        window.navigationBarColor = CosmicMidnight.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
    }
    MaterialTheme(colorScheme = SacredColors, typography = SacredTypography, content = content)
}
