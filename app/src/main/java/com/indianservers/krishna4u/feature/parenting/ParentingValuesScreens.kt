package com.indianservers.krishna4u.feature.parenting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun ParentingValuesLibraryScreen(readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var selectedValue by remember { mutableStateOf("All") }
    val visible = if (selectedValue == "All") parentingValuesSessions else parentingValuesSessions.filter { it.value == selectedValue }
    FeatureScaffold("Parenting with Krishna’s Values", "Stories · Conversations · Family activities", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, "Values are learned in relationship", "Read together, invite the child’s thinking and practise one small action as a family.") }
        item { SacredListCard("Reading Mode · ${readingMode(readingModeId).title}", "${readingMode(readingModeId).ageRange} · Facilitation tips adapt automatically", R.drawable.icon_relationships, { onNavigate("28") }) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("All", "Honesty", "Responsibility", "Compassion", "Courage").take(3).forEach { value ->
                    SpiritualChip(value, R.drawable.icon_dharma, selectedValue == value, { selectedValue = value }, Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("Compassion", "Courage").forEach { value ->
                    SpiritualChip(value, if (value == "Courage") R.drawable.icon_courage else R.drawable.icon_compassion, selectedValue == value, { selectedValue = value }, Modifier.weight(1f))
                }
            }
        }
        items(visible) { session ->
            SacredListCard(session.title, "${session.value} · ${session.lesson}", if (session.value == "Courage") R.drawable.icon_courage else R.drawable.icon_compassion, { onNavigate("parenting_values/${session.id}") })
        }
        item { GlassCard(Modifier.fillMaxWidth()) { Text("Use curiosity before correction. These sessions support family conversation; they do not replace professional help for safety, developmental or mental-health concerns.", color = MutedText) } }
    }
}

@Composable
fun ParentingValuesSessionScreen(sessionId: String?, readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val session = parentingValuesSession(sessionId)
    val index = parentingValuesSessions.indexOf(session)
    val previous = parentingValuesSessions.getOrNull(index - 1)
    val next = parentingValuesSessions.getOrNull(index + 1)
    val spoken = "${session.title}. ${session.story} Lesson. ${session.lesson} Conversation questions. ${session.conversationPrompts.joinToString(" ")} Family activity. ${session.activityTitle}. ${session.activitySteps.joinToString(" ")}"
    FeatureScaffold(session.title, "${session.value.uppercase()} · FAMILY SESSION ${index + 1} OF ${parentingValuesSessions.size}", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Read together", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.headlineSmall)
                    EnglishAudioIcon(spoken, Modifier.size(44.dp))
                }
            }
        }
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, session.lesson, session.story) }
        item { SacredListCard("For the parent or caregiver", session.parentNote, R.drawable.icon_relationships, {}) }
        item { SacredListCard("${readingMode(readingModeId).title} facilitation tip", session.facilitationTip(readingModeId), R.drawable.icon_teachings, {}) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Talk about it", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    session.conversationPrompts.forEach { prompt ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("?", color = AntiqueGold, style = MaterialTheme.typography.titleLarge)
                            Text(prompt, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Family activity · ${session.activityTitle}", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    session.activitySteps.forEachIndexed { step, text ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("${step + 1}", color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                            Text(text, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SecondarySacredButton("Share Family Session", { shareSacredText(context, session.title, "${session.title}\n\nStory:\n${session.story}\n\nLesson:\n${session.lesson}\n\nTalk about it:\n${session.conversationPrompts.joinToString("\n") { "• $it" }}\n\nFamily activity · ${session.activityTitle}:\n${session.activitySteps.mapIndexed { i, value -> "${i + 1}. $value" }.joinToString("\n")}\n\nShared from Krishna For You") }, Modifier.fillMaxWidth()) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Sessions" else "Previous", { onNavigate(previous?.let { "parenting_values/${it.id}" } ?: "parenting_values") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Sessions" else "Next", { onNavigate(next?.let { "parenting_values/${it.id}" } ?: "parenting_values") }, Modifier.weight(1f))
            }
        }
    }
}
