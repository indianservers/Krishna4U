package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.guidance.krishnaMessagesFor
import com.indianservers.krishna4u.feature.guidance.totalKrishnaComfortMessages
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KrishnaSpeaksContentTest {
    @Test
    fun libraryContainsMoreThanTwoHundredMessages() {
        assertEquals(204, totalKrishnaComfortMessages)
        assertTrue(totalKrishnaComfortMessages > 200)
    }

    @Test
    fun onboardingNeedSelectsMatchingMessages() {
        val distressed = krishnaMessagesFor(setOf("Distressed"))
        assertEquals(36, distressed.size)
        assertTrue(distressed.all { it.situation == "When You Feel Distressed" || it.situation == "When Everything Feels Too Much" })
    }

    @Test
    fun emptySelectionHasAComfortingFallback() {
        assertTrue(krishnaMessagesFor(emptySet()).isNotEmpty())
    }
}
