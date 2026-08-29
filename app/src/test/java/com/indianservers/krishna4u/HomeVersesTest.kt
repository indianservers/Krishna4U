package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.home.homeVerses
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeVersesTest {
    @Test
    fun homeVerseCollectionContainsFortyCompleteUniqueEntries() {
        assertEquals(40, homeVerses.size)
        assertEquals(40, homeVerses.map { it.reference }.distinct().size)
        assertTrue(homeVerses.all { it.quote.isNotBlank() })
        assertTrue(homeVerses.all { it.chapter in 1..18 && it.verse > 0 })
    }
}
