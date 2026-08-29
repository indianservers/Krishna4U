package com.indianservers.krishna4u.feature.emotions

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
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun EmotionalIntelligenceLibraryScreen(readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val mode = readingMode(readingModeId)
    FeatureScaffold("Krishna & Emotions", "Emotional intelligence for daily life", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item {
            SacredHero(
                R.drawable.illustration_02_krishna_portrait,
                "Feel clearly. Choose wisely.",
                "Emotions are messengers. Krishna’s wisdom can help us understand them without letting them control our words and actions."
            )
        }
        item { SacredListCard("Reading Mode · ${mode.title}", "${mode.ageRange} · Examples adapt to your selection", R.drawable.icon_relationships, { onNavigate("28") }) }
        items(emotionalIntelligenceLessons) { lesson ->
            SacredListCard(lesson.title, lesson.subtitle, lesson.icon, { onNavigate("emotional_intelligence/${lesson.id}") })
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("A gentle safety note", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    Text("These lessons build everyday emotional skills. Persistent distress, danger, abuse or thoughts of self-harm need support from a trusted adult or qualified professional.", color = MutedText)
                }
            }
        }
    }
}

@Composable
fun EmotionalIntelligenceLessonScreen(lessonId: String?, readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val lesson = emotionalIntelligenceLesson(lessonId)
    val index = emotionalIntelligenceLessons.indexOf(lesson)
    val previous = emotionalIntelligenceLessons.getOrNull(index - 1)
    val next = emotionalIntelligenceLessons.getOrNull(index + 1)
    FeatureScaffold(lesson.title, "EMOTIONAL INTELLIGENCE · ${index + 1} OF ${emotionalIntelligenceLessons.size}", R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_06_meditating_seeker, lesson.openingQuestion, lesson.subtitle) }
        item { SacredListCard("Krishna’s connection", lesson.krishnaConnection, R.drawable.icon_teachings) }
        item { SacredListCard("The emotional skill", lesson.emotionalSkill, lesson.icon) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Practise it step by step", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    lesson.steps.forEachIndexed { stepIndex, step ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("${stepIndex + 1}", color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                            Text(step, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SacredListCard("A ${readingMode(readingModeId).title.lowercase()} example", lesson.exampleFor(readingModeId), R.drawable.icon_life_journey) }
        item { SacredListCard("Reflect", lesson.reflection, R.drawable.icon_journal) }
        item { SacredListCard("Try this today", lesson.practice, R.drawable.icon_check) }
        item {
            SecondarySacredButton(
                "Share Lesson",
                {
                    shareSacredText(
                        context,
                        "Krishna and Emotional Intelligence · ${lesson.title}",
                        "${lesson.openingQuestion}\n\n${lesson.krishnaConnection}\n\n${lesson.emotionalSkill}\n\nTry this today:\n${lesson.practice}\n\nShared from Krishna For You"
                    )
                },
                Modifier.fillMaxWidth()
            )
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Lessons" else "Previous", { onNavigate(previous?.let { "emotional_intelligence/${it.id}" } ?: "emotional_intelligence") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Lessons" else "Next", { onNavigate(next?.let { "emotional_intelligence/${it.id}" } ?: "emotional_intelligence") }, Modifier.weight(1f))
            }
        }
    }
}
