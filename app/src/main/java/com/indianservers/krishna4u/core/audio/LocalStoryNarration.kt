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
fun LocalStoryNarration(text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val currentText by rememberUpdatedState(text)
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var ready by remember { mutableStateOf(false) }
    var speaking by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("Preparing offline narration…") }

    DisposableEffect(Unit) {
        val mainHandler = Handler(Looper.getMainLooper())
        var localEngine: TextToSpeech? = null
        localEngine = TextToSpeech(context) { result ->
            if (result == TextToSpeech.SUCCESS) {
                val tts = localEngine ?: return@TextToSpeech
                val voice = tts.voices
                    ?.filter { it.locale.language == Locale.ENGLISH.language && !it.isNetworkConnectionRequired }
                    ?.sortedByDescending { it.quality }
                    ?.firstOrNull()
                if (voice != null) {
                    tts.voice = voice
                    tts.setSpeechRate(0.86f)
                    ready = true
                    status = "Narrated with the device’s offline English voice"
                } else status = "Install an offline English voice in device text-to-speech settings."
            } else status = "Narration is unavailable on this device."
        }
        localEngine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) { mainHandler.post { speaking = true } }
            override fun onDone(utteranceId: String?) { mainHandler.post { speaking = false } }
            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) { mainHandler.post { speaking = false } }
            override fun onError(utteranceId: String?, errorCode: Int) { mainHandler.post { speaking = false } }
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
                speaking -> "■  Stop Story"
                ready -> "▶  Listen Together"
                else -> "Preparing Narration…"
            },
            onClick = {
                val tts = engine ?: return@PrimaryGoldButton
                if (speaking) {
                    tts.stop()
                    speaking = false
                } else if (ready) {
                    if (tts.speak(currentText, TextToSpeech.QUEUE_FLUSH, Bundle(), "family-story") == TextToSpeech.ERROR) {
                        status = "Unable to play this story."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(6.dp))
        Text(status, Modifier.fillMaxWidth(), color = MutedText, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
}
