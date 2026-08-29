package com.indianservers.krishna4u

import com.indianservers.krishna4u.core.localization.appLanguages
import com.indianservers.krishna4u.core.localization.supportedLanguageCode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppLanguagesTest {
    @Test
    fun englishIsTheOnlyAvailableLaunchLanguage() {
        assertEquals(listOf("en"), appLanguages.filter { it.available }.map { it.code })
        assertTrue(appLanguages.any { it.code == "es" })
        assertTrue(appLanguages.any { it.code == "ar" })
    }

    @Test
    fun legacyAndUnsupportedSelectionsSafelyResolveToEnglish() {
        assertEquals("en", supportedLanguageCode("English"))
        assertEquals("en", supportedLanguageCode("తెలుగు"))
        assertEquals("en", supportedLanguageCode("fr"))
    }
}
