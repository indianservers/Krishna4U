package com.indianservers.krishna4u.feature.conceptmaps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.FeatureScaffold
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.GoldDivider
import com.indianservers.krishna4u.core.design.SacredHero
import com.indianservers.krishna4u.core.design.SacredIcon
import com.indianservers.krishna4u.core.design.SacredListCard
import com.indianservers.krishna4u.core.design.SecondarySacredButton
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun GitaConceptMapsLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    FeatureScaffold(
        "Gita Concept Maps",
        "See how Krishna’s teachings connect",
        R.drawable.bg_03_kurukshetra_cosmos,
        onBack,
        onNavigate
    ) {
        item {
            SacredHero(
                R.drawable.illustration_07_open_gita,
                "From one idea to the next",
                "Follow each path slowly. Tap a map to see where the chain begins, how it develops and where wisdom can change its direction."
            )
        }
        items(gitaConceptMaps) { map ->
            GlassCard(Modifier.fillMaxWidth(), onClick = { onNavigate("gita_concept_maps/${map.id}") }) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SacredIcon(map.icon, map.title, Modifier.size(48.dp))
                    Text(map.title, color = LightGold, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                    Text(map.subtitle, color = MutedText, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                    GoldDivider(Modifier.padding(horizontal = 28.dp))
                    Text(
                        map.stages.joinToString("  →  ") { it.title },
                        color = SoftWhite,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        item {
            SacredListCard(
                "How to read these maps",
                "The arrows describe a teaching sequence, not an unavoidable fate. Awareness, wise support and right action can interrupt an unhealthy chain at any stage.",
                R.drawable.icon_info
            )
        }
    }
}

@Composable
fun GitaConceptMapDetailsScreen(mapId: String?, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val map = gitaConceptMap(mapId)
    var selectedStage by remember(map.id) { mutableIntStateOf(0) }
    FeatureScaffold(
        map.title,
        "INTERACTIVE GITA CONCEPT MAP",
        R.drawable.bg_08_minimal_starfield,
        onBack,
        onNavigate,
        showBottomBar = false
    ) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    SacredIcon(map.icon, null, Modifier.size(52.dp))
                    Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(map.subtitle, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                        Text("Tap each stage to explore it", color = MutedText, style = MaterialTheme.typography.bodyMedium)
                    }
                    EnglishAudioIcon(map.spokenText, Modifier.size(44.dp))
                }
            }
        }
        item {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                map.stages.forEachIndexed { index, stage ->
                    GlassCard(
                        Modifier.fillMaxWidth(),
                        onClick = { selectedStage = index }
                    ) {
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Box(contentAlignment = Alignment.Center) {
                                    SacredIcon(stage.icon, stage.title, Modifier.size(50.dp))
                                    Text("${index + 1}", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                }
                                Column(Modifier.weight(1f).padding(start = 12.dp)) {
                                    Text(stage.title, color = LightGold, style = MaterialTheme.typography.headlineSmall)
                                    if (selectedStage != index) Text("Tap to understand this stage", color = MutedText, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                            if (selectedStage == index) {
                                GoldDivider()
                                Text(stage.explanation, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                                Text("In daily life", color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                                Text(stage.signInLife, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                    if (index < map.stages.lastIndex) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(Modifier.height(4.dp))
                            Box(Modifier.width(2.dp).height(20.dp).background(AntiqueGold.copy(alpha = .65f)))
                            Text("↓", color = AntiqueGold, style = MaterialTheme.typography.headlineMedium)
                            Spacer(Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
        item { SacredListCard("The turning point", map.turningPoint, R.drawable.icon_chakra) }
        item { SacredListCard("Practise this today", map.practice, R.drawable.icon_check) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Essential ślokas", color = LightGold, style = MaterialTheme.typography.headlineSmall)
                    map.verses.forEach { verse ->
                        SecondarySacredButton(
                            "${verse.reference} · ${verse.teaching}",
                            { onNavigate(verse.route) },
                            Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        item {
            Text(
                "A visual learning guide based on connected Bhagavad Gita teachings · explanations are not literal verse translations",
                color = MutedText,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
        }
    }
}
