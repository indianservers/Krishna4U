package com.indianservers.krishna4u

import com.indianservers.krishna4u.data.repository.gitaChapterTakeaways
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitaChapterTakeawaysTest {
    @Test
    fun everyChapterHasFiveToEightDistinctTakeaways() {
        assertEquals((1..18).toSet(), gitaChapterTakeaways.keys)
        gitaChapterTakeaways.forEach { (_, takeaways) ->
            assertTrue(takeaways.size in 5..8)
            assertEquals(takeaways.size, takeaways.distinct().size)
            assertTrue(takeaways.all { it.length >= 35 })
        }
    }
}
