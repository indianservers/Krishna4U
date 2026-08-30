package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.conceptmaps.gitaConceptMaps
import com.indianservers.krishna4u.feature.wisdom.exploreModuleShortcuts
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitaConceptMapsContentTest {
    @Test
    fun mapsPreserveTheRequestedIdeaSequencesAndCompleteGuidance() {
        assertEquals(24, gitaConceptMaps.size)
        assertEquals(
            listOf("attachment-confusion", "knowledge-equanimity", "duty-freedom", "devotion-peace"),
            gitaConceptMaps.take(4).map { it.id }
        )
        assertEquals(
            listOf(
                listOf("Attachment", "Desire", "Anger", "Confusion"),
                listOf("Knowledge", "Right Action", "Equanimity"),
                listOf("Duty", "Offering", "Freedom from Results"),
                listOf("Devotion", "Surrender", "Peace")
            ),
            gitaConceptMaps.take(4).map { map -> map.stages.map { it.title } }
        )
        assertEquals(gitaConceptMaps.size, gitaConceptMaps.map { it.id }.distinct().size)
        assertEquals(gitaConceptMaps.size, gitaConceptMaps.map { it.title }.distinct().size)
        gitaConceptMaps.forEach { map ->
            assertTrue(map.turningPoint.isNotBlank())
            assertTrue(map.practice.isNotBlank())
            assertTrue(map.verses.size >= 2)
            assertTrue(map.verses.all { it.route.startsWith("gita_verse/") })
            assertTrue(map.stages.all { it.explanation.isNotBlank() && it.signInLife.isNotBlank() })
            assertTrue(map.spokenText.contains("Practice today"))
        }
        assertTrue(exploreModuleShortcuts.any { it.route == "gita_concept_maps" })
    }
}
