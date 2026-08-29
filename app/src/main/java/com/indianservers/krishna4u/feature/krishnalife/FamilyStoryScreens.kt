package com.indianservers.krishna4u.feature.krishnalife

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.LocalStoryNarration
import com.indianservers.krishna4u.core.design.FeatureScaffold
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.PrimaryGoldButton
import com.indianservers.krishna4u.core.design.PeacockStorySweep
import com.indianservers.krishna4u.core.design.SacredHero
import com.indianservers.krishna4u.core.design.SacredIcon
import com.indianservers.krishna4u.core.design.SacredListCard
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun FamilyStoryLibraryScreen(readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val mode = readingMode(readingModeId)
    FeatureScaffold("STORY TIME TOGETHER", "Parent–Child Story Mode", R.drawable.bg_02_vrindavan_dawn, onBack, onNavigate) {
        item {
            SacredHero(
                R.drawable.illustration_01_krishna_full_body,
                "Listen, imagine and talk together",
                "${mode.title} · ${mode.ageRange}\nChoose a short Krishna story, listen as a family, then explore three questions without rushing toward a ‘correct’ answer."
            )
        }
        items(krishnaLifeEvents, key = { it.id }) { event ->
            SacredListCard(event.title, "${event.subtitle} · Short story and 3 questions", event.icon, { onNavigate("family_story/${event.id}") })
        }
    }
}

@Composable
fun FamilyStoryDetailsScreen(eventId: String?, readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val event = lifeEvent(eventId)
    FeatureScaffold(event.title, "Family Story Time", event.background, onBack, onNavigate, showBottomBar = false) {
        item { SacredHero(event.illustration, event.title, event.qualities) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    PeacockStorySweep("family-${event.id}")
                    Text("Story Time", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    Text(event.storyFor(if (readingModeId == "adults") "adults" else "kids"), color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    LocalStoryNarration(event.storyFor(if (readingModeId == "adults") "adults" else "kids"), Modifier.fillMaxWidth())
                }
            }
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Talk Together", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    event.familyQuestionsFor(readingModeId).forEachIndexed { index, question ->
                        Row {
                            SacredIcon(R.drawable.icon_lotus, null, Modifier.size(25.dp))
                            Text("${index + 1}.  $question", Modifier.padding(start = 9.dp), color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
        item { PrimaryGoldButton("Explore the Full Lesson", { onNavigate("07/${event.id}") }, Modifier.fillMaxWidth()) }
    }
}
