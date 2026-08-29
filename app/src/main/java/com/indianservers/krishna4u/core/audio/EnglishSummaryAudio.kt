package com.indianservers.krishna4u.core.audio

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.core.design.PrimaryGoldButton
import com.indianservers.krishna4u.ui.theme.MutedText
import java.util.Locale

@Composable
fun EnglishSummaryAudio(text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val currentText by rememberUpdatedState(text)
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var ready by remember { mutableStateOf(false) }
    var speaking by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("Preparing offline English audio…") }

    DisposableEffect(Unit) {
        val mainHandler = Handler(Looper.getMainLooper())
        var localEngine: TextToSpeech? = null
        localEngine = TextToSpeech(context) { result ->
            if (result == TextToSpeech.SUCCESS) {
                val tts = localEngine ?: return@TextToSpeech
                val offlineVoice = tts.voices
                    ?.filter { voice -> voice.locale.language == Locale.ENGLISH.language && !voice.isNetworkConnectionRequired }
                    ?.sortedByDescending { voice -> voice.quality }
                    ?.firstOrNull()
                if (offlineVoice != null) {
                    tts.voice = offlineVoice
                    tts.setSpeechRate(0.9f)
                    ready = true
                    status = "English audio · offline device voice"
                } else {
                    status = "Offline English voice unavailable. Install one in device text-to-speech settings."
                }
            } else {
                status = "English audio could not be initialized on this device."
            }
        }
        localEngine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) { mainHandler.post { speaking = true } }
            override fun onDone(utteranceId: String?) { mainHandler.post { speaking = false } }
            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                mainHandler.post {
                    speaking = false
                    status = "Audio playback stopped unexpectedly."
                }
            }
            override fun onError(utteranceId: String?, errorCode: Int) {
                mainHandler.post {
                    speaking = false
                    status = "Audio playback stopped unexpectedly."
                }
            }
        })
        engine = localEngine
        onDispose {
            localEngine.stop()
            localEngine.shutdown()
            engine = null
        }
    }

    Column(modifier) {
        PrimaryGoldButton(
            text = when {
                speaking -> "■  Stop English Audio"
                ready -> "▶  Listen to English Summary"
                else -> "Preparing English Audio…"
            },
            onClick = {
                val tts = engine ?: return@PrimaryGoldButton
                if (speaking) {
                    tts.stop()
                    speaking = false
                } else if (ready) {
                    val result = tts.speak(currentText, TextToSpeech.QUEUE_FLUSH, Bundle(), "chapter-summary")
                    if (result == TextToSpeech.ERROR) status = "Unable to play this summary."
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(6.dp))
        Text(status, Modifier.fillMaxWidth(), color = MutedText, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
}
