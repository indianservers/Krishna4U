package com.indianservers.krishna4u.feature.night

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.ui.theme.*
import java.time.LocalDate

data class KrishnaNightMessage(val title: String, val message: String, val release: String)

val krishnaNightMessages = listOf(
    KrishnaNightMessage("You have done enough", "Place today in My hands. You have done enough for this day. What remains can wait for morning. Let your mind become quiet now; I will stay beside you while you rest.", "I release the work that can wait."),
    KrishnaNightMessage("You do not have to solve tonight", "My dear one, the answer you are searching for does not need to be forced from a tired mind. Close this day gently. Tomorrow can bring new light, new facts and new strength.", "I release the need to know everything now."),
    KrishnaNightMessage("Your mistakes can become wisdom", "Do not carry every mistake into your sleep. Keep the lesson, repair what you can tomorrow and put down the shame. One difficult moment is not your whole story.", "I release harsh judgment of myself."),
    KrishnaNightMessage("Let your heart become soft", "You were strong in many unseen ways today. You may rest without proving anything more. Breathe slowly and allow your heart to receive the same kindness you give to others.", "I release the need to appear strong."),
    KrishnaNightMessage("This feeling will move", "The sadness, fear or anger you feel tonight is real, but it is not permanent. Let it pass through your heart without making it your identity. You are still held in love.", "I release the belief that this moment will last forever."),
    KrishnaNightMessage("Tomorrow is still open", "Perhaps today did not happen as you hoped. The next page has not been written. Rest now so that you can meet tomorrow with clearer eyes and a steadier heart.", "I release today’s unfinished expectations."),
    KrishnaNightMessage("You are not alone in the quiet", "When the world becomes silent, do not mistake quiet for absence. Sit with Me for one breath, then another. My presence does not depend on noise, success or perfect prayer.", "I release the fear that I have been forgotten."),
    KrishnaNightMessage("Your body deserves rest", "Your body carried you through every moment of this day. Thank it with water, stillness and sleep. Rest is not time taken away from your purpose; it prepares you to live it well.", "I release guilt about resting."),
    KrishnaNightMessage("Put down what is not yours", "You cannot carry every person’s choices, feelings and future. Offer them care, but return each burden to its proper hands. Keep only the duty that truly belongs to you.", "I release the burdens that are not mine."),
    KrishnaNightMessage("Peace can begin before answers", "You do not need every problem to disappear before you feel one quiet breath of peace. Let peace begin here, inside this small pause, while life is still unfinished.", "I release the demand that everything be perfect."),
    KrishnaNightMessage("Your worth did not change today", "Praise did not create your worth, and criticism cannot remove it. Success and failure are events that teach you; neither is the name of your soul. Rest as someone already worthy of care.", "I release the need to measure my worth."),
    KrishnaNightMessage("Forgive the day", "Forgive this day for being imperfect. Forgive yourself for being human within it. Keep the truth, choose tomorrow’s repair and allow this night to close without another inner argument.", "I release the fight with what has already happened."),
    KrishnaNightMessage("One small gratitude is enough", "You do not need to call the whole day beautiful. Find one small good thing—a kind word, a meal, a breath, a lesson—and let gratitude hold that single light without denying your pain.", "I release the idea that gratitude must hide pain."),
    KrishnaNightMessage("Sleep beneath My care", "Let your final thought be simple: you are loved, you are learning and you are allowed to begin again. Close your eyes. The night knows how to carry you toward morning.", "I release this day and welcome rest.")
)

@Composable
fun KrishnaNightMessageScreen(displayName: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val startIndex = (LocalDate.now().dayOfYear - 1) % krishnaNightMessages.size
    var messageIndex by remember { mutableIntStateOf(startIndex) }
    val message = krishnaNightMessages[messageIndex]
    val readerName = displayName.trim().ifBlank { "Friend" }
    val reducedMotion = LocalReducedMotion.current
    val transition = rememberInfiniteTransition(label = "nightBreathingGlow")
    val glowScale by transition.animateFloat(.9f, 1.08f, infiniteRepeatable(tween(2800), RepeatMode.Reverse), label = "nightGlowScale")
    val glowAlpha by transition.animateFloat(.48f, .9f, infiniteRepeatable(tween(2800), RepeatMode.Reverse), label = "nightGlowAlpha")
    val spoken = "My dear $readerName. ${message.message} Tonight, say: ${message.release}"

    FeatureScaffold("KRISHNA’S NIGHT MESSAGE", "A quiet ending for your day", R.drawable.bg_05_moonlit_sacred_river, onBack, onNavigate) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Box(Modifier.fillMaxWidth().height(320.dp), contentAlignment = Alignment.Center) {
                    StardustOverlay(Modifier.matchParentSize())
                    Image(
                        painterResource(R.drawable.ui_glow_orb),
                        null,
                        Modifier.size(250.dp).scale(if (reducedMotion) 1f else glowScale).alpha(if (reducedMotion) .72f else glowAlpha),
                        contentScale = ContentScale.Fit
                    )
                    Text("☾", color = LightGold, fontSize = 126.sp, textAlign = TextAlign.Center)
                    Column(Modifier.align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Good night, $readerName", color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                        Text("Breathe slowly. Nothing more is required right now.", color = MutedText, textAlign = TextAlign.Center)
                    }
                }
            }
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(message.title, Modifier.weight(1f), color = AntiqueGold, style = MaterialTheme.typography.headlineSmall)
                        EnglishAudioIcon(spoken, Modifier.size(48.dp))
                    }
                    Text(message.message, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    GoldDivider()
                    Text("Tonight, I release…", color = LightGold, style = MaterialTheme.typography.titleMedium)
                    Text(message.release, color = SoftWhite, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
        item {
            SecondarySacredButton(
                "Another Night Message",
                { messageIndex = (messageIndex + 1) % krishnaNightMessages.size },
                Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                "Sleep well. Tomorrow is another place where courage, kindness and dharma can begin.",
                color = MutedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            )
        }
    }
}
