package com.indianservers.krishna4u.feature.evening

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.ui.theme.*
import java.time.LocalDate

@Composable
fun KrishnaEveningReflectionScreen(
    displayName: String,
    onSaveReflection: (String) -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val readerName = displayName.trim().ifBlank { "Friend" }
    var deckNumber by remember { mutableIntStateOf(0) }
    val prompts = remember(deckNumber) {
        eveningReflectionDeck(LocalDate.now().dayOfYear * 101 + deckNumber)
    }
    var currentIndex by remember(deckNumber) { mutableIntStateOf(0) }
    val answers = remember(deckNumber) { mutableStateMapOf<String, String>() }
    var saved by remember(deckNumber) { mutableStateOf(false) }
    val prompt = prompts[currentIndex]
    val answer = answers[prompt.id].orEmpty()

    FeatureScaffold(
        "KRISHNA’S EVENING REFLECTION",
        "Look back with truth · Rest without guilt",
        R.drawable.bg_05_moonlit_sacred_river,
        onBack,
        onNavigate
    ) {
        item {
            SacredHero(
                R.drawable.illustration_06_meditating_seeker,
                "Sit with Me, $readerName",
                "Keep the lesson, make the repair and release the rest. Your answers stay on this device and are saved only when you choose."
            )
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                if (prompt.essential) "Essential reflection" else "Tonight’s deeper reflection",
                                color = AntiqueGold,
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text("Question ${currentIndex + 1} of ${prompts.size}", color = MutedText)
                        }
                        EnglishAudioIcon("My dear $readerName. ${prompt.question} ${prompt.gentleCue}", Modifier.size(44.dp))
                    }
                    LinearProgressIndicator(
                        progress = { (currentIndex + 1f) / prompts.size },
                        modifier = Modifier.fillMaxWidth(),
                        color = LightGold,
                        trackColor = SoftWhite.copy(alpha = .14f)
                    )
                    Text(prompt.question, color = LightGold, style = MaterialTheme.typography.headlineSmall)
                    Text(prompt.gentleCue, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    OutlinedTextField(
                        value = answer,
                        onValueChange = { answers[prompt.id] = it.take(600); saved = false },
                        label = { Text("Write honestly, in your own words") },
                        minLines = 4,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(
                    if (currentIndex == 0) "New Questions" else "Previous",
                    {
                        if (currentIndex == 0) deckNumber++ else currentIndex--
                    },
                    Modifier.weight(1f)
                )
                PrimaryGoldButton(
                    if (currentIndex == prompts.lastIndex) "Finish" else "Next",
                    {
                        if (currentIndex < prompts.lastIndex) currentIndex++ else {
                            val completed = prompts.mapNotNull { item ->
                                answers[item.id]?.trim()?.takeIf { it.isNotBlank() }?.let { response ->
                                    "${item.question}\n$response"
                                }
                            }
                            if (completed.isNotEmpty()) {
                                onSaveReflection("Krishna’s Evening Reflection · ${LocalDate.now()}\n\n${completed.joinToString("\n\n")}")
                                saved = true
                            }
                        }
                    },
                    Modifier.weight(1f)
                )
            }
        }
        if (saved) {
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        SacredIcon(R.drawable.icon_check, null, Modifier.size(42.dp))
                        Text("Saved privately in your Journal", color = LightGold, textAlign = TextAlign.Center)
                        Text("You may now place this day in Krishna’s hands.", color = MutedText, textAlign = TextAlign.Center)
                    }
                }
            }
        }
        item {
            Text(
                "The six essential questions return each evening. Six more are selected from 50 deeper prompts, and New Questions gives you another set.",
                color = MutedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
        }
    }
}
