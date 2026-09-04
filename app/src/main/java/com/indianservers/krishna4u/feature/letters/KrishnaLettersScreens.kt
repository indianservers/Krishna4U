package com.indianservers.krishna4u.feature.letters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite
import com.indianservers.krishna4u.ui.theme.LocalReducedMotion

@Composable
fun KrishnaLettersLibraryScreen(displayName: String, languageCode: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val readerName = displayName.trim().ifBlank { stringResource(R.string.letters_default_reader) }
    val localizedLetters = remember(languageCode) { localizedKrishnaLetters(languageCode) }
    var query by remember { mutableStateOf("") }
    var selectedAudience by remember { mutableStateOf("All") }
    val visibleLetters = remember(query, selectedAudience, localizedLetters) {
        localizedLetters.filter { letter ->
            val searchableText = listOf(letter.situation, letter.title, letter.preview) + letter.paragraphs
            val matchesQuery = query.isBlank() || searchableText.any { it.contains(query, ignoreCase = true) }
            val matchesAudience = selectedAudience == "All" || selectedAudience in letter.source.audiences
            matchesQuery && matchesAudience
        }
    }
    FeatureScaffold(stringResource(R.string.letters_title), stringResource(R.string.letters_subtitle), R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item {
            SacredHero(
                R.drawable.letters_envelope_hero,
                stringResource(R.string.letters_dear_reader, readerName),
                stringResource(R.string.letters_hero_guidance)
            )
        }
        item { OutlinedTextField(query, { query = it }, label = { Text(stringResource(R.string.letters_search_prompt)) }, leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(24.dp)) }, singleLine = true, modifier = Modifier.fillMaxWidth()) }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(7.dp), contentPadding = PaddingValues(horizontal = 2.dp)) {
                items(listOf("All") + krishnaLetterAudiences) { audience ->
                    SpiritualChip(
                        audienceLabel(audience),
                        audienceIcon(audience),
                        selectedAudience == audience,
                        { selectedAudience = audience },
                        Modifier.widthIn(min = 94.dp)
                    )
                }
            }
        }
        items(visibleLetters) { letter ->
            SacredListCard(
                letter.situation,
                "${letter.source.audiences.joinToString(" · ") { audienceLabelText(it, languageCode) }}\n${letter.preview}",
                letter.source.icon,
                { onNavigate("krishna_letters/${letter.source.id}") }
            )
        }
        if (visibleLetters.isEmpty()) item { GlassCard(Modifier.fillMaxWidth()) { Text(stringResource(R.string.letters_no_match), color = MutedText) } }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Text(
                    stringResource(R.string.letters_disclaimer),
                    color = MutedText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun audienceLabel(audience: String): String = stringResource(audienceLabelResource(audience))

private fun audienceLabelText(audience: String, languageCode: String): String = when (languageCode) {
    "te" -> mapOf("Children" to "పిల్లలు", "Students" to "విద్యార్థులు", "Youth" to "యువత", "Parents" to "తల్లిదండ్రులు", "Professionals" to "ఉద్యోగులు", "Couples" to "దంపతులు", "Elders" to "పెద్దలు", "Grief & Illness" to "దుఃఖం & అనారోగ్యం", "Caregivers" to "సంరక్షకులు", "Leaders" to "నాయకులు", "Spiritual Seekers" to "ఆధ్యాత్మిక సాధకులు", "New Beginnings" to "కొత్త ఆరంభాలు")[audience] ?: audience
    "hi" -> mapOf("Children" to "बच्चे", "Students" to "विद्यार्थी", "Youth" to "युवा", "Parents" to "माता-पिता", "Professionals" to "पेशेवर", "Couples" to "दंपति", "Elders" to "बुज़ुर्ग", "Grief & Illness" to "शोक और बीमारी", "Caregivers" to "देखभाल करने वाले", "Leaders" to "नेता", "Spiritual Seekers" to "आध्यात्मिक साधक", "New Beginnings" to "नई शुरुआत")[audience] ?: audience
    else -> audience
}

private fun audienceLabelResource(audience: String): Int = when (audience) {
    "Children" -> R.string.letters_audience_children
    "Students" -> R.string.letters_audience_students
    "Youth" -> R.string.letters_audience_youth
    "Parents" -> R.string.letters_audience_parents
    "Professionals" -> R.string.letters_audience_professionals
    "Couples" -> R.string.letters_audience_couples
    "Elders" -> R.string.letters_audience_elders
    "Grief & Illness" -> R.string.letters_audience_grief_illness
    "Caregivers" -> R.string.letters_audience_caregivers
    "Leaders" -> R.string.letters_audience_leaders
    "Spiritual Seekers" -> R.string.letters_audience_spiritual_seekers
    "New Beginnings" -> R.string.letters_audience_new_beginnings
    else -> R.string.letters_audience_all
}

private fun audienceIcon(audience: String): Int = when (audience) {
    "Children" -> R.drawable.icon_playfulness
    "Students" -> R.drawable.icon_gita
    "Youth" -> R.drawable.icon_courage
    "Parents" -> R.drawable.icon_compassion
    "Professionals" -> R.drawable.icon_karma
    "Couples" -> R.drawable.icon_relationships
    "Elders" -> R.drawable.icon_lotus
    "Grief & Illness" -> R.drawable.letters_icon_heart
    "Caregivers" -> R.drawable.icon_love
    "Leaders" -> R.drawable.icon_leadership
    "Spiritual Seekers" -> R.drawable.icon_meditation
    "New Beginnings" -> R.drawable.letters_icon_star
    else -> R.drawable.icon_peacock_feather
}

@Composable
fun KrishnaLetterScreen(letterId: String?, displayName: String, languageCode: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val readerName = displayName.trim().ifBlank { stringResource(R.string.letters_default_reader) }
    val localizedLetters = remember(languageCode) { localizedKrishnaLetters(languageCode) }
    val letter = remember(letterId, languageCode) { localizedKrishnaLetter(letterId, languageCode) }
    val index = localizedLetters.indexOfFirst { it.source.id == letter.source.id }
    val previous = localizedLetters.getOrNull(index - 1)
    val next = localizedLetters.getOrNull(index + 1)
    val personalizedParagraphs = remember(letter.source.id, readerName, languageCode) { letter.personalizedParagraphs(readerName) }
    val reducedMotion = LocalReducedMotion.current
    var pageEntered by remember(letter.source.id) { mutableStateOf(false) }
    val pageProgress by animateFloatAsState(
        targetValue = if (pageEntered || reducedMotion) 1f else 0f,
        animationSpec = tween(420),
        label = "letterPageTurn"
    )
    LaunchedEffect(letter.source.id) { pageEntered = true }
    val pageModifier = Modifier.fillMaxWidth().graphicsLayer {
        rotationY = if (reducedMotion) 0f else (1f - pageProgress) * -10f
        translationX = if (reducedMotion) 0f else (1f - pageProgress) * 34f
        alpha = .45f + (.55f * pageProgress)
        cameraDistance = 18f
    }
    val shareText = "${personalizedParagraphs.joinToString("\n\n")}\n\n${stringResource(R.string.letters_reflect)}:\n${letter.reflection}\n\n${stringResource(R.string.letters_closing)}\n${stringResource(R.string.letters_krishna)}\n\n${stringResource(R.string.letters_inspired_note)} · ${stringResource(R.string.letters_shared_from)}"
    FeatureScaffold(letter.title, stringResource(R.string.letters_count_situation, index + 1, localizedLetters.size, letter.situation), R.drawable.bg_08_minimal_starfield, onBack, onNavigate, false) {
        item {
            GlassCard(pageModifier) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    androidx.compose.foundation.Image(androidx.compose.ui.res.painterResource(R.drawable.letters_seal), null, Modifier.size(58.dp))
                    Column(Modifier.weight(1f)) {
                        Text(stringResource(R.string.letters_personal_for, readerName), color = LightGold, style = MaterialTheme.typography.headlineMedium)
                        Text(stringResource(R.string.letters_listen), color = MutedText)
                    }
                    EnglishAudioIcon(letter.spokenText(readerName), Modifier.size(46.dp), languageCode)
                }
            }
        }
        items(personalizedParagraphs) { paragraph ->
            GlassCard(pageModifier) {
                Text(paragraph, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
            }
        }
        item { SacredListCard(stringResource(R.string.letters_pause_reflect), letter.reflection, R.drawable.icon_journal) }
        item { SacredListCard(stringResource(R.string.letters_gentle_step), letter.nextStep, R.drawable.icon_check) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Text(stringResource(R.string.letters_closing), color = LightGold, style = MaterialTheme.typography.titleMedium)
                    Text(stringResource(R.string.letters_krishna), color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                    Text(stringResource(R.string.letters_inspired_note), color = MutedText, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        item { SecondarySacredButton(stringResource(R.string.letters_share), { shareSacredText(context, letter.title, shareText) }, Modifier.fillMaxWidth()) }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SecondarySacredButton(if (previous == null) stringResource(R.string.letters_all) else stringResource(R.string.action_previous), { onNavigate(previous?.let { "krishna_letters/${it.source.id}" } ?: "krishna_letters") }, Modifier.weight(1f))
                PrimaryGoldButton(if (next == null) stringResource(R.string.letters_all) else stringResource(R.string.action_next), { onNavigate(next?.let { "krishna_letters/${it.source.id}" } ?: "krishna_letters") }, Modifier.weight(1f))
            }
        }
    }
}
