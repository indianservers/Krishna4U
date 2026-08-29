package com.indianservers.krishna4u.feature.difficultquestions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun DifficultQuestionsLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val filtered = remember(query) {
        difficultKrishnaQuestions.filter { item ->
            query.isBlank() || listOf(item.question, item.shortAnswer, item.theme, item.ethicalTension).any { it.contains(query, true) }
        }
    }
    FeatureScaffold("Difficult Questions About Krishna", "Context · Tension · Multiple readings", R.drawable.bg_07_gita_wisdom, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_03_krishna_arjuna_chariot, "Faith need not fear honest questions", "These studies preserve ethical difficulty instead of forcing every episode into an easy answer.") }
        item {
            OutlinedTextField(query, { query = it }, label = { Text("Search war, strategy, punishment or lila") }, leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(25.dp)) }, singleLine = true, modifier = Modifier.fillMaxWidth())
        }
        items(filtered) { question ->
            SacredListCard(question.question, "${question.theme} · ${question.shortAnswer}", R.drawable.icon_teachings, { onNavigate("difficult_question/${question.id}") })
        }
        if (filtered.isEmpty()) item { SacredListCard("No question found", "Try a broader word.", R.drawable.icon_search, {}) }
        item { GlassCard(Modifier.fillMaxWidth()) { Text("These are study notes, not final verdicts. The Mahabharata and Krishna traditions contain layered voices, and translations and commentaries often differ.", color = MutedText) } }
    }
}

@Composable
fun DifficultQuestionScreen(questionId: String?, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val item = difficultKrishnaQuestion(questionId)
    val index = difficultKrishnaQuestions.indexOf(item)
    val previous = difficultKrishnaQuestions.getOrNull(index - 1)
    val next = difficultKrishnaQuestions.getOrNull(index + 1)
    val spoken = "${item.question} Short answer. ${item.shortAnswer} Context. ${item.context} Ethical tension. ${item.ethicalTension} Possible readings. ${item.readings.joinToString(" ")} What not to conclude. ${item.doNotConclude} Reflection. ${item.reflection}"
    FeatureScaffold("A Difficult Question", "${item.theme.uppercase()} · ${index + 1} OF ${difficultKrishnaQuestions.size}", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, item.question, item.shortAnswer) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Listen to the complete study", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.titleLarge)
                    EnglishAudioIcon(spoken, Modifier.size(44.dp))
                }
            }
        }
        item { SacredListCard("Narrative context", item.context, R.drawable.icon_gita, {}) }
        item { SacredListCard("The ethical tension", item.ethicalTension, R.drawable.icon_dharma, {}) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Possible readings", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    item.readings.forEach { reading ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("✦", color = AntiqueGold)
                            Text(reading, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SacredListCard("What not to conclude", item.doNotConclude, R.drawable.icon_courage, {}) }
        item { SacredListCard("Reflect", item.reflection, R.drawable.icon_journal, {}) }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(5.dp)) { Text("Textual note", color = LightGold, style = MaterialTheme.typography.titleMedium); Text(item.sourceNote, color = MutedText) } } }
        item {
            SecondarySacredButton("Share Study", { shareSacredText(context, item.question, "${item.question}\n\n${item.shortAnswer}\n\nContext:\n${item.context}\n\nEthical tension:\n${item.ethicalTension}\n\nPossible readings:\n${item.readings.joinToString("\n") { "• $it" }}\n\nWhat not to conclude:\n${item.doNotConclude}\n\nShared from Krishna For You") }, Modifier.fillMaxWidth())
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Questions" else "Previous", { onNavigate(previous?.let { "difficult_question/${it.id}" } ?: "difficult_questions") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Questions" else "Next", { onNavigate(next?.let { "difficult_question/${it.id}" } ?: "difficult_questions") }, Modifier.weight(1f))
            }
        }
    }
}
