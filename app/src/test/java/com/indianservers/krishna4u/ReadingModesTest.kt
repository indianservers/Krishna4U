package com.indianservers.krishna4u

import com.indianservers.krishna4u.core.localization.ageAppropriateAnswer
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.core.localization.readingModes
import com.indianservers.krishna4u.feature.krishnalife.krishnaLifeEvents
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReadingModesTest {
    @Test
    fun allAgeModesHaveDistinctProfiles() {
        assertEquals(listOf("kids", "teens", "adults"), readingModes.map { it.id })
        assertEquals("Teens", readingMode("unknown").title)
    }

    @Test
    fun lifeContentAdaptsToTheSelectedAge() {
        krishnaLifeEvents.forEach { event ->
            assertEquals(3, event.takeawaysFor("kids").size)
            assertEquals(5, event.takeawaysFor("teens").size)
            assertTrue(event.storyFor("kids").length <= event.storyFor("adults").length)
            assertEquals(3, event.familyQuestionsFor("kids").size)
            assertEquals(3, event.familyQuestionsFor("adults").size)
        }
    }

    @Test
    fun guidanceAnswersBecomeMoreDetailedWithAge() {
        val answer = "First idea. Second idea. Third idea. Fourth idea."
        assertEquals("First idea. Second idea.", ageAppropriateAnswer(answer, "kids"))
        assertEquals("First idea. Second idea. Third idea.", ageAppropriateAnswer(answer, "teens"))
        assertEquals(answer, ageAppropriateAnswer(answer, "adults"))
    }
}
