package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.parenting.parentingValuesSessions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ParentingValuesContentTest {
    @Test
    fun sessionsCoverFourValuesWithCompleteFamilyMaterial() {
        assertEquals(12, parentingValuesSessions.size)
        assertEquals(setOf("Honesty", "Responsibility", "Compassion", "Courage"), parentingValuesSessions.map { it.value }.toSet())
        assertEquals(12, parentingValuesSessions.map { it.id }.distinct().size)
        assertEquals(12, parentingValuesSessions.map { it.title }.distinct().size)
        parentingValuesSessions.groupBy { it.value }.values.forEach { assertEquals(3, it.size) }
        parentingValuesSessions.forEach { session ->
            assertTrue(session.story.split(Regex("\\s+")).size >= 30)
            assertEquals(4, session.conversationPrompts.size)
            assertEquals(3, session.activitySteps.size)
            assertTrue(session.conversationPrompts.all { it.endsWith("?") })
            assertTrue(session.parentNote.isNotBlank())
            assertTrue(session.kidsTip.isNotBlank())
            assertTrue(session.teensTip.isNotBlank())
        }
    }
}
