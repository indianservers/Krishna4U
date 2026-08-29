package com.indianservers.krishna4u.feature.gita

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.data.repository.OfflineGitaVerse
import com.indianservers.krishna4u.data.repository.OfflineGitaRepository
import com.indianservers.krishna4u.ui.theme.*
import java.util.Locale
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

private enum class GitaAudioMode { Sanskrit, English }

@Composable
fun ListenToGitaScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val context = LocalContext.current
    val repository = remember { OfflineGitaRepository(context.applicationContext) }
    val verses = remember { repository.versesInChapter(2) }
    var verseIndex by remember { mutableIntStateOf(verses.indexOfFirst { it.verse == 47 }.coerceAtLeast(0)) }
    val verse = verses[verseIndex]
    var mode by remember { mutableStateOf(GitaAudioMode.Sanskrit) }
    var speed by remember { mutableFloatStateOf(1f) }
    var timerMinutes by remember { mutableIntStateOf(0) }
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var ready by remember { mutableStateOf(false) }
    var playing by remember { mutableStateOf(false) }
    var spokenCharacter by remember { mutableIntStateOf(0) }
    var utteranceBase by remember { mutableIntStateOf(0) }

    val speechText = remember(verse, mode) {
        when (mode) {
            GitaAudioMode.Sanskrit -> "${verse.sanskrit}. ${verse.transliteration}"
            GitaAudioMode.English -> verse.englishSummary
        }
    }
    val upcomingVerses = remember(verseIndex) { verses.drop(verseIndex + 1).take(3) }
    val currentSpeechText by rememberUpdatedState(speechText)

    DisposableEffect(Unit) {
        val handler = Handler(Looper.getMainLooper())
        var localEngine: TextToSpeech? = null
        localEngine = TextToSpeech(context.applicationContext) { result ->
            if (result == TextToSpeech.SUCCESS) {
                ready = true
                localEngine?.setSpeechRate(speed)
            }
        }
        localEngine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                handler.post { playing = true }
            }
            override fun onDone(utteranceId: String?) {
                handler.post {
                    spokenCharacter = currentSpeechText.length
                    playing = false
                }
            }
            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                handler.post { playing = false }
            }
            override fun onError(utteranceId: String?, errorCode: Int) {
                handler.post { playing = false }
            }
            override fun onRangeStart(utteranceId: String?, start: Int, end: Int, frame: Int) {
                handler.post { spokenCharacter = (utteranceBase + start).coerceAtMost(currentSpeechText.length) }
            }
        })
        engine = localEngine
        onDispose {
            localEngine.stop()
            localEngine.shutdown()
            engine = null
        }
    }

    LaunchedEffect(verseIndex, mode) {
        engine?.stop()
        playing = false
        spokenCharacter = 0
        utteranceBase = 0
    }
    LaunchedEffect(timerMinutes) {
        if (timerMinutes > 0) {
            delay(timerMinutes * 60_000L)
            engine?.stop()
            playing = false
            timerMinutes = 0
        }
    }

    fun playFromCurrentPosition() {
        val tts = engine ?: return
        if (!ready) return
        if (playing) {
            tts.stop()
            playing = false
            return
        }
        val start = spokenCharacter.takeIf { it in 0 until speechText.length } ?: 0
        utteranceBase = start
        tts.setSpeechRate(speed)
        val desiredLanguage = if (mode == GitaAudioMode.Sanskrit) Locale.forLanguageTag("hi-IN") else Locale.ENGLISH
        tts.voices
            ?.filter { it.locale.language == desiredLanguage.language && !it.isNetworkConnectionRequired }
            ?.maxByOrNull { it.quality }
            ?.let { tts.voice = it }
        tts.speak(speechText.substring(start), TextToSpeech.QUEUE_FLUSH, Bundle(), "gita-${verse.chapter}-${verse.verse}-${mode.name}")
    }

    fun moveTo(index: Int) {
        verseIndex = index.coerceIn(0, verses.lastIndex)
    }

    FeatureScaffold(
        "Listen to the Gita",
        "Let wisdom enter through stillness",
        R.drawable.bg_04_sacred_cosmic_temple,
        onBack,
        onNavigate
    ) {
        item {
            Box(Modifier.fillMaxWidth().height(280.dp), contentAlignment = Alignment.Center) {
                AnimatedMandalaHalo(Modifier.size(255.dp))
                Image(
                    painterResource(R.drawable.illustration_07_open_gita),
                    "Open Bhagavad Gita",
                    Modifier.fillMaxWidth().height(250.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        item {
            GitaNowPlayingCard(
                verse = verse,
                speechText = speechText,
                mode = mode,
                ready = ready,
                playing = playing,
                progress = if (speechText.isEmpty()) 0f else spokenCharacter.toFloat() / speechText.length,
                speed = speed,
                timerMinutes = timerMinutes,
                onSeek = { fraction ->
                    engine?.stop()
                    playing = false
                    spokenCharacter = (speechText.length * fraction).roundToInt().coerceIn(0, speechText.length)
                },
                onBackTen = { spokenCharacter = (spokenCharacter - speechText.length / 6).coerceAtLeast(0); if (playing) { engine?.stop(); playing = false } },
                onForwardTen = { spokenCharacter = (spokenCharacter + speechText.length / 6).coerceAtMost(speechText.length); if (playing) { engine?.stop(); playing = false } },
                onPrevious = { moveTo(verseIndex - 1) },
                onPlayPause = ::playFromCurrentPosition,
                onNext = { moveTo(verseIndex + 1) },
                onSpeed = {
                    engine?.stop()
                    playing = false
                    speed = when (speed) { .75f -> 1f; 1f -> 1.25f; else -> .75f }
                },
                onTimer = { timerMinutes = when (timerMinutes) { 0 -> 5; 5 -> 10; 10 -> 15; else -> 0 } },
                onMode = { mode = it }
            )
        }
        item { Text("Up Next", color = LightGold, style = MaterialTheme.typography.headlineMedium) }
        items(upcomingVerses, key = { "${it.chapter}.${it.verse}" }) { queued ->
            GitaQueueCard(queued, onClick = { moveTo(verses.indexOf(queued)) })
        }
    }
}

@Composable
private fun GitaNowPlayingCard(
    verse: OfflineGitaVerse,
    speechText: String,
    mode: GitaAudioMode,
    ready: Boolean,
    playing: Boolean,
    progress: Float,
    speed: Float,
    timerMinutes: Int,
    onSeek: (Float) -> Unit,
    onBackTen: () -> Unit,
    onForwardTen: () -> Unit,
    onPrevious: () -> Unit,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onSpeed: () -> Unit,
    onTimer: () -> Unit,
    onMode: (GitaAudioMode) -> Unit
) {
    val estimatedSeconds = (speechText.split(Regex("\\s+")).size * 0.52f / speed).roundToInt().coerceAtLeast(12)
    val elapsedSeconds = (estimatedSeconds * progress.coerceIn(0f, 1f)).roundToInt()
    GlassCard(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                SacredIcon(chapterIcon(verse.chapter), null, Modifier.size(50.dp))
                Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                    Text("Chapter ${verse.chapter} · Sankhya Yoga", color = SoftWhite, style = MaterialTheme.typography.titleLarge)
                    Text("Sloka ${verse.chapter}.${verse.verse}", color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SacredIcon(R.drawable.icon_download, "Available offline", Modifier.size(30.dp))
                    Text("Offline", color = MutedText, style = MaterialTheme.typography.labelSmall)
                }
            }
            Text(
                if (mode == GitaAudioMode.Sanskrit) verse.sanskrit else verse.englishSummary,
                Modifier.fillMaxWidth(),
                color = if (mode == GitaAudioMode.Sanskrit) LightGold else SoftWhite,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                maxLines = 4
            )
            Image(painterResource(R.drawable.ui_audio_waveform), null, Modifier.fillMaxWidth().height(52.dp), contentScale = ContentScale.FillWidth)
            Slider(
                value = progress.coerceIn(0f, 1f),
                onValueChange = onSeek,
                colors = SliderDefaults.colors(thumbColor = LightGold, activeTrackColor = AntiqueGold, inactiveTrackColor = MutedText.copy(alpha = .35f)),
                modifier = Modifier.fillMaxWidth()
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(formatPlayerTime(elapsedSeconds), color = MutedText)
                Text(formatPlayerTime(estimatedSeconds), color = MutedText)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                PlayerTextControl("↶ 10", "Back ten seconds", onBackTen)
                PlayerIconControl(R.drawable.icon_previous, "Previous sloka", 42, onPrevious)
                Box(
                    Modifier.size(78.dp).clip(CircleShape).border(2.dp, AntiqueGold, CircleShape).clickable(enabled = ready, onClick = onPlayPause),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedMandalaHalo(Modifier.size(74.dp), durationMillis = 24000)
                    SacredIcon(if (playing) R.drawable.icon_pause else R.drawable.icon_play, if (playing) "Pause" else "Play", Modifier.size(42.dp))
                }
                PlayerIconControl(R.drawable.icon_next, "Next sloka", 42, onNext)
                PlayerTextControl("10 ↷", "Forward ten seconds", onForwardTen)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                PlayerPill("${speed}x", R.drawable.icon_audio, onSpeed)
                PlayerPill(if (timerMinutes == 0) "Timer" else "$timerMinutes min", R.drawable.icon_timer, onTimer)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SpiritualChip("ॐ  Sanskrit", R.drawable.icon_om, mode == GitaAudioMode.Sanskrit, { onMode(GitaAudioMode.Sanskrit) }, Modifier.weight(1f))
                SpiritualChip("English Meaning", R.drawable.icon_gita, mode == GitaAudioMode.English, { onMode(GitaAudioMode.English) }, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PlayerIconControl(icon: Int, description: String, size: Int, onClick: () -> Unit) {
    SacredIcon(icon, description, Modifier.size(size.dp).clickable(onClick = onClick))
}

@Composable
private fun PlayerTextControl(label: String, description: String, onClick: () -> Unit) {
    Box(Modifier.size(44.dp).clip(CircleShape).border(1.dp, AntiqueGold, CircleShape).clickable(onClick = onClick), contentAlignment = Alignment.Center) {
        Text(label, color = LightGold, style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center)
    }
}

@Composable
private fun PlayerPill(label: String, icon: Int, onClick: () -> Unit) {
    Row(
        Modifier.height(42.dp).clip(CircleShape).border(1.dp, AntiqueGold.copy(alpha = .75f), CircleShape).clickable(onClick = onClick).padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        SacredIcon(icon, null, Modifier.size(21.dp))
        Text(label, color = SoftWhite, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun GitaQueueCard(verse: OfflineGitaVerse, onClick: () -> Unit) {
    GlassCard(Modifier.fillMaxWidth(), onClick) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(52.dp).clip(CircleShape).border(1.dp, AntiqueGold, CircleShape), contentAlignment = Alignment.Center) {
                Text("${verse.chapter}.${verse.verse}", color = LightGold, style = MaterialTheme.typography.titleMedium)
            }
            Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(verse.sanskrit, color = LightGold, style = MaterialTheme.typography.titleMedium, maxLines = 1)
                Text(verse.englishSummary, color = MutedText, style = MaterialTheme.typography.bodySmall, maxLines = 2)
            }
            SacredIcon(R.drawable.icon_play, "Play Sloka ${verse.chapter}.${verse.verse}", Modifier.size(30.dp))
        }
    }
}

private fun formatPlayerTime(seconds: Int): String = "%02d:%02d".format(seconds / 60, seconds % 60)
