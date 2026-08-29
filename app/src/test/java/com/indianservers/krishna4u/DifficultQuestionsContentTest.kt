package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.difficultquestions.difficultKrishnaQuestions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DifficultQuestionsContentTest {
    @Test
    fun questionsPreserveContextTensionAndMultipleReadings() {
        assertEquals(12, difficultKrishnaQuestions.size)
        assertEquals(12, difficultKrishnaQuestions.map { it.id }.distinct().size)
        assertEquals(12, difficultKrishnaQuestions.map { it.question }.distinct().size)
        assertTrue(difficultKrishnaQuestions.map { it.theme }.toSet().containsAll(setOf("War & Peace", "Strategy", "Punishment", "Divine Play", "Mahabharata Ethics")))
        difficultKrishnaQuestions.forEach { item ->
            assertTrue(item.question.endsWith("?"))
            assertTrue(item.context.split(Regex("\\s+")).size >= 25)
            assertTrue(item.ethicalTension.split(Regex("\\s+")).size >= 20)
            assertTrue(item.readings.size >= 3)
            assertTrue(item.readings.distinct().size == item.readings.size)
            assertTrue(item.doNotConclude.isNotBlank())
            assertTrue(item.reflection.endsWith("?"))
            assertTrue(item.sourceNote.isNotBlank())
        }
    }
}
