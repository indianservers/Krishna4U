package com.indianservers.krishna4u.feature.wisdom

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

data class ExploreShortcut(
    val route: String,
    val title: String,
    val description: String,
    @param:DrawableRes val icon: Int
)

val exploreModuleShortcuts = listOf(
    ExploreShortcut("emotional_intelligence", "Krishna & Emotional Intelligence", "Understand feelings, calm reactions and resolve conflict.", R.drawable.icon_relationships),
    ExploreShortcut("emotion_wheel", "Emotion Wheel", "Find a letter, verse and calming action for what you feel.", R.drawable.icon_inner_peace),
    ExploreShortcut("collectibles", "Sacred Collectibles", "Awaken sacred symbols through real learning.", R.drawable.icon_chakra),
    ExploreShortcut("gita_concept_maps", "Gita Concept Maps", "See how attachment, knowledge, duty and devotion unfold.", R.drawable.icon_strategy),
    ExploreShortcut("evening_reflection", "Krishna’s Evening Reflection", "Review your day with truth, gratitude, repair and release.", R.drawable.icon_journal),
    ExploreShortcut("commitments", "My 18 Commitments to Krishna", "Renew 18 vows and practise them through daily action.", R.drawable.icon_check),
    ExploreShortcut("krishna_letters", "Krishna’s Letters to You", "A personal letter for what your heart is carrying.", R.drawable.icon_compassion),
    ExploreShortcut("one_minute_stories", "One-Minute Krishna Stories", "108 concise stories with one moral and action.", R.drawable.icon_flute),
    ExploreShortcut("dharma_decisions", "Dharma Decision Stories", "Choose through realistic dilemmas and reveal guidance.", R.drawable.icon_dharma),
    ExploreShortcut("difficult_questions", "Difficult Questions About Krishna", "Explore morally complex questions without easy answers.", R.drawable.icon_teachings),
    ExploreShortcut("parenting_values", "Parenting with Krishna’s Values", "Stories, prompts and activities for families.", R.drawable.icon_relationships),
    ExploreShortcut("10", "The Krishna Within", "Awaken wisdom, playfulness, courage and compassion.", R.drawable.icon_lotus),
    ExploreShortcut("21", "What Is Dharma?", "Understand truth, duty and courageous right action.", R.drawable.icon_dharma),
    ExploreShortcut("20", "Questions of the Heart", "Simple answers about dharma, suffering and the mind.", R.drawable.icon_ask_krishna),
    ExploreShortcut("23", "When Life Feels…", "Choose what you feel and receive a calm next step.", R.drawable.icon_mind),
    ExploreShortcut("24", "Today with Krishna", "Listen, understand, reflect and act each day.", R.drawable.icon_calendar),
    ExploreShortcut("19", "Gita Study Mode", "Study one teaching slowly and attentively.", R.drawable.icon_gita)
)

val allExploreShortcuts: List<ExploreShortcut>
    get() = exploreModuleShortcuts + wisdomThemes.map { theme ->
        ExploreShortcut(
            route = "wisdom_theme/${theme.id}",
            title = theme.title,
            description = "5 Bhagavad Gita teachings",
            icon = theme.icon
        )
    }

fun exploreShortcut(route: String): ExploreShortcut? = allExploreShortcuts.firstOrNull { it.route == route }
