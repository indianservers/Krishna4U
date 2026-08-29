package com.indianservers.krishna4u.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

private val SacredColors = darkColorScheme(
    primary = AntiqueGold, onPrimary = CosmicMidnight,
    secondary = CelestialCyan, background = CosmicMidnight,
    surface = DeepIndigo, onBackground = SoftWhite,
    onSurface = SoftWhite, outline = WarmGold
)

private val SacredTwilightColors = darkColorScheme(
    primary = LightGold, onPrimary = CosmicMidnight,
    secondary = CelestialCyan, background = Color(0xFF13254D),
    surface = Color(0xFF173568), onBackground = SoftWhite,
    onSurface = SoftWhite, outline = LightGold
)

val LocalSacredDarkTheme = staticCompositionLocalOf { true }
val LocalReducedMotion = staticCompositionLocalOf { false }

private fun sacredTypography(size: String): androidx.compose.material3.Typography {
    val scale = when (size) {
        "Large" -> 1.18f
        "Compact" -> .90f
        else -> 1f
    }
    return SacredTypography.copy(
        displayLarge = SacredTypography.displayLarge.copy(fontSize = (38 * scale).sp, lineHeight = (43 * scale).sp),
        headlineLarge = SacredTypography.headlineLarge.copy(fontSize = (30 * scale).sp, lineHeight = (36 * scale).sp),
        headlineMedium = SacredTypography.headlineMedium.copy(fontSize = (24 * scale).sp, lineHeight = (30 * scale).sp),
        titleLarge = SacredTypography.titleLarge.copy(fontSize = (20 * scale).sp, lineHeight = (25 * scale).sp),
        bodyLarge = SacredTypography.bodyLarge.copy(fontSize = (16 * scale).sp, lineHeight = (23 * scale).sp),
        bodyMedium = SacredTypography.bodyMedium.copy(fontSize = (14 * scale).sp, lineHeight = (20 * scale).sp),
        labelLarge = SacredTypography.labelLarge.copy(fontSize = (15 * scale).sp)
    )
}

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

@Composable
fun KrishnaPreferenceTheme(
    darkTheme: Boolean,
    textSize: String,
    reducedMotion: Boolean,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSacredDarkTheme provides darkTheme,
        LocalReducedMotion provides reducedMotion
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) SacredColors else SacredTwilightColors,
            typography = sacredTypography(textSize),
            content = content
        )
    }
}
