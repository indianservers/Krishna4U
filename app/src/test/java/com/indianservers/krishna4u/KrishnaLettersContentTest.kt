package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.letters.krishnaLetters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KrishnaLettersContentTest {
    @Test
    fun lettersCoverRequestedSituationsWithSubstantialUniqueMessages() {
        assertEquals(listOf("failure", "loneliness", "grief", "rejection", "guilt", "uncertainty"), krishnaLetters.map { it.id })
        assertEquals(krishnaLetters.size, krishnaLetters.map { it.title }.distinct().size)
        krishnaLetters.forEach { letter ->
            assertEquals(4, letter.paragraphs.size)
            assertTrue(letter.paragraphs.joinToString(" ").split(Regex("\\s+")).size >= 180)
            assertTrue(letter.reflection.endsWith("?"))
            assertTrue(letter.nextStep.isNotBlank())
            assertTrue(letter.spokenText("Sai").startsWith("My dear Sai"))
        }
    }
}
