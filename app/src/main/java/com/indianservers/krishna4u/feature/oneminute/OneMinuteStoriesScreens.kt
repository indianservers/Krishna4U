package com.indianservers.krishna4u.feature.oneminute

import androidx.annotation.DrawableRes
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
import java.time.LocalDate

@DrawableRes
private fun storyIcon(theme: String): Int = when (theme) {
    "Beginnings" -> R.drawable.icon_lotus
    "Courage" -> R.drawable.icon_courage
    "Friendship", "Relationships" -> R.drawable.icon_friendship
    "Nature & Care" -> R.drawable.icon_peacock_feather
    "Dharma" -> R.drawable.icon_dharma
    "Mind", "Discipline" -> R.drawable.icon_mind
    "Action" -> R.drawable.icon_karma
    "Wisdom" -> R.drawable.icon_teachings
    "Youth" -> R.drawable.icon_relationships
    else -> R.drawable.icon_om
}

@Composable
fun OneMinuteStoriesLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    var selectedTheme by remember { mutableStateOf("All") }
    val themes = remember { listOf("All") + oneMinuteKrishnaStories.map { it.theme }.distinct() }
    val dailyStory = remember { oneMinuteKrishnaStories[(LocalDate.now().dayOfYear - 1) % oneMinuteKrishnaStories.size] }
    val filtered = remember(query, selectedTheme) {
        oneMinuteKrishnaStories.filter { item ->
            (selectedTheme == "All" || item.theme == selectedTheme) &&
                (query.isBlank() || listOf(item.title, item.story, item.moral, item.action, item.theme).any { it.contains(query, true) })
        }
    }
    FeatureScaffold("One-Minute Krishna Stories", "108 small stories · 108 daily actions", R.drawable.bg_01_cosmic_mandala, onBack, onNavigate) {
        item {
            SacredHero(
                R.drawable.illustration_01_krishna_full_body,
                "Today’s Story · ${dailyStory.number}",
                dailyStory.title
            )
        }
        item { PrimaryGoldButton("Read Today’s Story", { onNavigate("one_minute_story/${dailyStory.id}") }, Modifier.fillMaxWidth()) }
        item {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search stories, morals or actions") },
                leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(25.dp)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text("Browse by theme", color = LightGold, style = MaterialTheme.typography.titleLarge)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                themes.chunked(3).forEach { row ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                        row.forEach { theme ->
                            val label = when (theme) { "Nature & Care" -> "Nature"; "Relationships" -> "Relations"; else -> theme }
                            SpiritualChip(label, storyIcon(theme), selectedTheme == theme, { selectedTheme = theme }, Modifier.weight(1f))
                        }
                        repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }
        item { Text("${filtered.size} stories", color = LightGold, style = MaterialTheme.typography.headlineSmall) }
        items(filtered, key = { it.id }) { item ->
            SacredListCard("${item.number}. ${item.title}", item.moral, storyIcon(item.theme), { onNavigate("one_minute_story/${item.id}") })
        }
        if (filtered.isEmpty()) item { SacredListCard("No stories found", "Try another word or choose All.", R.drawable.icon_search) }
    }
}

@Composable
fun OneMinuteStoryScreen(storyId: String?, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val item = oneMinuteStory(storyId)
    val index = oneMinuteKrishnaStories.indexOf(item)
    val previous = oneMinuteKrishnaStories.getOrNull(index - 1)
    val next = oneMinuteKrishnaStories.getOrNull(index + 1)
    val spoken = "${item.title}. ${item.story} Takeaways: ${item.moral} ${item.dharmaTakeaway} Today's action: ${item.action}"
    FeatureScaffold(item.title, "STORY ${item.number} OF 108 · ${item.theme.uppercase()}", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate, false) {
        item {
            SacredHero(
                if (item.theme == "Youth" || item.theme == "Discipline") R.drawable.illustration_06_meditating_seeker else R.drawable.illustration_02_krishna_portrait,
                "A story for today",
                item.theme
            )
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    PeacockStorySweep("minute-${item.id}")
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text("One-minute story", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.headlineSmall)
                        EnglishAudioIcon(spoken, Modifier.size(44.dp))
                    }
                    Text(item.story, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Takeaways", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    Text("• ${item.moral}", color = SoftWhite, style = MaterialTheme.typography.titleLarge)
                    Text("• ${item.dharmaTakeaway}", color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        item { SacredListCard("Do this today", item.action, R.drawable.icon_check) }
        item {
            SecondarySacredButton(
                "Share Story",
                {
                    shareSacredText(
                        context,
                        "One-Minute Krishna Story · ${item.title}",
                        "${item.title}\n\n${item.story}\n\nTakeaways:\n• ${item.moral}\n• ${item.dharmaTakeaway}\n\nDo this today:\n${item.action}\n\nStory ${item.number} of 108 · Shared from Krishna For You"
                    )
                },
                Modifier.fillMaxWidth()
            )
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) "All Stories" else "Previous", { onNavigate(previous?.let { "one_minute_story/${it.id}" } ?: "one_minute_stories") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) "All Stories" else "Next", { onNavigate(next?.let { "one_minute_story/${it.id}" } ?: "one_minute_stories") }, Modifier.weight(1f))
            }
        }
    }
}
