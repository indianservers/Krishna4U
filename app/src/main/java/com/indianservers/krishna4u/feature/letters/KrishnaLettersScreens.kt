package com.indianservers.krishna4u.feature.letters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun KrishnaLettersLibraryScreen(displayName: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val readerName = displayName.trim().ifBlank { "Friend" }
    FeatureScaffold("Krishna’s Letters to You", "For the moments that ask for deeper words", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item {
            SacredHero(
                R.drawable.illustration_02_krishna_portrait,
                "My dear $readerName,",
                "Choose what your heart is carrying. Read slowly, listen privately and keep only the guidance that helps you take a healthy next step."
            )
        }
        items(krishnaLetters) { letter ->
            SacredListCard(letter.situation, letter.preview, letter.icon, { onNavigate("krishna_letters/${letter.id}") })
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Text(
                    "These are Krishna-inspired reflective letters, not literal scriptural quotations. Spiritual encouragement can accompany—but should not replace—trusted personal or professional support.",
                    color = MutedText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun KrishnaLetterScreen(letterId: String?, displayName: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val readerName = displayName.trim().ifBlank { "Friend" }
    val letter = krishnaLetter(letterId)
    val index = krishnaLetters.indexOf(letter)
    val previous = krishnaLetters.getOrNull(index - 1)
    val next = krishnaLetters.getOrNull(index + 1)
    val shareText = "My dear $readerName,\n\n${letter.paragraphs.joinToString("\n\n")}\n\nReflect:\n${letter.reflection}\n\nWith you in every sincere step,\nKrishna\n\nKrishna-inspired reflection · Shared from Krishna For You"
    FeatureScaffold(letter.title, "LETTER ${index + 1} OF ${krishnaLetters.size} · ${letter.situation.uppercase()}", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("My dear $readerName,", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                        Text("Listen to this letter", color = MutedText)
                    }
                    EnglishAudioIcon(letter.spokenText(readerName), Modifier.size(46.dp))
                }
            }
        }
        items(letter.paragraphs) { paragraph ->
            GlassCard(Modifier.fillMaxWidth()) {
                Text(paragraph, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
            }
        }
        item { SacredListCard("Pause and reflect", letter.reflection, R.drawable.icon_journal, {}) }
        item { SacredListCard("A gentle next step", letter.nextStep, R.drawable.icon_check, {}) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Text("With you in every sincere step,", color = LightGold, style = MaterialTheme.typography.titleMedium)
                    Text("Krishna", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    Text("A Krishna-inspired reflection", color = MutedText, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        item { SecondarySacredButton("Share Letter", { shareSacredText(context, letter.title, shareText) }, Modifier.fillMaxWidth()) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Letters" else "Previous", { onNavigate(previous?.let { "krishna_letters/${it.id}" } ?: "krishna_letters") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Letters" else "Next", { onNavigate(next?.let { "krishna_letters/${it.id}" } ?: "krishna_letters") }, Modifier.weight(1f))
            }
        }
    }
}
