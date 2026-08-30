package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.letters.krishnaLetters
import com.indianservers.krishna4u.feature.letters.krishnaLetterAudiences
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
                "relieved", "confident", "inspired", "belonging", "disappointed", "embarrassed", "bored", "homesick",
                "child-truth", "left-out-school", "student-focus", "important-exam", "youth-peer-pressure",
                "identity-confusion", "parent-failing", "child-pulls-away", "work-taken-over", "work-unseen",
                "couple-arguments", "rebuilding-trust", "elder-invisible", "depending-on-others", "caring-for-ill",
                "grief-returns", "leader-decision", "faith-dry"
            ),
            krishnaLetters.map { it.id }
        )
        assertEquals(64, krishnaLetters.size)
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
                "When You Miss Home", "When You Are Afraid to Tell the Truth", "When You Feel Left Out at School",
                "When You Cannot Focus on Your Studies", "Before an Important Exam", "When Friends Pressure You",
                "When You Do Not Know Who You Are", "When You Feel You Are Failing as a Parent",
                "When Your Child Is Pulling Away", "When Work Has Taken Over Your Life",
                "When Your Work Is Not Recognised", "When Every Conversation Becomes an Argument",
                "When Trust Needs to Be Rebuilt", "When Age Makes You Feel Invisible",
                "When You Must Depend on Others", "When You Are Caring for Someone Who Is Ill",
                "When Grief Returns Unexpectedly", "When Others Depend on Your Decision", "When Your Faith Feels Dry"
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
            assertTrue("${letter.id} should have an audience", letter.audiences.isNotEmpty())
            assertTrue("${letter.id} has an unknown audience", letter.audiences.all { it in krishnaLetterAudiences })
        }
        krishnaLetterAudiences.forEach { audience ->
            assertTrue("Audience $audience should have letters", krishnaLetters.any { audience in it.audiences })
        }
        val happiness = krishnaLetters.single { it.id == "happy" }
        val happinessText = happiness.paragraphs.joinToString(" ").lowercase()
        assertTrue(happinessText.contains("someone was hurt, failed, lost or was pushed down"))
        assertTrue(happinessText.contains("joy becomes kindness"))

        listOf("failure", "uncertainty", "difficult-decision", "important-exam").forEach { id ->
            val guidanceText = krishnaLetters.single { it.id == id }.paragraphs.joinToString(" ").lowercase()
            assertTrue("$id should explain relevant guidance from a Guru or teacher", "guru" in guidanceText)
        }
        listOf("grateful", "success", "important-exam").forEach { id ->
            val gratitudeText = krishnaLetters.single { it.id == id }.paragraphs.joinToString(" ").lowercase()
            assertTrue("$id should encourage thanking helpers", "thank" in gratitudeText && "help" in gratitudeText)
        }
    }
}
