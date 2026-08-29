package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.decisions.dharmaDecisionStories
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DharmaDecisionContentTest {
    @Test
    fun dilemmasAreCompleteAndOfferOneDharmaAlignedDirection() {
        assertEquals(12, dharmaDecisionStories.size)
        assertEquals(12, dharmaDecisionStories.map { it.id }.distinct().size)
        assertEquals(12, dharmaDecisionStories.map { it.title }.distinct().size)
        assertTrue(dharmaDecisionStories.map { it.id }.containsAll(listOf("exam-cheating", "bullying", "peer-pressure", "hide-mistake")))
        dharmaDecisionStories.forEach { dilemma ->
            assertEquals(3, dilemma.choices.size)
            assertEquals(1, dilemma.choices.count { it.dharmaAligned })
            assertTrue(dilemma.question.endsWith("?"))
            assertTrue(dilemma.choices.all { it.text.isNotBlank() && it.consequence.isNotBlank() })
            assertTrue(dilemma.krishnaGuidance.isNotBlank())
            assertTrue(dilemma.principle.isNotBlank())
            assertTrue(dilemma.action.isNotBlank())
        }
    }
}
