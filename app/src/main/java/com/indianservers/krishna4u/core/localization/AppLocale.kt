package com.indianservers.krishna4u.core.localization

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

/**
 * Supplies language-specific Android resources without changing or rewriting the English source.
 * Any missing Telugu resource automatically falls back to res/values/strings.xml.
 */
@Composable
fun ProvideAppLanguage(languageCode: String, content: @Composable () -> Unit) {
    val baseContext = LocalContext.current
    val baseConfiguration = LocalConfiguration.current
    val supportedCode = supportedLanguageCode(languageCode)
    val localizedContext = remember(baseContext, baseConfiguration, supportedCode) {
        val configuration = Configuration(baseConfiguration).apply {
            setLocale(Locale.forLanguageTag(supportedCode))
        }
        baseContext.createConfigurationContext(configuration)
    }
    CompositionLocalProvider(
        LocalContext provides localizedContext,
        LocalConfiguration provides localizedContext.resources.configuration,
        content = content
    )
}
