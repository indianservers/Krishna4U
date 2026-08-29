package com.indianservers.krishna4u.feature.krishnalife

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.*

@Composable
fun KrishnaLifeJourneyScreen(readingModeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val mode = readingMode(readingModeId)
    FeatureScaffold("KRISHNA’S LIFE", "Stories that shape character", R.drawable.bg_02_vrindavan_dawn, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, "Stories from Krishna’s life", "${mode.title} · ${mode.ageRange}\n${mode.description}") }
        item { SacredListCard("Reading Mode · ${mode.title}", "${mode.ageRange} · Tap to change in Settings", R.drawable.icon_font_size, { onNavigate("28") }) }
        item { SacredListCard("Parent–Child Story Mode", "Short narrated stories and questions to explore together", R.drawable.icon_relationships, { onNavigate("family_stories") }) }
        items(krishnaLifeEvents) { event -> SacredListCard(event.title, "${event.era} · ${event.subtitle}", event.icon, { onNavigate("07/${event.id}") }) }
    }
}

@Composable
fun LifeEventDetailsScreen(eventId: String?, readingModeId: String, bookmarked: Boolean, onToggleBookmark: () -> Unit, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val event = lifeEvent(eventId)
    FeatureScaffold(event.title, event.subtitle, event.background, onBack, onNavigate, showBottomBar = false) {
        item { SacredHero(event.illustration, event.title, event.qualities) }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { Text("Story", color = LightGold, style = MaterialTheme.typography.titleLarge); Text(event.storyFor(readingModeId), color = SoftWhite, style = MaterialTheme.typography.bodyLarge) } } }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { Text("Takeaways", color = LightGold, style = MaterialTheme.typography.titleLarge); event.takeawaysFor(readingModeId).forEachIndexed { i, takeaway -> Row { SacredIcon(R.drawable.icon_check, null, Modifier.size(25.dp)); Text("${i + 1}.  $takeaway", Modifier.padding(start = 9.dp), color = SoftWhite) } } } } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { SecondarySacredButton(if (bookmarked) "✓ Saved" else "☆ Bookmark", onToggleBookmark, Modifier.weight(1f)); SecondarySacredButton("Share Story", { shareSacredText(context, event.title, "${event.title} · ${event.subtitle}\n\nStory\n${event.storyFor(readingModeId)}\n\nTakeaways\n${event.takeawaysFor(readingModeId).mapIndexed { index, value -> "${index + 1}. $value" }.joinToString("\n")}\n\nShared from Krishna For You") }, Modifier.weight(1f)) } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { SecondarySacredButton("Life Journey", onBack, Modifier.weight(1f)); PrimaryGoldButton("Apply this lesson", { onNavigate("11") }, Modifier.weight(1.45f)) } }
    }
}
