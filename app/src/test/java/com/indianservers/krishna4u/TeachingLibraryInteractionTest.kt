package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.teachings.teachingLibrary
import com.indianservers.krishna4u.feature.teachings.teachingReadMinutes
import org.junit.Assert.assertTrue
import org.junit.Test

class TeachingLibraryInteractionTest {
    @Test
    fun everyTeachingHasAUsefulReadingTime() {
        assertTrue(teachingLibrary.all { teachingReadMinutes(it) >= 3 })
    }
}
