package com.indianservers.krishna4u

import com.indianservers.krishna4u.feature.letters.krishnaLetters
import com.indianservers.krishna4u.feature.letters.krishnaLetterAudiences
import com.indianservers.krishna4u.feature.letters.localizedKrishnaLetters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KrishnaLettersContentTest {
    @Test
    fun everyLetterHasCompleteLocalTeluguAndHindiContent() {
        val telugu = localizedKrishnaLetters("te")
        val hindi = localizedKrishnaLetters("hi")
        assertEquals(krishnaLetters.size, telugu.size)
        assertEquals(krishnaLetters.size, hindi.size)
        assertEquals(krishnaLetters.map { it.id }, telugu.map { it.source.id })
        assertEquals(krishnaLetters.map { it.id }, hindi.map { it.source.id })

        listOf(telugu to Regex("[\\u0C00-\\u0C7F]"), hindi to Regex("[\\u0900-\\u097F]")).forEach { (letters, script) ->
            letters.forEach { letter ->
                val fields = listOf(letter.situation, letter.title, letter.preview) + letter.paragraphs +
                    listOf(letter.reflection, letter.nextStep)
                assertEquals("${letter.source.id} must have four translated paragraphs", 4, letter.paragraphs.size)
                assertTrue("${letter.source.id} has a blank translation", fields.all { it.isNotBlank() })
                assertTrue("${letter.source.id} is missing the selected script", fields.all { script.containsMatchIn(it) })
                assertTrue("${letter.source.id} leaked a generator marker", fields.none { "[KFY" in it || "\\u0" in it })
            }
        }

        assertEquals(krishnaLetters.map { it.title }, localizedKrishnaLetters("en").map { it.title })
        assertTrue(telugu.first().personalizedParagraphs("సాయి").first().startsWith("నా ప్రియమైన సాయి,"))
        assertTrue(hindi.first().personalizedParagraphs("साई").first().startsWith("मेरे प्रिय साई,"))
    }

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
                "grief-returns", "leader-decision", "faith-dry", "moving-on",
                "comfort-growth", "happiness-before-duty", "pain-strength", "smart-work",
                "someone-only-takes", "presence-costs-peace", "returning-to-hurt", "friendship-changed",
                "outgrown-place", "guilty-choosing-self", "memories-pull-back", "closure-never-came",
                "love-trust-gone", "beginning-betrays-past",
                "rise-again", "stronger-than-moment", "life-purpose", "choose-courage", "ready-to-grow",
                "walk-dharma", "dream-discipline", "valuable-gift", "future-still-open", "next-step-strength"
            ),
            krishnaLetters.map { it.id }
        )
        assertEquals(89, krishnaLetters.size)
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
                "When Grief Returns Unexpectedly", "When Others Depend on Your Decision", "When Your Faith Feels Dry",
                "When It Is Time to Move On", "When Comfort Is Keeping You Small",
                "When You Are Waiting to Feel Happy Before You Begin",
                "When Pain Makes You Question Your Strength", "When Hard Work Is Not Giving Results",
                "When Someone Only Comes to Take", "When Their Presence Costs You Peace",
                "When You Keep Returning to What Hurt You", "When a Friendship Is No Longer the Same",
                "When You Have Outgrown a Place", "When You Feel Guilty for Choosing Yourself",
                "When Memories Keep Pulling You Back", "When the Closure You Wanted Never Came",
                "When Love Remains but Trust Is Gone", "When Beginning Again Feels Like Betraying the Past",
                "When You Are Ready to Rise Again", "When This Moment Feels Bigger Than You",
                "When You Want Your Life to Matter", "When Courage Is Calling You",
                "When You Are Ready to Grow", "When the Right Path Feels Lonely",
                "When Your Dream Needs Discipline", "When You Wonder What You Can Give",
                "When One Result Makes Your Future Look Closed", "When You Need Strength to Take the Next Step"
            ),
            krishnaLetters.map { it.situation }
        )
        assertEquals(krishnaLetters.size, krishnaLetters.map { it.title }.distinct().size)
        assertEquals(krishnaLetters.size, krishnaLetters.map { it.paragraphs.last() }.distinct().size)
        val shortLetters = krishnaLetters.filter {
            it.readingParagraphs.joinToString(" ").split(Regex("\\s+")).size < 130
        }.map { it.id }
        assertTrue("Concise letters should still contain at least 130 words: $shortLetters", shortLetters.isEmpty())
        krishnaLetters.forEach { letter ->
            assertEquals(4, letter.paragraphs.size)
            assertEquals(4, letter.readingParagraphs.size)
            assertTrue(
                "${letter.id} should be lightly shorter for readers",
                letter.readingParagraphs.joinToString(" ").length < letter.paragraphs.joinToString(" ").length
            )
            assertTrue(letter.reflection.endsWith("?"))
            assertTrue(letter.nextStep.isNotBlank())
            assertTrue(letter.spokenText("Sai").startsWith("My dear Sai"))
            assertTrue(letter.personalizedReadingParagraphs("Sai").first().startsWith("My dear Sai,"))
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
