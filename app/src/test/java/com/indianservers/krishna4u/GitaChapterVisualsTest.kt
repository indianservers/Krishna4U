package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.gita.chapterBackground
import com.indianservers.krishna4u.feature.gita.chapterIcon
import com.indianservers.krishna4u.feature.gita.chapterIllustration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitaChapterVisualsTest {
    @Test
    fun everyChapterHasItsOwnSemanticIcon() {
        val icons = (1..18).map(::chapterIcon)
        assertEquals(18, icons.distinct().size)
    }

    @Test
    fun everyChapterHasAThemeIllustrationAndBackground() {
        assertTrue((1..18).all { chapterIllustration(it) != 0 })
        assertTrue((1..18).all { chapterBackground(it) != 0 })
    }
}
