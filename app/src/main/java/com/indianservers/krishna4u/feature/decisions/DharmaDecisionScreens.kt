package com.indianservers.krishna4u.feature.decisions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
fun DharmaDecisionLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    FeatureScaffold("Dharma Decision Stories", "Choose first · Reflect · Reveal guidance", R.drawable.bg_06_dharma_crossroads, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_03_krishna_arjuna_chariot, "What would you do?", "Real Dharma is practised before the outcome is known. Enter each situation, choose honestly and then examine the guidance.") }
        items(dharmaDecisionStories) { dilemma ->
            SacredListCard(dilemma.title, "${dilemma.category} · ${dilemma.situation}", R.drawable.icon_dharma, { onNavigate("dharma_decision/${dilemma.id}") })
        }
    }
}

@Composable
fun DharmaDecisionStoryScreen(storyId: String?, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val dilemma = dharmaDecisionStory(storyId)
    val index = dharmaDecisionStories.indexOf(dilemma)
    val previous = dharmaDecisionStories.getOrNull(index - 1)
    val next = dharmaDecisionStories.getOrNull(index + 1)
    var selectedChoice by remember(dilemma.id) { mutableStateOf<Int?>(null) }
    var revealed by remember(dilemma.id) { mutableStateOf(false) }
    val selected = selectedChoice?.let(dilemma.choices::get)
    FeatureScaffold(dilemma.title, "DHARMA DECISION ${index + 1} OF ${dharmaDecisionStories.size} · ${dilemma.category.uppercase()}", R.drawable.bg_06_dharma_crossroads, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_06_meditating_seeker, dilemma.question, dilemma.situation) }
        item { Text("Choose before revealing the guidance", color = LightGold, style = MaterialTheme.typography.headlineSmall) }
        items(dilemma.choices.indices.toList()) { choiceIndex ->
            val choice = dilemma.choices[choiceIndex]
            GlassCard(
                Modifier.fillMaxWidth().clickable {
                    selectedChoice = choiceIndex
                    revealed = false
                }
            ) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedChoice == choiceIndex, onClick = {
                        selectedChoice = choiceIndex
                        revealed = false
                    })
                    Text(choice.text, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f).padding(start = 8.dp))
                }
            }
        }
        item {
            PrimaryGoldButton(
                if (selectedChoice == null) "Choose an answer first" else "Reveal Krishna-Inspired Guidance",
                { if (selectedChoice != null) revealed = true },
                Modifier.fillMaxWidth()
            )
        }
        if (revealed && selected != null) {
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(if (selected.dharmaAligned) "A Dharma-aligned choice" else "Pause and reconsider", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                        Text(selected.consequence, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text("Krishna-inspired guidance", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.titleLarge)
                            EnglishAudioIcon("${dilemma.krishnaGuidance} Remember: ${dilemma.principle} Today's action: ${dilemma.action}", Modifier.size(44.dp))
                        }
                        Text(dilemma.krishnaGuidance, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            item { SacredListCard("Remember", dilemma.principle, R.drawable.icon_teachings, {}) }
            item { SacredListCard("Put Dharma into action", dilemma.action, R.drawable.icon_check, {}) }
            item {
                SecondarySacredButton(
                    "Share This Decision",
                    {
                        shareSacredText(context, dilemma.title, "${dilemma.situation}\n\nKrishna-inspired guidance:\n${dilemma.krishnaGuidance}\n\nRemember:\n${dilemma.principle}\n\nAction:\n${dilemma.action}\n\nShared from Krishna For You")
                    },
                    Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Stories" else "Previous", { onNavigate(previous?.let { "dharma_decision/${it.id}" } ?: "dharma_decisions") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Stories" else "Next", { onNavigate(next?.let { "dharma_decision/${it.id}" } ?: "dharma_decisions") }, Modifier.weight(1f))
            }
        }
    }
}
