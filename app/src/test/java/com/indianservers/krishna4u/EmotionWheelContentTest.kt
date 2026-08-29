package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.emotions.emotionWheelItems
import com.indianservers.krishna4u.feature.letters.krishnaLetters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EmotionWheelContentTest {
    @Test
    fun everyEmotionConnectsToARealLetterVerseAndAction() {
        assertEquals(listOf("afraid", "angry", "lonely", "guilty", "confused"), emotionWheelItems.map { it.id })
        val letterIds = krishnaLetters.map { it.id }.toSet()
        emotionWheelItems.forEach { item ->
            assertTrue(item.letterId in letterIds)
            assertTrue(item.verse.startsWith("Bhagavad Gita"))
            assertTrue(item.verseMeaning.length >= 45)
            assertTrue(item.calmingAction.length >= 60)
        }
    }
}
