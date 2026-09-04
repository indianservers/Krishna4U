package com.indianservers.krishna4u.feature.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.KrishnaCosmicBackground
import com.indianservers.krishna4u.core.design.SacredBottomNavigation
import com.indianservers.krishna4u.feature.wisdom.exploreShortcut
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.LocalReducedMotion
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite
import java.time.LocalTime
import kotlinx.coroutines.delay

private data class HomeCard(val title: String, val body: String, val icon: Int, val route: String)

fun isNightMessageTime(deviceTime: LocalTime): Boolean = deviceTime.hour >= 20

@Composable
fun HomeScreen(
    displayName: String,
    selectedNeeds: Set<String>,
    readSlokaCount: Int,
    homeShortcuts: Set<String>,
    onToggleHomeShortcut: (String) -> Unit,
    onDisplayNameChanged: (String) -> Unit,
    onOpen: (String) -> Unit
) {
    val effectiveName = displayName.trim().ifBlank { "Seeker" }
    var showRenameDialog by remember { mutableStateOf(false) }
    var nameDraft by remember(effectiveName) { mutableStateOf(effectiveName) }
    val reducedMotion = LocalReducedMotion.current
    val featuredVerse = remember { homeVerses.random() }
    val deviceTime by produceState(initialValue = LocalTime.now()) {
        while (true) {
            value = LocalTime.now()
            delay(30_000)
        }
    }
    val showNightMessage = isNightMessageTime(deviceTime)
    val pinnedShortcuts = remember(homeShortcuts) {
        homeShortcuts.mapNotNull(::exploreShortcut).sortedBy { it.title }
    }
    val chakraTransition = rememberInfiniteTransition(label = "settingsChakra")
    val chakraRotation by chakraTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(30000, easing = LinearEasing)),
        label = "settingsChakraRotation"
    )
    val cards = listOf(
        HomeCard(androidx.compose.ui.res.stringResource(R.string.home_krishna_life), androidx.compose.ui.res.stringResource(R.string.home_krishna_life_desc), R.drawable.home_icon_krishna_life, "06"),
        HomeCard(androidx.compose.ui.res.stringResource(R.string.home_teachings), androidx.compose.ui.res.stringResource(R.string.home_teachings_desc), R.drawable.home_icon_teachings, "08"),
        HomeCard(androidx.compose.ui.res.stringResource(R.string.home_bhagavad_gita), androidx.compose.ui.res.stringResource(R.string.home_bhagavad_gita_desc), R.drawable.home_icon_bhagavad_gita, "12"),
        HomeCard(androidx.compose.ui.res.stringResource(R.string.home_ask_krishna), androidx.compose.ui.res.stringResource(R.string.home_ask_krishna_desc), R.drawable.home_icon_ask_krishna, "22")
    )
    KrishnaCosmicBackground(R.drawable.bg_01_cosmic_mandala) {
        Column(Modifier.fillMaxSize().statusBarsPadding()) {
            Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painterResource(R.drawable.icon_peacock_feather), null, Modifier.size(52.dp).clip(CircleShape))
                Column(Modifier.weight(1f).padding(start = 12.dp)) {
                    Row(
                        Modifier.fillMaxWidth().clickable {
                            nameDraft = effectiveName
                            showRenameDialog = true
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            androidx.compose.ui.res.stringResource(R.string.home_namaste, effectiveName),
                            modifier = Modifier.weight(1f),
                            color = LightGold,
                            style = MaterialTheme.typography.headlineMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text("✎", color = AntiqueGold, fontSize = 20.sp)
                    }
                    Text(androidx.compose.ui.res.stringResource(R.string.home_blessing), color = MutedText)
                }
                Image(
                    painterResource(R.drawable.icon_settings),
                    "Open profile settings",
                    Modifier.size(38.dp).rotate(if (reducedMotion) 0f else chakraRotation).clickable { onOpen("28") }
                )
            }
            LazyColumn(Modifier.weight(1f), contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 18.dp, vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    GlassCard(
                        Modifier.fillMaxWidth().height(310.dp),
                        onClick = { onOpen("gita_verse/${featuredVerse.chapter}/${featuredVerse.verse}") }
                    ) {
                        Image(
                            painterResource(R.drawable.illustration_02_krishna_portrait),
                            null,
                            Modifier.align(Alignment.CenterEnd).fillMaxWidth(.56f).fillMaxHeight(),
                            contentScale = ContentScale.Fit
                        )
                        Column(Modifier.align(Alignment.CenterStart).fillMaxWidth(.44f)) {
                            Text("“", color = LightGold, style = MaterialTheme.typography.headlineLarge)
                            Text(featuredVerse.quote, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                            Spacer(Modifier.height(8.dp))
                            Text(featuredVerse.reference, color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                            Image(painterResource(R.drawable.icon_play), "Open verse", Modifier.padding(top = 8.dp).size(38.dp))
                        }
                    }
                }
                item {
                    GlassCard(Modifier.fillMaxWidth()) {
                        val completed = readSlokaCount.coerceIn(0, 700)
                        val progress = completed / 700f
                        val percentage = progress * 100f
                        Column(Modifier.fillMaxWidth()) {
                            Text(androidx.compose.ui.res.stringResource(R.string.home_gita_journey), color = SoftWhite, style = MaterialTheme.typography.titleLarge)
                            Text(
                                if (completed == 0) androidx.compose.ui.res.stringResource(R.string.home_begin_sloka_journey)
                                else androidx.compose.ui.res.stringResource(R.string.home_sloka_progress, completed, "%.1f".format(percentage)),
                                color = MutedText,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.height(10.dp))
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
                                color = AntiqueGold,
                                trackColor = SoftWhite.copy(alpha = .16f),
                                drawStopIndicator = {}
                            )
                        }
                    }
                }
                items(2) { rowIndex ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        cards.slice(rowIndex * 2..rowIndex * 2 + 1).forEach { card ->
                            GlassCard(
                                Modifier.weight(1f).height(140.dp),
                                onClick = { onOpen(card.route) },
                                contentPadding = 10.dp
                            ) {
                                Column(Modifier.fillMaxSize()) {
                                    Text(
                                        card.title,
                                        color = LightGold,
                                        fontSize = 18.sp,
                                        lineHeight = 21.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            card.body,
                                            Modifier.weight(1f),
                                            color = MutedText,
                                            fontSize = 15.sp,
                                            lineHeight = 20.sp,
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Spacer(Modifier.width(5.dp))
                                        Image(
                                            painterResource(card.icon),
                                            card.title,
                                            Modifier.size(54.dp).clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                if (pinnedShortcuts.isNotEmpty()) {
                    item {
                        Text(androidx.compose.ui.res.stringResource(R.string.home_explore_shortcuts), color = LightGold, style = MaterialTheme.typography.titleLarge)
                    }
                    items(pinnedShortcuts, key = { it.route }) { shortcut ->
                        GlassCard(Modifier.fillMaxWidth()) {
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Row(
                                    Modifier.weight(1f).clickable { onOpen(shortcut.route) },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(painterResource(shortcut.icon), null, Modifier.size(34.dp))
                                    Column(Modifier.padding(horizontal = 10.dp)) {
                                        Text(shortcut.title, color = LightGold, style = MaterialTheme.typography.titleMedium)
                                        Text(shortcut.description, color = MutedText, style = MaterialTheme.typography.bodySmall, maxLines = 1)
                                    }
                                }
                                Image(
                                    painterResource(R.drawable.icon_bookmark),
                                    "Remove ${shortcut.title} from Home",
                                    Modifier.size(25.dp).clickable { onToggleHomeShortcut(shortcut.route) }
                                )
                            }
                        }
                    }
                }
                item {
                    GlassCard(Modifier.fillMaxWidth().height(106.dp), onClick = { onOpen("krishna_speaks") }, contentPadding = 10.dp) {
                        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(R.drawable.illustration_10_flute_feather),
                                null,
                                Modifier.size(82.dp),
                                contentScale = ContentScale.Fit
                            )
                            Column(Modifier.weight(1f).padding(start = 4.dp)) {
                                Text(androidx.compose.ui.res.stringResource(R.string.home_krishna_speaks), color = LightGold, style = MaterialTheme.typography.titleLarge, fontSize = 17.sp, maxLines = 1)
                                Text(selectedNeeds.firstOrNull()?.let { androidx.compose.ui.res.stringResource(R.string.home_message_for, it) } ?: androidx.compose.ui.res.stringResource(R.string.home_message_for_today), color = MutedText, maxLines = 1)
                            }
                            Text("→", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                        }
                    }
                }
                item {
                    GlassCard(Modifier.fillMaxWidth().height(118.dp), onClick = { onOpen("krishna_letters") }) {
                        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(R.drawable.icon_compassion),
                                androidx.compose.ui.res.stringResource(R.string.home_krishna_letters),
                                Modifier.size(64.dp),
                                contentScale = ContentScale.Fit
                            )
                            Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                                Text(androidx.compose.ui.res.stringResource(R.string.home_krishna_letters), color = LightGold, style = MaterialTheme.typography.titleLarge)
                                Text(androidx.compose.ui.res.stringResource(R.string.home_krishna_letters_desc), color = MutedText, style = MaterialTheme.typography.bodyMedium)
                            }
                            Text("→", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                        }
                    }
                }
                if (showNightMessage) {
                    item {
                        GlassCard(Modifier.fillMaxWidth().height(118.dp), onClick = { onOpen("night_message") }) {
                            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(R.drawable.letters_icon_star),
                                    androidx.compose.ui.res.stringResource(R.string.home_night_message),
                                    Modifier.size(64.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                                    Text(androidx.compose.ui.res.stringResource(R.string.home_night_message), color = LightGold, style = MaterialTheme.typography.titleLarge)
                                    Text(androidx.compose.ui.res.stringResource(R.string.home_night_message_desc), color = MutedText, style = MaterialTheme.typography.bodyMedium)
                                }
                                Text("→", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                            }
                        }
                    }
                }
            }
            SacredBottomNavigation(onOpen)
        }
        if (showRenameDialog) {
            AlertDialog(
                onDismissRequest = { showRenameDialog = false },
                title = { Text(androidx.compose.ui.res.stringResource(R.string.edit_display_name)) },
                text = {
                    OutlinedTextField(
                        value = nameDraft,
                        onValueChange = { nameDraft = it.take(40) },
                        label = { Text(androidx.compose.ui.res.stringResource(R.string.display_name)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        val savedName = nameDraft.trim().ifBlank { "Seeker" }
                        onDisplayNameChanged(savedName)
                        showRenameDialog = false
                    }) { Text(androidx.compose.ui.res.stringResource(R.string.action_save)) }
                },
                dismissButton = {
                    TextButton(onClick = { showRenameDialog = false }) {
                        Text(androidx.compose.ui.res.stringResource(R.string.action_cancel))
                    }
                }
            )
        }
    }
}
