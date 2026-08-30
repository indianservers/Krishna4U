package com.indianservers.krishna4u.feature.parenting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
    var selectedMode by remember { mutableStateOf("All") }
    var selectedValue by remember { mutableStateOf("All") }
    LaunchedEffect(selectedMode) { selectedValue = "All" }
    val visible = parentingValuesSessions.filter { session ->
        val modeMatches = when (selectedMode) {
            "Family" -> !session.teenFocused
            "Parent–Teen" -> session.teenFocused
            else -> true
        }
        modeMatches && (selectedValue == "All" || session.value == selectedValue)
    }
    val availableValues = listOf("All") + parentingValuesSessions
        .filter { session ->
            when (selectedMode) {
                "Family" -> !session.teenFocused
                "Parent–Teen" -> session.teenFocused
                else -> true
            }
        }
        .map { it.value }
        .distinct()
    FeatureScaffold("Parenting with Krishna’s Values", "Stories · Conversations · Family activities", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, "Values are learned in relationship", "Read together, invite the child’s thinking and practise one small action as a family.") }
        item { SacredListCard("What happened today?", "Choose a real situation and receive calm-first words, a first action and the right family session", R.drawable.icon_ask_krishna, { onNavigate("parenting_today") }) }
        item { SacredListCard("Reading Mode · ${readingMode(readingModeId).title}", "${readingMode(readingModeId).ageRange} · Facilitation tips adapt automatically", R.drawable.icon_relationships, { onNavigate("28") }) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("All", "Family", "Parent–Teen").forEach { mode ->
                    SpiritualChip(mode, if (mode == "Parent–Teen") R.drawable.icon_relationships else R.drawable.icon_dharma, selectedMode == mode, { selectedMode = mode }, Modifier.weight(1f))
                }
            }
        }
        item {
            LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                items(availableValues) { value ->
                    val icon = when (value) {
                        "Courage", "Handling failure" -> R.drawable.icon_courage
                        "Compassion", "Service and generosity", "Care for animals and nature" -> R.drawable.icon_compassion
                        "Friendship", "Respect", "Care for elders", "Healthy boundaries" -> R.drawable.icon_relationships
                        "Self-control", "Patience", "Discipline" -> R.drawable.icon_mind
                        else -> R.drawable.icon_dharma
                    }
                    SpiritualChip(value, icon, selectedValue == value, { selectedValue = value }, Modifier.widthIn(min = 108.dp))
                }
            }
        }
        items(visible) { session ->
            SacredListCard(session.title, "${if (session.teenFocused) "Parent–Teen · " else ""}${session.value} · ${session.lesson}", if (session.teenFocused) R.drawable.icon_relationships else if (session.value == "Courage") R.drawable.icon_courage else R.drawable.icon_compassion, { onNavigate("parenting_values/${session.id}") })
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
    val rolePlaySteps = session.rolePlaySteps()
    val weeklyAction = session.actionForThisWeek()
    val spoken = "${session.title}. ${session.story} Lesson. ${session.lesson} Conversation questions. ${session.conversationPrompts.joinToString(" ")} Family activity. ${session.activityTitle}. ${session.activitySteps.joinToString(" ")} Family role-play. ${rolePlaySteps.joinToString(" ")} One action this week. $weeklyAction"
    FeatureScaffold(session.title, "${if (session.teenFocused) "PARENT–TEEN" else session.value.uppercase()} · FAMILY SESSION ${index + 1} OF ${parentingValuesSessions.size}", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Read together", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.headlineSmall)
                    EnglishAudioIcon(spoken, Modifier.size(44.dp))
                }
            }
        }
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, session.lesson, session.story) }
        item { SacredListCard("For the parent or caregiver", session.parentNote, R.drawable.icon_relationships) }
        item { SacredListCard("${readingMode(readingModeId).title} facilitation tip", session.facilitationTip(readingModeId), R.drawable.icon_teachings) }
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
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Family role-play", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    rolePlaySteps.forEachIndexed { step, text ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("${step + 1}", color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                            Text(text, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SacredListCard("One action this week", weeklyAction, R.drawable.icon_check) }
        item { SecondarySacredButton("Share Family Session", { shareSacredText(context, session.title, "${session.title}\n\nStory:\n${session.story}\n\nLesson:\n${session.lesson}\n\nTalk about it:\n${session.conversationPrompts.joinToString("\n") { "• $it" }}\n\nFamily activity · ${session.activityTitle}:\n${session.activitySteps.mapIndexed { i, value -> "${i + 1}. $value" }.joinToString("\n")}\n\nFamily role-play:\n${rolePlaySteps.mapIndexed { i, value -> "${i + 1}. $value" }.joinToString("\n")}\n\nOne action this week:\n$weeklyAction\n\nShared from Krishna For You") }, Modifier.fillMaxWidth()) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Sessions" else "Previous", { onNavigate(previous?.let { "parenting_values/${it.id}" } ?: "parenting_values") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Sessions" else "Next", { onNavigate(next?.let { "parenting_values/${it.id}" } ?: "parenting_values") }, Modifier.weight(1f))
            }
        }
    }
}
