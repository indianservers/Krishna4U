package com.indianservers.krishna4u.feature.gita

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.data.repository.OfflineGitaRepository
import com.indianservers.krishna4u.ui.theme.*

private val slokas = listOf("2.13" to "The Self passes through stages of the body", "2.47" to "Focus on action, not its fruits", "2.48" to "Remain steady in success and failure", "2.70" to "Peace belongs to the undisturbed mind")

@Composable
fun GitaOverviewScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = FeatureScaffold("Bhagavad Gita", "The song of timeless wisdom", R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate) {
    item { SacredHero(R.drawable.illustration_05_vishvarupa, "When duty meets doubt", "On Kurukshetra, Krishna revealed a path of action, wisdom and devotion.") }
    item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { listOf("18\nChapters", "700\nSlokas", "3\nPaths").forEach { GlassCard(Modifier.weight(1f)) { Text(it, Modifier.fillMaxWidth(), color = LightGold, textAlign = TextAlign.Center) } } } }
    item { SacredListCard("Karma Yoga", "The path of selfless action", R.drawable.icon_karma, { onNavigate("wisdom_theme/action") }) }
    item { SacredListCard("Jnana Yoga", "The path of knowledge", R.drawable.icon_teachings, { onNavigate("wisdom_theme/wisdom") }) }
    item { SacredListCard("Bhakti Yoga", "The path of devotion", R.drawable.icon_om, { onNavigate("wisdom_theme/faith") }) }
    item { SacredListCard("Chapter-wise Summaries", "18 chapters with themes and complete English summaries", R.drawable.icon_teachings, { onNavigate("gita_summaries") }) }
    item { SacredListCard("All 700 Slokas", "Sanskrit, transliteration and English meaning — fully offline", R.drawable.icon_gita, { onNavigate("gita_slokas/1") }) }
    item { PrimaryGoldButton("Explore Chapters", { onNavigate("13") }, Modifier.fillMaxWidth()) }
}

@Composable
fun ChapterExplorerScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val repository = remember { OfflineGitaRepository(context.applicationContext) }
    var selected by remember { mutableIntStateOf(2) }
    val selectedChapter = repository.chapter(selected)
    FeatureScaffold("EXPLORE 18 CHAPTERS", "$selected of 18 selected", R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate) {
        item { Box(Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) { AnimatedMandalaHalo(Modifier.size(290.dp)); Image(painterResource(R.drawable.illustration_03_krishna_arjuna_chariot), null, Modifier.size(210.dp), contentScale = ContentScale.Fit) } }
        items((1..18).chunked(3)) { row -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { row.forEach { n -> SpiritualChip(n.toString(), R.drawable.icon_chakra, selected == n, { selected = n }, Modifier.weight(1f)) } } }
        item { SacredListCard("Chapter $selected · ${selectedChapter.title}", selectedChapter.theme, R.drawable.icon_gita, { onNavigate("gita_chapter/$selected") }) }
        item { PrimaryGoldButton("Continue Chapter", { onNavigate("gita_chapter/$selected") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun ChapterSummaryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = CompleteChapterScreen(2, onBack, onNavigate)

@Composable
fun AllSlokasScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = FeatureScaffold("Chapter 2 · 72 Slokas", "Sankhya Yoga", R.drawable.bg_07_gita_wisdom, onBack, onNavigate) {
    item { SacredHero(R.drawable.illustration_01_krishna_full_body, "Sacred verses", "Read, listen, understand and save verified selections from this chapter.") }
    items(slokas) { (number, title) ->
        val verse = number.substringAfter('.').toInt()
        SacredListCard(number, title, R.drawable.icon_play, { onNavigate("gita_verse/2/$verse") })
    }
}

@Composable
fun IndividualSlokaScreen(bookmarked: Boolean, onToggleBookmark: () -> Unit, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    FeatureScaffold("Sloka 2.47", "Bhagavad Gita", R.drawable.bg_07_gita_wisdom, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_07_open_gita, "कर्मण्येवाधिकारस्ते मा फलेषु कदाचन", "karmaṇy-evādhikāras te mā phaleṣu kadācana") }
        item { GlassCard(Modifier.fillMaxWidth()) { Column { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Text("English meaning", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.titleLarge); EnglishAudioIcon("You have a right to perform your prescribed duty, but you are not entitled to the fruits of action.", Modifier.size(44.dp)) }; Text("You have a right to perform your prescribed duty, but you are not entitled to the fruits of action.", color = SoftWhite, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center) } } }
        item { SecondarySacredButton(if (bookmarked) "✓ Saved" else "☆ Bookmark", onToggleBookmark, Modifier.fillMaxWidth()) }
        item { SecondarySacredButton("Share Sloka", { shareSacredText(context, "Bhagavad Gita 2.47", "Bhagavad Gita 2.47\n\nकर्मण्येवाधिकारस्ते मा फलेषु कदाचन\n\nYou have a right to perform your prescribed duty, but you are not entitled to the fruits of action.\n\nShared from Krishna For You") }, Modifier.fillMaxWidth()) }
        item { PrimaryGoldButton("Understand this Sloka", { onNavigate("17") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun SlokaExplanationScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = FeatureScaffold("UNDERSTANDING 2.47", "Krishna’s teaching for daily life", R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate, false) {
    item { SacredHero(R.drawable.illustration_03_krishna_arjuna_chariot, "Simple meaning", "Give your full attention to what you can do.") }
    items(listOf(Triple("Deeper wisdom", "Attachment to results creates fear and weakens action.", R.drawable.icon_teachings), Triple("Modern example", "Study with discipline; do not let anxiety decide your effort.", R.drawable.icon_life_journey), Triple("Practice", "Choose one important action today and perform it without checking the outcome.", R.drawable.icon_check))) { x -> SacredListCard(x.first, x.second, x.third, {}) }
    item { PrimaryGoldButton("Next Teaching", { onNavigate("18") }, Modifier.fillMaxWidth()) }
}

@Composable
fun ListenToGitaScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var playing by remember { mutableStateOf(false) }
    FeatureScaffold("Listen to the Gita", "Let wisdom enter through sound", R.drawable.bg_04_sacred_cosmic_temple, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_07_open_gita, "Chapter 2 · Sloka 2.47", "Sanskrit recitation · audio unavailable in supplied pack") }
        item { Image(painterResource(R.drawable.ui_audio_waveform), null, Modifier.fillMaxWidth().height(90.dp), contentScale = ContentScale.FillWidth) }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) { SacredIcon(R.drawable.icon_previous, "Previous", Modifier.size(52.dp).clickable { }); Spacer(Modifier.width(20.dp)); SacredIcon(if (playing) R.drawable.icon_pause else R.drawable.icon_play, if (playing) "Pause" else "Play", Modifier.size(76.dp).clickable { playing = !playing }); Spacer(Modifier.width(20.dp)); SacredIcon(R.drawable.icon_next, "Next", Modifier.size(52.dp).clickable { }) } }
        items(slokas) { (n, t) -> SacredListCard(n, t, R.drawable.icon_audio, {}) }
    }
}

@Composable
fun GitaStudyModeScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var reflection by remember { mutableStateOf("") }; var saved by remember { mutableStateOf(false) }
    FeatureScaffold("STUDY MODE · CHAPTER 2", "Focused learning", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, "Sloka 2.47", "कर्मण्येवाधिकारस्ते मा फलेषु कदाचन") }
        item { SacredListCard("Word by word", "karmaṇi — in action · adhikāraḥ — right", R.drawable.icon_teachings, {}) }
        item { SacredListCard("What I learned", "My effort is mine; the result is not mine alone.", R.drawable.icon_mind, {}) }
        item { OutlinedTextField(reflection, { reflection = it }, label = { Text("Reflection") }, modifier = Modifier.fillMaxWidth(), minLines = 4) }
        item { PrimaryGoldButton(if (saved) "Reflection Saved" else "Save Reflection", { saved = true }, Modifier.fillMaxWidth()) }
    }
}
