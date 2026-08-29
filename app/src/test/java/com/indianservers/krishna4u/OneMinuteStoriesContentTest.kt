package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.oneminute.oneMinuteKrishnaStories
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OneMinuteStoriesContentTest {
    @Test
    fun collectionContainsExactly108CompleteUniqueStories() {
        assertEquals((1..108).toList(), oneMinuteKrishnaStories.map { it.number })
        assertEquals(108, oneMinuteKrishnaStories.map { it.id }.distinct().size)
        assertEquals(108, oneMinuteKrishnaStories.map { it.title }.distinct().size)
        assertEquals(108, oneMinuteKrishnaStories.map { it.story }.distinct().size)
        assertEquals(12, oneMinuteKrishnaStories.map { it.theme }.distinct().size)
        oneMinuteKrishnaStories.forEach { story ->
            assertTrue(story.story.split(Regex("\\s+")).size in 20..95)
            assertTrue(story.moral.isNotBlank())
            assertTrue(story.dharmaTakeaway.split(Regex("\\s+")).size >= 8)
            assertTrue(story.action.isNotBlank())
            assertTrue(Regex("[.!?][”\\\"]?$").containsMatchIn(story.moral))
            assertTrue(Regex("[.!?][”\\\"]?$").containsMatchIn(story.dharmaTakeaway))
            assertTrue(Regex("[.!?][”\\\"]?$").containsMatchIn(story.action))
        }
    }
}
