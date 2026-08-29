package com.indianservers.krishna4u.feature.teachings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.*

@Composable
fun TeachingsLibraryScreen(
    bookmarks: Set<String>,
    onToggleBookmark: (String) -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }
    var query by remember { mutableStateOf("") }
    val activeFilter = teachingFilters.first { it.label == selectedFilter }
    val visibleTeachings = teachingLibrary.filter { teaching ->
        activeFilter.matches(teaching) &&
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
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 2.dp)) {
                items(teachingFilters, key = { it.label }) { filter ->
                    SpiritualChip(
                        filter.label,
                        filter.icon,
                        selectedFilter == filter.label,
                        { selectedFilter = filter.label }
                    )
                }
            }
        }
        item {
            TeachingWisdomWheel(
                selectedFilter = selectedFilter,
                onSelect = { selectedFilter = it }
            )
        }
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    if (selectedFilter == "All") "Explore ${visibleTeachings.size} teachings" else "$selectedFilter · ${visibleTeachings.size} teachings",
                    Modifier.weight(1f),
                    color = LightGold,
                    style = MaterialTheme.typography.headlineSmall
                )
                if (selectedFilter != "All" || query.isNotBlank()) {
                    Text(
                        "Clear",
                        color = AntiqueGold,
                        modifier = Modifier.clickable { selectedFilter = "All"; query = "" }.padding(8.dp)
                    )
                }
            }
        }
        items(visibleTeachings, key = { it.id }) { teaching ->
            TeachingLibraryCard(
                teaching = teaching,
                bookmarked = "teaching:${teaching.id}" in bookmarks,
                onBookmark = { onToggleBookmark(teaching.id) },
                onOpen = { onNavigate("teaching/${teaching.id}") }
            )
        }
        if (visibleTeachings.isEmpty()) item { SacredListCard("No teachings found", "Try a shorter phrase or choose All.", R.drawable.icon_search) }
    }
}

private data class TeachingFilter(
    val label: String,
    val icon: Int,
    val matches: (TeachingUi) -> Boolean
)

private val teachingFilters = listOf(
    TeachingFilter("All", R.drawable.icon_lotus) { true },
    TeachingFilter("Dharma", R.drawable.icon_dharma) { it.category == "Daily Dharma" },
    TeachingFilter("Karma", R.drawable.icon_karma) { it.category == "Gita Wisdom" && (it.icon == R.drawable.icon_karma || it.title.contains("action", true) || it.teaching.contains("action", true)) },
    TeachingFilter("Mind", R.drawable.icon_mind) { it.icon == R.drawable.icon_mind || listOf(it.title, it.summary, it.teaching).any { text -> text.contains("mind", true) || text.contains("attention", true) } },
    TeachingFilter("Love", R.drawable.icon_love) { it.category == "Compassion" || it.icon == R.drawable.icon_compassion || it.icon == R.drawable.icon_love },
    TeachingFilter("Leadership", R.drawable.icon_leadership) { it.icon == R.drawable.icon_leadership || it.title.contains("lead", true) || it.teaching.contains("leader", true) },
    TeachingFilter("Guidance", R.drawable.icon_teachings) { it.category == "Krishna’s Guidance" }
)

@Composable
private fun TeachingWisdomWheel(selectedFilter: String, onSelect: (String) -> Unit) {
    val nodes = teachingFilters.drop(1)
    GlassCard(Modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth().height(320.dp), contentAlignment = Alignment.Center) {
            AnimatedMandalaHalo(Modifier.size(270.dp))
            Image(
                painterResource(R.drawable.illustration_02_krishna_portrait),
                "Krishna at the centre of the wisdom paths",
                Modifier.size(145.dp),
                contentScale = ContentScale.Fit
            )
            WisdomNode(nodes[0], selectedFilter, onSelect, Modifier.align(Alignment.TopStart).padding(start = 24.dp))
            WisdomNode(nodes[1], selectedFilter, onSelect, Modifier.align(Alignment.TopEnd).padding(end = 24.dp))
            WisdomNode(nodes[2], selectedFilter, onSelect, Modifier.align(Alignment.CenterEnd))
            WisdomNode(nodes[3], selectedFilter, onSelect, Modifier.align(Alignment.BottomEnd).padding(end = 24.dp))
            WisdomNode(nodes[4], selectedFilter, onSelect, Modifier.align(Alignment.BottomStart).padding(start = 24.dp))
            WisdomNode(nodes[5], selectedFilter, onSelect, Modifier.align(Alignment.CenterStart))
        }
    }
}

@Composable
private fun WisdomNode(filter: TeachingFilter, selectedFilter: String, onSelect: (String) -> Unit, modifier: Modifier = Modifier) {
    val selected = filter.label == selectedFilter
    Column(
        modifier
            .width(76.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (selected) AntiqueGold.copy(alpha = .22f) else CosmicMidnight.copy(alpha = .72f))
            .border(1.dp, if (selected) LightGold else AntiqueGold.copy(alpha = .55f), RoundedCornerShape(18.dp))
            .clickable { onSelect(if (selected) "All" else filter.label) }
            .padding(vertical = 7.dp, horizontal = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SacredIcon(filter.icon, filter.label, Modifier.size(34.dp))
        Text(filter.label, color = if (selected) LightGold else SoftWhite, style = MaterialTheme.typography.labelSmall, maxLines = 1)
    }
}

@Composable
private fun TeachingLibraryCard(
    teaching: TeachingUi,
    bookmarked: Boolean,
    onBookmark: () -> Unit,
    onOpen: () -> Unit
) {
    GlassCard(Modifier.fillMaxWidth().height(148.dp), onClick = onOpen) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(teachingArtwork(teaching)),
                null,
                Modifier.fillMaxHeight().width(112.dp).clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.weight(1f).padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(teaching.title, color = LightGold, style = MaterialTheme.typography.titleLarge, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(teachingFilterLabel(teaching), color = AntiqueGold, style = MaterialTheme.typography.labelLarge)
                Text("◷ ${teachingReadMinutes(teaching)} min read", color = MutedText, style = MaterialTheme.typography.bodyMedium)
            }
            Box(
                Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(if (bookmarked) AntiqueGold.copy(alpha = .24f) else CosmicMidnight.copy(alpha = .65f))
                    .clickable(onClick = onBookmark),
                contentAlignment = Alignment.Center
            ) {
                SacredIcon(R.drawable.icon_bookmark, if (bookmarked) "Remove bookmark" else "Bookmark teaching", Modifier.size(27.dp))
            }
        }
    }
}

private fun teachingArtwork(teaching: TeachingUi): Int = when {
    teaching.icon == R.drawable.icon_mind -> R.drawable.illustration_06_meditating_seeker
    teaching.icon == R.drawable.icon_leadership || teaching.icon == R.drawable.icon_courage -> R.drawable.illustration_03_krishna_arjuna_chariot
    teaching.category == "Gita Wisdom" -> R.drawable.illustration_07_open_gita
    teaching.category == "Compassion" -> R.drawable.illustration_02_krishna_portrait
    teaching.icon == R.drawable.icon_peacock_feather -> R.drawable.illustration_09_peacock_feather
    else -> R.drawable.illustration_01_krishna_full_body
}

private fun teachingFilterLabel(teaching: TeachingUi): String = when {
    teaching.icon == R.drawable.icon_leadership -> "Leadership"
    teaching.icon == R.drawable.icon_mind -> "Inner Peace"
    teaching.icon == R.drawable.icon_karma -> "Karma Yoga"
    teaching.category == "Compassion" -> "Love & Compassion"
    teaching.category == "Daily Dharma" -> "Dharma"
    else -> teaching.category
}

internal fun teachingReadMinutes(teaching: TeachingUi): Int {
    val words = sequenceOf(teaching.summary, teaching.question, teaching.teaching, teaching.practice)
        .plus(teaching.takeaways.asSequence())
        .sumOf { text -> text.split(Regex("\\s+")).count { it.isNotBlank() } }
    return (words / 150.0).toInt().coerceAtLeast(3)
}

@Composable
fun TeachingDetailsScreen(teachingId: String, bookmarked: Boolean, onToggleBookmark: () -> Unit, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val teaching = teachingById(teachingId)
    FeatureScaffold("TEACHING · ${teaching.category.uppercase()}", teaching.source, R.drawable.bg_03_kurukshetra_cosmos, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, teaching.title, teaching.question) }
        item { SacredListCard("Teaching", teaching.teaching, R.drawable.icon_teachings) }
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
        item { SacredListCard("Try this today", teaching.practice, R.drawable.icon_strategy) }
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
        items(reflectionSteps) { step -> SacredListCard(step, "A step in your reflection", R.drawable.icon_check) }
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
