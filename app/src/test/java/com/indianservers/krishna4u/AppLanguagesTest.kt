package com.indianservers.krishna4u

import com.indianservers.krishna4u.core.localization.appLanguages
import com.indianservers.krishna4u.core.localization.supportedLanguageCode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppLanguagesTest {
    @Test
    fun englishTeluguAndHindiAreAvailableLaunchLanguages() {
        assertEquals(listOf("en", "hi", "te"), appLanguages.filter { it.available }.map { it.code })
        assertTrue(appLanguages.any { it.code == "es" })
        assertTrue(appLanguages.any { it.code == "ar" })
        assertTrue(appLanguages.any { it.code == "ta" })
        assertTrue(appLanguages.any { it.code == "kn" })
    }

    @Test
    fun legacyAndUnsupportedSelectionsSafelyResolveToEnglish() {
        assertEquals("en", supportedLanguageCode("English"))
        assertEquals("te", supportedLanguageCode("తెలుగు"))
        assertEquals("te", supportedLanguageCode("Telugu"))
        assertEquals("hi", supportedLanguageCode("हिन्दी"))
        assertEquals("hi", supportedLanguageCode("Hindi"))
        assertEquals("en", supportedLanguageCode("fr"))
        assertEquals("en", supportedLanguageCode("தமிழ்"))
        assertEquals("en", supportedLanguageCode("ಕನ್ನಡ"))
    }
}
