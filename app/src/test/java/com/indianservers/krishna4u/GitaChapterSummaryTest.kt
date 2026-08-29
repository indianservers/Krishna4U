package com.indianservers.krishna4u

import com.indianservers.krishna4u.data.repository.gitaChapters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitaChapterSummaryTest {
    @Test
    fun everyChapterHasAOneHundredToTwoHundredWordSummary() {
        assertEquals(18, gitaChapters.size)
        gitaChapters.forEach { chapter ->
            val wordCount = chapter.summary.trim().split(Regex("\\s+")).size
            assertTrue("Chapter ${chapter.number} has only $wordCount words", wordCount >= 100)
            assertTrue("Chapter ${chapter.number} has $wordCount words", wordCount <= 200)
        }
    }
}
