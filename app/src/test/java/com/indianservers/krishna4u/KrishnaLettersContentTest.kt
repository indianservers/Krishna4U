package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.letters.krishnaLetters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KrishnaLettersContentTest {
    @Test
    fun lettersCoverRequestedSituationsWithSubstantialUniqueMessages() {
        assertEquals(
            listOf(
                "failure", "loneliness", "grief", "rejection", "guilt", "uncertainty",
                "anxiety", "lost-hope", "heart-broken", "not-enough", "misunderstood", "future-fear",
                "life-unfair", "tired-strong", "self-forgiveness", "betrayal", "letting-go", "family-struggling",
                "stuck", "unanswered-prayers", "difficult-decision", "lost-purpose", "anger", "comparison",
                "change", "carrying-too-much", "forgotten", "financial-struggle", "health-failing", "begin-again",
                "happy", "grateful", "success", "proud", "excited", "peaceful", "loved", "hopeful",
                "relieved", "confident", "inspired", "belonging", "disappointed", "embarrassed", "bored", "homesick"
            ),
            krishnaLetters.map { it.id }
        )
        assertEquals(46, krishnaLetters.size)
        assertEquals(
            listOf(
                "When You Feel You Have Failed", "When Loneliness Feels Heavy", "When You Are Grieving",
                "When You Feel Rejected", "When Guilt Will Not Release You", "When the Path Is Uncertain",
                "When Anxiety Overwhelms You", "When You Have Lost Hope", "When Your Heart Is Broken",
                "When You Feel You Are Not Enough", "When Nobody Seems to Understand You",
                "When You Are Afraid of the Future", "When Life Feels Unfair", "When You Are Tired of Being Strong",
                "When You Cannot Forgive Yourself", "When Someone Has Betrayed You", "When You Must Let Someone Go",
                "When Your Family Is Struggling", "When You Feel Stuck in Life", "When Your Prayers Seem Unanswered",
                "When You Are Facing a Difficult Decision", "When You Have Lost Your Purpose",
                "When Anger Is Controlling You", "When You Keep Comparing Yourself", "When Change Feels Frightening",
                "When You Are Carrying Too Much", "When You Feel Forgotten", "When You Are Struggling Financially",
                "When Your Health Is Failing", "When You Need the Courage to Begin Again",
                "When You Feel Happy", "When You Feel Grateful", "When You Have Succeeded",
                "When You Feel Proud of Yourself", "When You Feel Excited", "When You Feel Peaceful",
                "When You Feel Deeply Loved", "When You Feel Hopeful", "When You Feel Relieved",
                "When You Feel Confident", "When You Feel Inspired", "When You Feel You Belong",
                "When You Feel Disappointed", "When You Feel Embarrassed", "When You Feel Bored or Restless",
                "When You Miss Home"
            ),
            krishnaLetters.map { it.situation }
        )
        assertEquals(krishnaLetters.size, krishnaLetters.map { it.title }.distinct().size)
        assertEquals(krishnaLetters.size, krishnaLetters.map { it.paragraphs.last() }.distinct().size)
        val shortLetters = krishnaLetters.filter {
            it.paragraphs.joinToString(" ").split(Regex("\\s+")).size < 180
        }.map { it.id }
        assertTrue("Letters should contain at least 180 words: $shortLetters", shortLetters.isEmpty())
        krishnaLetters.forEach { letter ->
            assertEquals(4, letter.paragraphs.size)
            assertTrue(letter.reflection.endsWith("?"))
            assertTrue(letter.nextStep.isNotBlank())
            assertTrue(letter.spokenText("Sai").startsWith("My dear Sai"))
        }
        val happiness = krishnaLetters.single { it.id == "happy" }
        val happinessText = happiness.paragraphs.joinToString(" ").lowercase()
        assertTrue(happinessText.contains("someone was hurt, failed, lost or was pushed down"))
        assertTrue(happinessText.contains("joy becomes kindness"))
    }
}
