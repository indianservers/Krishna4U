package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.emotions.emotionalIntelligenceLessons
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EmotionalIntelligenceContentTest {
    @Test
    fun moduleCoversFiveCoreEmotionalSkillsWithUniqueContent() {
        assertEquals(5, emotionalIntelligenceLessons.size)
        assertEquals(5, emotionalIntelligenceLessons.map { it.id }.distinct().size)
        assertEquals(5, emotionalIntelligenceLessons.map { it.title }.distinct().size)
        assertEquals(5, emotionalIntelligenceLessons.map { it.emotionalSkill }.distinct().size)
        emotionalIntelligenceLessons.forEach { lesson ->
            assertTrue(lesson.openingQuestion.endsWith("?"))
            assertTrue(lesson.steps.size in 4..6)
            assertTrue(lesson.kidsExample.isNotBlank())
            assertTrue(lesson.teensExample.isNotBlank())
            assertTrue(lesson.adultsExample.isNotBlank())
            assertTrue(lesson.practice.isNotBlank())
        }
    }
}
