package com.indianservers.krishna4u.feature.teachings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.*

@Composable
fun TeachingsLibraryScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var category by remember { mutableStateOf("All") }
    var query by remember { mutableStateOf("") }
    val visibleTeachings = teachingLibrary.filter { teaching ->
        (category == "All" || teaching.category == category) &&
            (query.isBlank() || listOf(teaching.title, teaching.summary, teaching.question, teaching.teaching, teaching.takeaways.joinToString()).any { it.contains(query, ignoreCase = true) })
    }
    FeatureScaffold("Krishna’s Teachings", "Timeless wisdom for modern life", R.drawable.bg_01_cosmic_mandala, onBack, onNavigate) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(24.dp)) },
                    placeholder = { Text("Search a teaching or life problem…") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("All", "Krishna’s Guidance", "Gita Wisdom", "Compassion", "Daily Dharma").chunked(2).forEach { row ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { label ->
                            val shortLabel = when (label) { "Krishna’s Guidance" -> "Guidance"; else -> label }
                            SpiritualChip(shortLabel, if (label == "Compassion") R.drawable.icon_compassion else R.drawable.icon_dharma, category == label, { category = label }, Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, "${teachingLibrary.size} paths for daily life", "Krishna’s guidance, compassionate stories, Gita wisdom and practical dharma for everyday choices.") }
        items(visibleTeachings) { teaching -> SacredListCard(teaching.title, teaching.summary, teaching.icon, { onNavigate("teaching/${teaching.id}") }) }
        if (visibleTeachings.isEmpty()) item { SacredListCard("No teachings found", "Try a shorter phrase or choose All.", R.drawable.icon_search, {}) }
    }
}

@Composable
fun TeachingDetailsScreen(teachingId: String, bookmarked: Boolean, onToggleBookmark: () -> Unit, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val teaching = teachingById(teachingId)
    FeatureScaffold("TEACHING · ${teaching.category.uppercase()}", teaching.source, R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, teaching.title, teaching.question) }
        item { SacredListCard("Teaching", teaching.teaching, R.drawable.icon_teachings, {}) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Takeaways", style = MaterialTheme.typography.headlineSmall, color = AntiqueGold)
                    teaching.takeaways.forEach { takeaway ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                            Text("✦", color = AntiqueGold)
                            Text(takeaway, style = MaterialTheme.typography.bodyLarge, color = SoftWhite, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        item { SacredListCard("Try this today", teaching.practice, R.drawable.icon_strategy, {}) }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { SecondarySacredButton(if (bookmarked) "✓ Saved" else "☆ Bookmark", onToggleBookmark, Modifier.weight(1f)); PrimaryGoldButton("Practice this today", { onNavigate("11") }, Modifier.weight(1.5f)) } }
        item { SecondarySacredButton("Share Teaching", { shareSacredText(context, "Krishna's Teaching · ${teaching.title}", "${teaching.question}\n\n${teaching.teaching}\n\nTakeaways:\n${teaching.takeaways.joinToString("\n") { "• $it" }}\n\n${teaching.source} · Shared from Krishna For You") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun LessonsFromKrishnaScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val qualities = listOf(
        Triple("Wisdom", R.drawable.icon_teachings, "wisdom_theme/wisdom"),
        Triple("Playfulness", R.drawable.icon_playfulness, "07/gokul"),
        Triple("Strategy", R.drawable.icon_strategy, "07/dwarka"),
        Triple("Courage", R.drawable.icon_courage, "teaching/dharma-draupadi"),
        Triple("Friendship", R.drawable.icon_friendship, "07/friendship"),
        Triple("Compassion", R.drawable.icon_compassion, "wisdom_theme/compassion")
    )
    FeatureScaffold("The Krishna Within", "Qualities his life invites us to awaken", R.drawable.bg_01_cosmic_mandala, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_01_krishna_full_body, "Awaken your inner strengths", "Krishna’s qualities are invitations to live with clarity, courage and compassion.") }
        items(qualities.chunked(2)) { row -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) { row.forEach { (name, icon, route) -> SacredListCard(name, "Explore this quality", icon, { onNavigate(route) }, Modifier.weight(1f)) } } }
        item { PrimaryGoldButton("Explore Wisdom", { onNavigate("09") }, Modifier.fillMaxWidth()) }
    }
}

@Composable
fun ApplyToYourLifeScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var answer by remember { mutableStateOf("") }
    var showGuidance by remember { mutableStateOf(false) }
    var showInputHint by remember { mutableStateOf(false) }
    val reflectionSteps = listOf(
        "Pause — separate fear from duty",
        "Discern — ask whom the choice serves",
        "Act — choose with courage",
        "Release — let go of the outcome"
    )
    FeatureScaffold("Bring Wisdom to Life", "Apply it to your life", R.drawable.bg_06_dharma_crossroads, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_06_meditating_seeker, "What are you facing today?", "A right choice can feel difficult. Krishna’s wisdom helps separate fear from dharma.") }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = answer,
                    onValueChange = {
                        answer = it.take(500)
                        showInputHint = false
                        showGuidance = false
                    },
                    label = { Text("Describe your situation") },
                    supportingText = {
                        Text(
                            if (showInputHint) "Please share a few words so the reflection can guide you." else "${answer.length}/500",
                            color = if (showInputHint) MaterialTheme.colorScheme.error else MutedText
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 6
                )
            }
        }
        items(reflectionSteps) { step -> SacredListCard(step, "A step in your reflection", R.drawable.icon_check, {}) }
        item {
            PrimaryGoldButton(
                "Begin Guided Reflection",
                {
                    if (answer.isBlank()) {
                        showInputHint = true
                        showGuidance = false
                    } else {
                        showInputHint = false
                        showGuidance = true
                    }
                },
                Modifier.fillMaxWidth()
            )
        }
        if (showGuidance) {
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Krishna-inspired reflection", color = LightGold, style = MaterialTheme.typography.titleLarge)
                        Text(
                            "First, breathe and name what is within your control. Ask which choice protects truth, responsibility and compassion—not merely comfort. Take the smallest courageous action you can today, then release the result. You are responsible for sincere effort, not for controlling every outcome.",
                            color = SoftWhite,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text("Your situation: ${answer.trim()}", color = MutedText, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
