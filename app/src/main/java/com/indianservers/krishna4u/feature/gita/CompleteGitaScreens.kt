package com.indianservers.krishna4u.feature.gita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.data.repository.*
import com.indianservers.krishna4u.ui.theme.*

@Composable
private fun rememberGitaRepository(): OfflineGitaRepository {
    val context = LocalContext.current
    return remember { OfflineGitaRepository(context.applicationContext) }
}

@Composable
fun ChapterWiseSummaryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val repository = rememberGitaRepository()
    FeatureScaffold("Chapter-wise Summaries", "18 paths through one timeless teaching", R.drawable.bg_07_gita_wisdom, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_07_open_gita, "The complete Bhagavad Gita", "Select a chapter for its theme, English summary and complete verse collection.") }
        items(gitaChapters) { chapter -> SacredListCard("${chapter.number}. ${chapter.title}", "${repository.versesInChapter(chapter.number).size} slokas · ${chapter.theme}", R.drawable.icon_gita, { onNavigate("gita_chapter/${chapter.number}") }) }
    }
}

@Composable
fun CompleteChapterScreen(chapterNumber: Int, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val repository = rememberGitaRepository(); val chapter = repository.chapter(chapterNumber); val verses = remember(chapterNumber) { repository.versesInChapter(chapterNumber) }
    val takeaways = gitaChapterTakeaways.getValue(chapterNumber)
    FeatureScaffold("Chapter ${chapter.number}", chapter.title, R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate, false) {
        item { SacredHero(if (chapterNumber == 11) R.drawable.illustration_05_vishvarupa else R.drawable.illustration_03_krishna_arjuna_chariot, chapter.theme, chapter.summary) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Key Takeaways", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                    Text("Carry these lessons from Chapter ${chapter.number} into daily life.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
                    takeaways.forEachIndexed { index, takeaway ->
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                            Text("${index + 1}.", color = AntiqueGold, style = MaterialTheme.typography.titleLarge)
                            Text(takeaway, Modifier.weight(1f).padding(start = 10.dp), color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
        item { GlassCard(Modifier.fillMaxWidth()) { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Text("English summary audio", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.titleLarge); EnglishAudioIcon(chapter.summary, Modifier.size(44.dp)) } } }
        item { SecondarySacredButton("Share Chapter Summary", { shareSacredText(context, "Bhagavad Gita · Chapter ${chapter.number}", "Bhagavad Gita · Chapter ${chapter.number}\n${chapter.title}\n${chapter.theme}\n\n${chapter.summary}\n\nKey Takeaways\n${takeaways.mapIndexed { index, item -> "${index + 1}. $item" }.joinToString("\n")}\n\nShared from Krishna For You") }, Modifier.fillMaxWidth()) }
        item { SacredListCard("${verses.size} slokas", "Sanskrit · transliteration · English meaning", R.drawable.icon_teachings, { onNavigate("gita_slokas/$chapterNumber") }) }
        item { PrimaryGoldButton("Read all slokas", { onNavigate("gita_slokas/$chapterNumber") }, Modifier.fillMaxWidth()) }
        if (chapterNumber < 18) item { SecondarySacredButton("Next: Chapter ${chapterNumber + 1}", { onNavigate("gita_chapter/${chapterNumber + 1}") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun CompleteSlokaLibraryScreen(initialChapter: Int, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val repository = rememberGitaRepository(); var chapter by remember { mutableIntStateOf(initialChapter.coerceIn(1, 18)) }; var query by remember { mutableStateOf("") }
    val currentChapter = repository.chapter(chapter)
    val verses = remember(chapter, query) { repository.versesInChapter(chapter).filter { query.isBlank() || it.englishSummary.contains(query, true) || it.sanskrit.contains(query, true) || "${it.chapter}.${it.verse}".contains(query) } }
    FeatureScaffold("All 700 Slokas", "Chapter $chapter · ${currentChapter.title} · ${repository.versesInChapter(chapter).size} verses", R.drawable.bg_07_gita_wisdom, onBack, onNavigate) {
        item { OutlinedTextField(query, { query = it }, label = { Text("Search Sanskrit, English or verse") }, leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(24.dp)) }, modifier = Modifier.fillMaxWidth()) }
        item {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (chapter > 1) {
                    val previous = repository.chapter(chapter - 1)
                    SecondarySacredButton("Previous · ${previous.number} · ${previous.title}", { chapter--; query = "" }, Modifier.fillMaxWidth())
                }
                if (chapter < 18) {
                    val next = repository.chapter(chapter + 1)
                    SecondarySacredButton("Next · ${next.number} · ${next.title}", { chapter++; query = "" }, Modifier.fillMaxWidth())
                }
            }
        }
        items(verses, key = { "${it.chapter}.${it.verse}" }) { verse -> SacredListCard("Sloka ${verse.chapter}.${verse.verse}", verse.englishSummary, R.drawable.icon_play, { onNavigate("gita_verse/${verse.chapter}/${verse.verse}") }) }
    }
}

@Composable
fun CompleteVerseReaderScreen(chapterNumber: Int, verseNumber: Int, bookmarked: Boolean, onToggleBookmark: () -> Unit, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val repository = rememberGitaRepository(); val verse = repository.verse(chapterNumber, verseNumber); val chapterVerses = remember(chapterNumber) { repository.versesInChapter(chapterNumber) }
    if (verse == null) return
    FeatureScaffold("Sloka ${verse.chapter}.${verse.verse}", repository.chapter(chapterNumber).title, R.drawable.bg_07_gita_wisdom, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_07_open_gita, verse.sanskrit, verse.transliteration) }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Text("English meaning", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.titleLarge); EnglishAudioIcon(verse.englishSummary, Modifier.size(44.dp)) }; Text(verse.englishSummary, color = SoftWhite, style = MaterialTheme.typography.bodyLarge); Text("Translation: ${verse.translator}", color = MutedText, style = MaterialTheme.typography.bodyMedium) } } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { SecondarySacredButton(if (bookmarked) "✓ Saved" else "☆ Bookmark", onToggleBookmark, Modifier.weight(1f)); SecondarySacredButton("Share", { shareSacredText(context, "Bhagavad Gita ${verse.chapter}.${verse.verse}", "Bhagavad Gita ${verse.chapter}.${verse.verse}\n\n${verse.sanskrit}\n\n${verse.transliteration}\n\nEnglish meaning:\n${verse.englishSummary}\n\nTranslation: ${verse.translator}\nShared from Krishna For You") }, Modifier.weight(1f)) } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { SecondarySacredButton("Previous", { if (verseNumber > 1) onNavigate("gita_verse/$chapterNumber/${verseNumber - 1}") }, Modifier.weight(1f)); SecondarySacredButton("All slokas", { onNavigate("gita_slokas/$chapterNumber") }, Modifier.weight(1f)); PrimaryGoldButton("Next", { if (verseNumber < chapterVerses.size) onNavigate("gita_verse/$chapterNumber/${verseNumber + 1}") }, Modifier.weight(1f)) } }
    }
}
