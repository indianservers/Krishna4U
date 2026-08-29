package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.collectibles.LearningActivity
import com.indianservers.krishna4u.feature.collectibles.sacredCollectibles
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SacredCollectiblesTest {
    @Test
    fun collectiblesUseRealLocalLearningActivity() {
        assertEquals(listOf("peacock-feather", "flute", "chakra", "lotus", "conch"), sacredCollectibles.map { it.id })
        assertEquals(5, sacredCollectibles.map { it.name }.distinct().size)

        val empty = LearningActivity(emptySet(), emptySet(), emptySet())
        assertTrue(sacredCollectibles.none { it.earned(empty) })

        val learning = LearningActivity(
            bookmarks = setOf("message:hope", "life:govardhan"),
            reflections = setOf("1|A true reflection"),
            readSlokas = (1..18).map { "2.$it" }.toSet()
        )
        assertTrue(sacredCollectibles.all { it.earned(learning) })
        sacredCollectibles.forEach {
            assertFalse(it.meaning.isBlank())
            assertTrue(it.requirement.isNotBlank())
        }
    }
}
