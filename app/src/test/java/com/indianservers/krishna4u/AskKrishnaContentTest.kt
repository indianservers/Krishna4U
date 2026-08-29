package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.guidance.askKrishnaQuestions
import com.indianservers.krishna4u.feature.guidance.searchAskKrishnaQuestions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AskKrishnaContentTest {
    @Test
    fun askKrishnaContainsTheCompleteQuestionCollection() {
        assertEquals(40, askKrishnaQuestions.size)
        assertEquals(40, askKrishnaQuestions.map { it.id }.distinct().size)
        assertEquals(40, askKrishnaQuestions.map { it.question }.distinct().size)
        assertTrue(askKrishnaQuestions.all { it.question.isNotBlank() && it.answer.length > 80 })
    }

    @Test
    fun realLifePhrasesFindRelevantGuidance() {
        assertEquals(3, searchAskKrishnaQuestions("exam fear").first().id)
        assertTrue(searchAskKrishnaQuestions("anger with parents").take(3).any { it.id == 11 })
        assertEquals(36, searchAskKrishnaQuestions("friend betrayed me").first().id)
        assertTrue(searchAskKrishnaQuestions("I cannot focus").take(3).any { it.id == 14 })
    }
}
