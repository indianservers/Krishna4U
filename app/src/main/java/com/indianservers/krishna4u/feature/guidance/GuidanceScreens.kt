package com.indianservers.krishna4u.feature.guidance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.localization.ageAppropriateAnswer
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun FaqLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val faqs = listOf("What is dharma?" to "Dharma is right action aligned with truth, responsibility and the welfare of all.", "How do I know my duty?" to "Examine your role, motives and whom your action serves.", "Is detachment the same as not caring?" to "No. Detachment means caring deeply without demanding a particular result.", "Why do good people suffer?" to "The Gita invites compassionate action without simplistic judgment.", "How can I control the mind?" to "Through patient practice and non-attachment." )
    var expanded by remember { mutableIntStateOf(0) }
    FeatureScaffold("QUESTIONS OF THE HEART", "Explore through Krishna’s wisdom", R.drawable.bg_08_minimal_starfield, onBack, onNavigate) {
        item { GlassCard(Modifier.fillMaxWidth()) { Text("Ask about dharma, karma, life…", color = MutedText) } }
        items(faqs.indices.toList()) { i -> SacredListCard(faqs[i].first, if (expanded == i) faqs[i].second else "Tap to explore", R.drawable.icon_ask_krishna, { expanded = if (expanded == i) -1 else i }) }
    }
}

@Composable
fun WhatIsDharmaScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = FeatureScaffold("What is Dharma?", "Living in alignment", R.drawable.bg_06_dharma_crossroads, onBack, onNavigate, false) {
    item { SacredHero(R.drawable.illustration_08_wisdom_tree, "Dharma", "Dharma is right action aligned with truth, responsibility and the wellbeing of all.") }
    items(listOf(Triple("Truth", "See reality clearly", R.drawable.icon_teachings), Triple("Duty", "Honour your responsibilities", R.drawable.icon_dharma), Triple("Compassion", "Protect the wellbeing of others", R.drawable.icon_compassion), Triple("Courage", "Act even when the path is difficult", R.drawable.icon_courage))) { x -> SacredListCard(x.first, x.second, x.third, {}) }
    item { SacredListCard("Bhagavad Gita 3.35", "Better one’s own dharma, even imperfectly lived.", R.drawable.icon_gita, { onNavigate("16") }) }
    item { PrimaryGoldButton("Explore related questions", { onNavigate("20") }, Modifier.fillMaxWidth()) }
}

@Composable
fun AskKrishnaScreen(readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    var expandedQuestionId by remember { mutableIntStateOf(-1) }
    val filteredQuestions = remember(query) {
        searchAskKrishnaQuestions(query)
    }
    FeatureScaffold("Ask Krishna", "Pause. Share what is on your heart.", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_06_meditating_seeker, "Wisdom for life’s questions", "${readingMode(readingModeId).title} reading mode · Explore 40 practical reflections on dharma, relationships, emotions, discipline and purpose.") }
        item { OutlinedTextField(query, { query = it }, label = { Text("Describe what is happening") }, placeholder = { Text("Try: exam fear, anger with parents…") }, modifier = Modifier.fillMaxWidth(), singleLine = true) }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) { listOf("Exam fear", "Parents", "Betrayal", "Focus").forEach { label -> val phrase = when (label) { "Parents" -> "anger with parents"; "Betrayal" -> "friend betrayed me"; "Focus" -> "I cannot focus"; else -> "exam fear" }; SpiritualChip(label, R.drawable.icon_lotus, query.equals(phrase, true), { query = phrase }, Modifier.weight(1f)) } } }
        item { Text("Questions and Answers", color = LightGold, style = MaterialTheme.typography.headlineSmall) }
        items(filteredQuestions, key = { it.id }) { entry ->
            SacredListCard(
                entry.question,
                if (expandedQuestionId == entry.id) ageAppropriateAnswer(entry.answer, readingModeId) else "Tap to read the answer",
                R.drawable.icon_ask_krishna,
                { expandedQuestionId = if (expandedQuestionId == entry.id) -1 else entry.id }
            )
        }
        if (filteredQuestions.isEmpty()) item {
            GlassCard(Modifier.fillMaxWidth()) {
                Text("No matching question yet. Try a word such as duty, stress, friendship, forgiveness or courage.", color = MutedText)
            }
        }
    }
}

@Composable
fun LifeSituationsScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var selected by remember { mutableStateOf("Anxious") }
    val moods = listOf("Anxious", "Angry", "Lost", "Grieving", "Rejected", "Confused")
    FeatureScaffold("WHEN LIFE FEELS…", "Choose what your heart needs today", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_06_meditating_seeker, selected, "Krishna’s wisdom meets difficult feelings with patience and clarity.") }
        items(moods.chunked(3)) { row -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) { row.forEach { mood -> SpiritualChip(mood, R.drawable.icon_mind, selected == mood, { selected = mood }, Modifier.weight(1f)) } } }
        item { SacredListCard("Return to what is in your hands", "Breathe. Name the next right action. Release the imagined future. Gita 2.48.", R.drawable.icon_inner_peace, {}) }
        item { PrimaryGoldButton("Begin 3-minute guidance", { onNavigate("25") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun TodayWithKrishnaScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val steps = remember { mutableStateListOf(false, false, false, false) }
    FeatureScaffold("TODAY WITH KRISHNA", "A gentle daily practice", R.drawable.bg_02_vrindavan_dawn, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, "TODAY’S SLOKA · 2.48", "Remain steady in success and failure. Such equanimity is yoga.") }
        items(listOf("LISTEN · Hear the verse", "UNDERSTAND · Read its meaning", "REFLECT · Write one insight", "ACT · Choose today’s practice").indices.toList()) { i -> SacredListCard(listOf("Listen", "Understand", "Reflect", "Act")[i], if (steps[i]) "Completed" else "Tap when complete", if (steps[i]) R.drawable.icon_check else R.drawable.icon_lotus, { steps[i] = !steps[i] }) }
        item { PrimaryGoldButton(if (steps.all { it }) "Practice Complete" else "Continue Today’s Practice", { if (!steps.all { it }) { val i = steps.indexOfFirst { !it }; if (i >= 0) steps[i] = true } }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun MeditationAndChantingScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var running by remember { mutableStateOf(false) }; var seconds by remember { mutableIntStateOf(300) }
    LaunchedEffect(running, seconds) { if (running && seconds > 0) { delay(1000); seconds-- } }
    val min = seconds / 60; val sec = seconds % 60
    FeatureScaffold("Sacred Stillness", "Meditate in Krishna’s presence", R.drawable.bg_04_sacred_cosmic_temple, onBack, onNavigate, false) {
        item { Box(Modifier.fillMaxWidth().height(340.dp), contentAlignment = Alignment.Center) { AnimatedMandalaHalo(Modifier.size(320.dp)); SacredHero(R.drawable.illustration_06_meditating_seeker, "%02d:%02d".format(min, sec), if (running) "Breathe gently…" else "Ready when you are", Modifier.fillMaxWidth()) } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { listOf("Flute", "River", "Temple Bell").forEach { SpiritualChip(it, R.drawable.icon_audio, false, {}, Modifier.weight(1f)) } } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { SecondarySacredButton(if (running) "Pause" else "Start", { running = !running }, Modifier.weight(1f)); PrimaryGoldButton("End Meditation", { running = false; seconds = 300 }, Modifier.weight(1.5f)) } }
    }
}
