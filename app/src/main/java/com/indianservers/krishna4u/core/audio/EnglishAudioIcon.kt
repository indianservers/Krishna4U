package com.indianservers.krishna4u.core.audio

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.SacredIcon
import java.util.Locale

/** Compact, fully local TTS control. The latest [text] is spoken whenever content changes. */
@Composable
fun EnglishAudioIcon(text: String, modifier: Modifier = Modifier) {
    val currentText by rememberUpdatedState(text)
    val context = androidx.compose.ui.platform.LocalContext.current
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var ready by remember { mutableStateOf(false) }
    var speaking by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val mainHandler = Handler(Looper.getMainLooper())
        var localEngine: TextToSpeech? = null
        localEngine = TextToSpeech(context) { result ->
            if (result == TextToSpeech.SUCCESS) {
                val tts = localEngine ?: return@TextToSpeech
                tts.voices
                    ?.filter { it.locale.language == Locale.ENGLISH.language && !it.isNetworkConnectionRequired }
                    ?.maxByOrNull { it.quality }
                    ?.let { tts.voice = it }
                tts.setSpeechRate(0.9f)
                ready = true
            }
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

    LaunchedEffect(text) {
        engine?.stop()
        speaking = false
    }

    IconButton(
        onClick = {
            val tts = engine ?: return@IconButton
            if (speaking) {
                tts.stop()
                speaking = false
            } else if (ready) {
                tts.speak(currentText, TextToSpeech.QUEUE_FLUSH, Bundle(), "english-${currentText.hashCode()}")
            }
        },
        enabled = ready || speaking,
        modifier = modifier
    ) {
        SacredIcon(
            if (speaking) R.drawable.icon_pause else R.drawable.icon_play,
            if (speaking) "Stop English audio" else "Play English meaning",
            Modifier.size(26.dp)
        )
    }
}
