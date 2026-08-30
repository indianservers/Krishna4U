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
        assertEquals(
            listOf(
                "peacock-feather", "flute", "chakra", "lotus", "conch",
                "dharma-wheel", "sacred-tree", "guru-paduka", "tulsi-leaf", "truth-lamp",
                "guardian-shield", "arjuna-bow", "cooling-moon", "friendship-garland", "sacred-mirror",
                "open-palm", "seva-bowl", "temple-bell", "sacred-kalasha", "promise-thread"
            ),
            sacredCollectibles.map { it.id }
        )
        assertEquals(20, sacredCollectibles.map { it.name }.distinct().size)

        val empty = LearningActivity(emptySet(), emptySet(), emptySet())
        assertTrue(sacredCollectibles.none { it.earned(empty) })

        val learning = LearningActivity(
            bookmarks = setOf("message:hope", "life:govardhan"),
            reflections = setOf("1|A true reflection"),
            readSlokas = (1..18).map { "2.$it" }.toSet(),
            commitmentPractices = setOf(
                "2026-08-30:dharma", "2026-08-30:nature", "2026-08-30:guides",
                "2026-08-30:respect", "2026-08-30:truth", "2026-08-30:protect",
                "2026-08-30:duty", "2026-08-30:anger", "2026-08-30:friendship",
                "2026-08-30:mistakes", "2026-08-30:forgive", "2026-08-30:serve",
                "2026-08-30:health", "2026-08-30:resources", "2026-08-30:promises"
            )
        )
        assertTrue(sacredCollectibles.all { it.earned(learning) })
        sacredCollectibles.forEach {
            assertFalse(it.meaning.isBlank())
            assertTrue(it.requirement.isNotBlank())
        }
        sacredCollectibles.drop(5).forEach {
            assertEquals("commitments", it.earningRoute)
            assertTrue(it.requirement.startsWith("First achieve this in real life"))
        }
    }
}
