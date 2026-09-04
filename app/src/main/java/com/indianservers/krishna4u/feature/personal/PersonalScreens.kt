package com.indianservers.krishna4u.feature.personal

import android.Manifest
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.localization.appLanguage
import com.indianservers.krishna4u.core.localization.appLanguages
import com.indianservers.krishna4u.core.localization.readingMode
import com.indianservers.krishna4u.core.localization.readingModes
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.data.repository.OfflineGitaRepository
import com.indianservers.krishna4u.feature.guidance.krishnaMessageById
import com.indianservers.krishna4u.feature.krishnalife.lifeEvent
import com.indianservers.krishna4u.feature.teachings.teachingById
import com.indianservers.krishna4u.ui.theme.*

private data class SavedBookmark(val id: String, val title: String, val body: String, val icon: Int, val route: String)

@Composable
fun JournalScreen(
    bookmarks: Set<String>,
    reflections: Set<String>,
    onToggleBookmark: (String) -> Unit,
    onSaveReflection: (String) -> Unit,
    onDeleteReflection: (String) -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val gitaRepository = remember { OfflineGitaRepository(context.applicationContext) }
    var tab by remember { mutableStateOf("Slokas") }; var note by remember { mutableStateOf("") }; var saved by remember { mutableStateOf(false) }
    val savedReflections = remember(reflections) { reflections.sortedByDescending { it.substringBefore('|').toLongOrNull() ?: 0L } }
    val savedItems = remember(bookmarks) {
        bookmarks.sorted().mapNotNull { id ->
            when {
                id.startsWith("sloka:") -> {
                    val parts = id.removePrefix("sloka:").split('.')
                    val chapter = parts.getOrNull(0)?.toIntOrNull()
                    val verse = parts.getOrNull(1)?.toIntOrNull()
                    if (chapter != null && verse != null) gitaRepository.verse(chapter, verse)?.let {
                        SavedBookmark(id, "Bhagavad Gita $chapter.$verse", it.englishSummary, R.drawable.icon_bookmark, "gita_verse/$chapter/$verse")
                    } else null
                }
                id.startsWith("teaching:") -> teachingById(id.removePrefix("teaching:")).let { teaching -> SavedBookmark(id, teaching.title, teaching.summary, teaching.icon, "teaching/${teaching.id}") }
                id.startsWith("life:") -> lifeEvent(id.removePrefix("life:")).let { event -> SavedBookmark(id, event.title, event.subtitle, event.icon, "07/${event.id}") }
                id.startsWith("message:") -> krishnaMessageById(id.removePrefix("message:"))?.let { message -> SavedBookmark(id, message.situation, message.text, R.drawable.icon_lotus, "krishna_speaks") }
                else -> null
            }
        }
    }
    val visibleItems = savedItems.filter { if (tab == "Slokas") it.id.startsWith("sloka:") else !it.id.startsWith("sloka:") }
    FeatureScaffold("My Sacred Space", "Wisdom you choose to keep", R.drawable.bg_08_minimal_starfield, onBack, onNavigate) {
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) { listOf("Slokas", "Wisdom", "Reflections").forEach { SpiritualChip(it, R.drawable.icon_journal, tab == it, { tab = it }, Modifier.weight(1f)) } } }
        item { SacredHero(R.drawable.illustration_07_open_gita, tab, "Your private, local collection for study and reflection.") }
        if (tab != "Reflections") {
            if (visibleItems.isEmpty()) item { GlassCard(Modifier.fillMaxWidth()) { Text(if (tab == "Slokas") "No saved slokas yet. Tap Bookmark while reading any sloka." else "No saved wisdom yet. Bookmark a teaching, Krishna message, or Life story.", color = MutedText, style = MaterialTheme.typography.bodyLarge) } }
            items(visibleItems, key = { it.id }) { bookmark ->
                GlassCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(verticalAlignment = Alignment.CenterVertically) { SacredIcon(bookmark.icon, null, Modifier.size(38.dp)); Column(Modifier.weight(1f).padding(start = 12.dp)) { Text(bookmark.title, color = LightGold, style = MaterialTheme.typography.titleLarge); Text(bookmark.body, color = MutedText, maxLines = 3) } }
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SecondarySacredButton("Remove", { onToggleBookmark(bookmark.id) }, Modifier.weight(1f))
                            SecondarySacredButton("Share", { shareSacredText(context, bookmark.title, "${bookmark.title}\n\n${bookmark.body}\n\nShared from Krishna For You") }, Modifier.weight(1f))
                            PrimaryGoldButton("Open", { onNavigate(bookmark.route) }, Modifier.weight(1f))
                        }
                    }
                }
            }
        } else {
            item { OutlinedTextField(note, { note = it; saved = false }, label = { Text("Write a private reflection") }, modifier = Modifier.fillMaxWidth(), minLines = 3) }
            item {
                PrimaryGoldButton(if (saved) "Reflection Saved" else "Save Reflection", {
                    if (note.isNotBlank()) {
                        onSaveReflection(note)
                        note = ""
                        saved = true
                    }
                }, Modifier.fillMaxWidth())
            }
            if (savedReflections.isEmpty()) {
                item { GlassCard(Modifier.fillMaxWidth()) { Text("Your saved reflections will appear here and stay on this device.", color = MutedText) } }
            } else {
                items(savedReflections, key = { it }) { entry ->
                    GlassCard(Modifier.fillMaxWidth()) {
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(entry.substringAfter('|'), color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                            SecondarySacredButton("Delete Reflection", { onDeleteReflection(entry) }, Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LearningProgressScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) = FeatureScaffold("Your Inner Journey", "Learning with Krishna · 28 days", R.drawable.bg_09_lotus_reflection, onBack, onNavigate) {
    item { SacredHero(R.drawable.illustration_08_wisdom_tree, "Level 4 · Seeker", "Your growth is measured by sincere practice, not comparison.") }
    item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { listOf("4 / 18\nChapters", "126\nSlokas", "18\nReflections").forEach { GlassCard(Modifier.weight(1f)) { Text(it, color = LightGold) } } } }
    items(listOf(Triple("Gita progress", "Chapter 4 of 18", R.drawable.icon_gita), Triple("Daily rhythm", "7-day reflection streak", R.drawable.icon_calendar), Triple("Meditation", "82 minutes of stillness", R.drawable.icon_meditation), Triple("Dharma milestone", "You completed 10 guided reflections", R.drawable.icon_dharma))) { x -> SacredListCard(x.first, x.second, x.third) }
    item { PrimaryGoldButton("Continue Your Journey", { onNavigate("24") }, Modifier.fillMaxWidth()) }
}

@Composable
fun ProfileSettingsScreen(
    displayName: String,
    language: String,
    textSize: String,
    readingModeId: String,
    notificationsEnabled: Boolean,
    notificationHour: Int,
    notificationMinute: Int,
    bedtimeMessageEnabled: Boolean,
    bedtimeHour: Int,
    bedtimeMinute: Int,
    darkTheme: Boolean,
    reducedMotion: Boolean,
    onSaveDisplayName: (String) -> Unit,
    onLanguageChanged: (String) -> Unit,
    onTextSizeChanged: (String) -> Unit,
    onReadingModeChanged: (String) -> Unit,
    onNotificationsChanged: (Boolean) -> Unit,
    onNotificationTimeChanged: (Int, Int) -> Unit,
    onBedtimeMessageChanged: (Boolean) -> Unit,
    onBedtimeChanged: (Int, Int) -> Unit,
    onDarkThemeChanged: (Boolean) -> Unit,
    onReducedMotionChanged: (Boolean) -> Unit,
    onResetJourney: () -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    var nameDraft by remember(displayName) { mutableStateOf(displayName) }
    var nameSaved by remember { mutableStateOf(false) }
    var selectionDialog by remember { mutableStateOf<String?>(null) }
    var informationDialog by remember { mutableStateOf<String?>(null) }
    var confirmReset by remember { mutableStateOf(false) }
    val notificationPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        onNotificationsChanged(granted)
    }
    val bedtimePermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        onBedtimeMessageChanged(granted)
    }
    LaunchedEffect(notificationsEnabled) {
        if (
            notificationsEnabled &&
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
    fun requestNotifications(enabled: Boolean) {
        if (!enabled) {
            onNotificationsChanged(false)
        } else if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            onNotificationsChanged(true)
        }
    }
    fun requestBedtimeMessage(enabled: Boolean) {
        if (!enabled) {
            onBedtimeMessageChanged(false)
        } else if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            bedtimePermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            onBedtimeMessageChanged(true)
        }
    }
    val formattedTime = remember(notificationHour, notificationMinute) {
        val hour = when {
            notificationHour == 0 -> 12
            notificationHour > 12 -> notificationHour - 12
            else -> notificationHour
        }
        "%d:%02d %s".format(hour, notificationMinute, if (notificationHour < 12) "AM" else "PM")
    }
    val formattedBedtime = remember(bedtimeHour, bedtimeMinute) {
        val hour = when {
            bedtimeHour == 0 -> 12
            bedtimeHour > 12 -> bedtimeHour - 12
            else -> bedtimeHour
        }
        "%d:%02d %s".format(hour, bedtimeMinute, if (bedtimeHour < 12) "AM" else "PM")
    }
    FeatureScaffold("Your Journey, Your Way", "Profile & settings", R.drawable.bg_08_minimal_starfield, onBack, onNavigate) {
        item { SacredHero(R.drawable.illustration_02_krishna_portrait, displayName, "Seeker of wisdom") }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth()) {
                    Text("Your name", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    Text("Used for your personal greeting. Stored only on this device.", color = MutedText)
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = nameDraft,
                        onValueChange = {
                            nameDraft = it.take(40)
                            nameSaved = false
                        },
                        label = { Text("Display name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                    PrimaryGoldButton(
                        text = if (nameSaved) "Name Saved" else "Save Name",
                        onClick = {
                            nameDraft = nameDraft.trim().ifBlank { "Seeker" }
                            onSaveDisplayName(nameDraft)
                            nameSaved = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        item { SacredListCard("Language", appLanguage(language).nativeName, R.drawable.icon_language, onClick = { selectionDialog = "Language" }) }
        item { SacredListCard("Age-based reading mode", "${readingMode(readingModeId).title} · ${readingMode(readingModeId).ageRange}", R.drawable.icon_relationships, onClick = { selectionDialog = "Reading mode" }) }
        item { SacredListCard("Text size", textSize, R.drawable.icon_font_size, onClick = { selectionDialog = "Text size" }) }
        item { ToggleCard("Daily notifications", "A Gita verse each day at $formattedTime", R.drawable.icon_notification, notificationsEnabled, ::requestNotifications) }
        if (notificationsEnabled) {
            item {
                SacredListCard("Notification time", formattedTime, R.drawable.icon_timer, onClick = {
                    TimePickerDialog(
                        context,
                        { _, hour, minute -> onNotificationTimeChanged(hour, minute) },
                        notificationHour,
                        notificationMinute,
                        false
                    ).show()
                })
            }
        }
        item { ToggleCard("Krishna’s Night Message", "A calming reflection each night at $formattedBedtime", R.drawable.letters_icon_star, bedtimeMessageEnabled, ::requestBedtimeMessage) }
        if (bedtimeMessageEnabled) {
            item {
                SacredListCard("Bedtime", formattedBedtime, R.drawable.icon_timer, onClick = {
                    TimePickerDialog(
                        context,
                        { _, hour, minute -> onBedtimeChanged(hour, minute) },
                        bedtimeHour,
                        bedtimeMinute,
                        false
                    ).show()
                })
            }
        }
        item { SacredListCard("Preview Night Message", "Moonlight, stars and a quiet reflection for the end of your day", R.drawable.icon_lotus, onClick = { onNavigate("night_message") }) }
        item { ToggleCard("Sacred dark theme", "Use the cosmic midnight palette", R.drawable.icon_theme, darkTheme, onDarkThemeChanged) }
        item { ToggleCard("Reduced motion", "Minimise decorative and transition animations", R.drawable.icon_settings, reducedMotion, onReducedMotionChanged) }
        item { SacredListCard("Privacy", "Notes and preferences stay on this device", R.drawable.icon_privacy, onClick = { informationDialog = "Privacy" }) }
        item { SacredListCard("About App", "From Sai Satish Damaraju · Indian Servers Pvt Ltd", R.drawable.icon_info, onClick = { informationDialog = "About App" }) }
        item { SecondarySacredButton("Reset Journey", { confirmReset = true }, Modifier.fillMaxWidth()) }
    }

    selectionDialog?.let { type ->
        AlertDialog(
            onDismissRequest = { selectionDialog = null },
            title = { Text("Choose $type") },
            text = {
                if (type == "Language") {
                    LazyColumn(Modifier.heightIn(max = 420.dp)) {
                        items(appLanguages, key = { it.code }) { choice ->
                            TextButton(
                                enabled = choice.available,
                                onClick = {
                                    onLanguageChanged(choice.code)
                                    selectionDialog = null
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                                    Text(choice.nativeName, modifier = Modifier.fillMaxWidth())
                                    if (choice.englishName != choice.nativeName) Text(choice.englishName, style = MaterialTheme.typography.bodyMedium)
                                }
                                if (!choice.available) Text("Coming soon", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                } else if (type == "Reading mode") {
                    Column {
                        readingModes.forEach { mode ->
                            TextButton(
                                onClick = {
                                    onReadingModeChanged(mode.id)
                                    selectionDialog = null
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                                    Text("${mode.title} · ${mode.ageRange}", style = MaterialTheme.typography.titleMedium)
                                    Text(mode.description, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                } else {
                    Column {
                        listOf("Compact", "Comfortable", "Large").forEach { choice ->
                        TextButton(
                            onClick = {
                                onTextSizeChanged(choice)
                                selectionDialog = null
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(choice, modifier = Modifier.fillMaxWidth()) }
                        }
                    }
                }
            },
            confirmButton = { TextButton({ selectionDialog = null }) { Text("Cancel") } }
        )
    }
    informationDialog?.let { type ->
        AlertDialog(
            onDismissRequest = { informationDialog = null },
            title = { Text(type) },
            text = {
                if (type == "Privacy") {
                    Text("Your name, settings, bookmarks and reflections are stored locally on this device. Krishna For You does not require an account or send this information to a remote service.")
                } else {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 520.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item { Text("Krishna For You", style = MaterialTheme.typography.headlineSmall, color = AntiqueGold) }
                        item { Text("From Sai Satish Damaraju, CEO, Indian Servers Pvt Ltd", style = MaterialTheme.typography.titleMedium, color = LightGold) }
                        item { HorizontalDivider(color = AntiqueGold.copy(alpha = 0.45f)) }
                        item { Text("From Krishna, Through SaiSatish", style = MaterialTheme.typography.titleLarge, color = AntiqueGold) }
                        item {
                            Text(
                                "Krishna For You is more than an app—it is a journey into the heart of the Bhagavad Gita and the life of Krishna as a mentor, friend, and guide. With each chapter, discover how ancient wisdom meets modern challenges—from confusion to courage, doubt to clarity, and fear to faith.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        item {
                            Text(
                                "Designed for children, youth, and seekers of all ages, it is a timeless capsule of values, strength, and purpose. Every page is filled with practical insights, moral clarity, and inspiration drawn from Krishna’s words and actions.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        item { Text("Version 1.0 · Local-first and private by design", style = MaterialTheme.typography.labelLarge, color = MutedText) }
                    }
                }
            },
            confirmButton = { TextButton({ informationDialog = null }) { Text("Done") } }
        )
    }
    if (confirmReset) {
        AlertDialog(
            onDismissRequest = { confirmReset = false },
            title = { Text("Reset your journey?") },
            text = { Text("This clears your local name, choices, bookmarks and settings and returns to personalisation.") },
            dismissButton = { TextButton({ confirmReset = false }) { Text("Cancel") } },
            confirmButton = {
                TextButton({ confirmReset = false; onResetJourney() }) { Text("Reset") }
            }
        )
    }
}

@Composable
private fun ToggleCard(title: String, body: String, icon: Int, checked: Boolean, onChecked: (Boolean) -> Unit) {
    GlassCard(Modifier.fillMaxWidth()) { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { SacredIcon(icon, null, Modifier.size(40.dp)); Column(Modifier.weight(1f).padding(horizontal = 12.dp)) { Text(title, color = LightGold, style = MaterialTheme.typography.titleLarge); Text(body, color = MutedText) }; Switch(checked, onChecked) } }
}
