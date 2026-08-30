package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.evening.additionalEveningReflectionPrompts
import com.indianservers.krishna4u.feature.evening.allEveningReflectionPrompts
import com.indianservers.krishna4u.feature.evening.essentialEveningReflectionPrompts
import com.indianservers.krishna4u.feature.evening.eveningReflectionDeck
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EveningReflectionContentTest {
    @Test
    fun reflectionLibraryContainsSixEssentialAndFiftyAdditionalPrompts() {
        assertEquals(6, essentialEveningReflectionPrompts.size)
        assertEquals(50, additionalEveningReflectionPrompts.size)
        assertEquals(56, allEveningReflectionPrompts.size)
        assertEquals(56, allEveningReflectionPrompts.map { it.id }.distinct().size)
        assertEquals(56, allEveningReflectionPrompts.map { it.question }.distinct().size)
        assertTrue(essentialEveningReflectionPrompts.all { it.essential })
        assertTrue(additionalEveningReflectionPrompts.none { it.essential })
        assertTrue(allEveningReflectionPrompts.all { it.question.endsWith("?") && it.gentleCue.isNotBlank() })
    }

    @Test
    fun nightlyDeckAlwaysKeepsEssentialsAndAddsARepeatableRandomSelection() {
        val first = eveningReflectionDeck(seed = 108)
        val same = eveningReflectionDeck(seed = 108)
        val different = eveningReflectionDeck(seed = 109)

        assertEquals(12, first.size)
        assertEquals(essentialEveningReflectionPrompts, first.take(6))
        assertEquals(first, same)
        assertTrue(first.drop(6) != different.drop(6))
    }
}
