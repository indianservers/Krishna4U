package com.indianservers.krishna4u.feature.commitments

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CommitmentsContentTest {
    @Test
    fun `contains exactly 18 distinct commitments`() {
        assertEquals(18, krishnaCommitments.size)
        assertEquals(18, krishnaCommitments.map { it.id }.distinct().size)
    }

    @Test
    fun `health vow explicitly rejects harmful substances`() {
        val healthPromise = krishnaCommitments.single { it.id == "health" }.promise.lowercase()
        listOf("cigarettes", "tobacco", "alcohol", "drugs", "mind", "body").forEach { requiredWord ->
            assertTrue("Missing $requiredWord from the health commitment", requiredWord in healthPromise)
        }
    }
}
