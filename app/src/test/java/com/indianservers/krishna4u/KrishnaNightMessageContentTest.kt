package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.night.krishnaNightMessages
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KrishnaNightMessageContentTest {
    @Test
    fun nightMessagesAreCalmDistinctAndActionable() {
        assertEquals(14, krishnaNightMessages.size)
        assertEquals(krishnaNightMessages.size, krishnaNightMessages.map { it.title }.distinct().size)
        assertEquals(krishnaNightMessages.size, krishnaNightMessages.map { it.message }.distinct().size)
        krishnaNightMessages.forEach { message ->
            assertTrue(message.message.split(Regex("\\s+")).size >= 25)
            assertTrue(message.release.startsWith("I release"))
        }
    }
}
