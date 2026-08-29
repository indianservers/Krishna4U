package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.home.isNightMessageTime
import java.time.LocalTime
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeNightMessageTimeTest {
    @Test
    fun `night message is hidden before 8 PM`() {
        assertFalse(isNightMessageTime(LocalTime.of(19, 59)))
    }

    @Test
    fun `night message appears from 8 PM until midnight`() {
        assertTrue(isNightMessageTime(LocalTime.of(20, 0)))
        assertTrue(isNightMessageTime(LocalTime.of(23, 59)))
    }

    @Test
    fun `night message hides again after midnight`() {
        assertFalse(isNightMessageTime(LocalTime.of(0, 0)))
        assertFalse(isNightMessageTime(LocalTime.of(7, 59)))
    }
}
