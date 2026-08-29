package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.wisdom.wisdomThemes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WisdomThemeContentTest {
    @Test
    fun libraryContainsEighteenCompleteThemes() {
        assertEquals(18, wisdomThemes.size)
        assertEquals(18, wisdomThemes.map { it.id }.distinct().size)
        wisdomThemes.forEach { theme ->
            assertEquals("${theme.title} should have five teachings", 5, theme.teachings.size)
            assertTrue(theme.description.isNotBlank())
            assertTrue(theme.practice.isNotBlank())
        }
    }

    @Test
    fun everyRequestedCategoryBeginsWithItsImportantSlokas() {
        val expected = linkedMapOf(
            "Mind Control" to listOf(6 to 5, 6 to 26),
            "Dharma and Duty" to listOf(3 to 8, 18 to 47),
            "Leadership and Action" to listOf(3 to 21, 3 to 25),
            "Confidence and Inner Strength" to listOf(2 to 3, 2 to 14),
            "Compassion and Love" to listOf(12 to 15, 5 to 18),
            "Wisdom and Knowledge" to listOf(4 to 34, 4 to 38),
            "Overcoming Desires" to listOf(2 to 63, 3 to 37),
            "Unity with the Divine" to listOf(6 to 29, 9 to 4),
            "Fear and Anxiety" to listOf(2 to 14, 18 to 66),
            "Faith and Devotion" to listOf(12 to 6, 12 to 7, 9 to 34),
            "Equality and Oneness" to listOf(5 to 18, 13 to 27),
            "Forgiveness and Tolerance" to listOf(2 to 14, 12 to 13, 12 to 14),
            "Sacrifice and Renunciation" to listOf(3 to 9, 18 to 2),
            "Happiness and Contentment" to listOf(12 to 14, 18 to 37),
            "Discipline and Control" to listOf(6 to 16, 6 to 17, 6 to 26),
            "Attachment and Detachment" to listOf(2 to 48, 3 to 30),
            "Nature of the Soul" to listOf(2 to 17, 2 to 24),
            "Action and Inaction" to listOf(4 to 17, 18 to 23)
        )
        assertEquals(expected.keys.toList(), wisdomThemes.map { it.title })
        wisdomThemes.forEach { theme ->
            assertEquals(expected.getValue(theme.title), theme.teachings.take(expected.getValue(theme.title).size).map { it.chapter to it.verse })
        }
    }
}
