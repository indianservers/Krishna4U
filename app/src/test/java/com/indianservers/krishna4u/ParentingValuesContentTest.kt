package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.parenting.parentingValuesSessions
import com.indianservers.krishna4u.feature.parenting.parentingTodayIssues
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ParentingValuesContentTest {
    @Test
    fun sessionsCoverFamilyValuesAndParentTeenSituationsWithCompleteMaterial() {
        assertEquals(37, parentingValuesSessions.size)
        assertEquals(37, parentingValuesSessions.map { it.id }.distinct().size)
        assertEquals(37, parentingValuesSessions.map { it.title }.distinct().size)
        val familySessions = parentingValuesSessions.filterNot { it.teenFocused }
        val teenSessions = parentingValuesSessions.filter { it.teenFocused }
        assertEquals(27, familySessions.size)
        assertEquals(10, teenSessions.size)
        val originalFamilySessions = parentingValuesSessions.take(12)
        assertEquals(setOf("Honesty", "Responsibility", "Compassion", "Courage"), originalFamilySessions.map { it.value }.toSet())
        originalFamilySessions.groupBy { it.value }.values.forEach { assertEquals(3, it.size) }
        assertEquals(
            listOf(
                "teen-privacy-secrecy", "teen-friendships-relationships", "teen-social-media",
                "teen-career-pressure", "teen-marks-comparison", "teen-independence",
                "teen-late-phone", "teen-consent-safety", "teen-substance-pressure",
                "teen-respectful-disagreement"
            ),
            teenSessions.map { it.id }
        )
        assertEquals(
            listOf(
                "Respect", "Gratitude", "Self-control", "Patience", "Discipline", "Friendship",
                "Forgiveness", "Humility", "Digital responsibility", "Money and contentment",
                "Care for elders", "Care for animals and nature", "Handling failure",
                "Healthy boundaries", "Service and generosity"
            ),
            parentingValuesSessions.takeLast(15).map { it.value }
        )
        parentingValuesSessions.forEach { session ->
            assertTrue(session.story.split(Regex("\\s+")).size >= 30)
            assertEquals(4, session.conversationPrompts.size)
            assertEquals(3, session.activitySteps.size)
            assertTrue(session.conversationPrompts.all { it.endsWith("?") })
            assertTrue(session.parentNote.isNotBlank())
            assertTrue(session.kidsTip.isNotBlank())
            assertTrue(session.teensTip.isNotBlank())
            assertEquals(3, session.rolePlaySteps().size)
            assertTrue(session.actionForThisWeek().isNotBlank())
        }
    }

    @Test
    fun whatHappenedTodayOffersCompleteGuidanceAndRealSessionRoutes() {
        assertEquals(
            listOf(
                "child-lied", "not-studying", "disrespect", "siblings-fighting", "screen-addiction",
                "bullying", "exam-fear", "refuses-responsibility", "jealous", "serious-mistake"
            ),
            parentingTodayIssues.map { it.id }
        )
        val sessionIds = parentingValuesSessions.mapTo(mutableSetOf()) { it.id }
        parentingTodayIssues.forEach { issue ->
            assertTrue(issue.parentFirst.isNotBlank())
            assertTrue(issue.sayThis.isNotBlank())
            assertTrue(issue.avoidThis.isNotBlank())
            assertTrue(issue.firstStep.isNotBlank())
            assertTrue(issue.recommendedSessionId in sessionIds)
        }
    }
}
