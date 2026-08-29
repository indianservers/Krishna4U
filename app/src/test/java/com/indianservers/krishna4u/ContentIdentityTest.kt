package com.indianservers.krishna4u

import com.indianservers.krishna4u.data.repository.gitaChapters
import com.indianservers.krishna4u.feature.krishnalife.krishnaLifeEvents
import com.indianservers.krishna4u.feature.teachings.teachingLibrary
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentIdentityTest {
    @Test
    fun everyGitaChapterHasUniqueIdentityAndContent() {
        assertEquals((1..18).toList(), gitaChapters.map { it.number })
        assertEquals(18, gitaChapters.map { it.title }.distinct().size)
        assertEquals(18, gitaChapters.map { it.theme }.distinct().size)
        assertEquals(18, gitaChapters.map { it.summary }.distinct().size)
    }

    @Test
    fun everyTeachingCardHasItsOwnDetailContent() {
        assertEquals(91, teachingLibrary.size)
        assertEquals(teachingLibrary.size, teachingLibrary.map { it.id }.distinct().size)
        assertEquals(teachingLibrary.size, teachingLibrary.map { it.title }.distinct().size)
        assertEquals(teachingLibrary.size, teachingLibrary.map { it.teaching }.distinct().size)
        assertEquals(7, teachingLibrary.count { it.category == "Compassion" })
        assertEquals(18, teachingLibrary.count { it.category == "Gita Wisdom" })
        assertEquals(18, teachingLibrary.count { it.category == "Daily Dharma" })
        assertEquals(48, teachingLibrary.count { it.category == "Krishna’s Guidance" })
        teachingLibrary.forEach { teaching ->
            assertTrue(teaching.question.endsWith("?"))
            assertTrue(teaching.takeaways.size in 4..6)
            assertTrue(teaching.practice.isNotBlank())
        }
    }

    @Test
    fun gitaTeachingTakeawaysAreNotChapterSummaryCopies() {
        val chapterSummaries = gitaChapters.map { it.summary.trim().lowercase() }.toSet()
        teachingLibrary.filter { it.category == "Gita Wisdom" }.forEach { teaching ->
            assertTrue(teaching.summary.trim().lowercase() !in chapterSummaries)
            assertTrue(teaching.teaching.trim().lowercase() !in chapterSummaries)
        }
    }

    @Test
    fun everyKrishnaLifeCardHasItsOwnDestinationAndStory() {
        assertEquals(34, krishnaLifeEvents.size)
        assertEquals(krishnaLifeEvents.size, krishnaLifeEvents.map { it.id }.distinct().size)
        assertEquals(krishnaLifeEvents.size, krishnaLifeEvents.map { it.title }.distinct().size)
        assertEquals(krishnaLifeEvents.size, krishnaLifeEvents.map { it.story }.distinct().size)
        krishnaLifeEvents.forEach { event ->
            assertTrue(event.openingQuestion.isNotBlank())
            assertTrue(event.choicePrompt.isNotBlank())
            assertEquals(4, event.storyText.split("\n\n").size)
            assertTrue(event.storyText.length > event.familyStoryText.length)
            assertTrue(event.takeaways.size in 5..7)
            assertTrue(event.familyStoryText.split("\n\n").size in 2..3)
            assertEquals(3, event.familyDiscussionQuestions.size)
        }
    }
}
