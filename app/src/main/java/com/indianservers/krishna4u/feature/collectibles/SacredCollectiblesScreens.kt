package com.indianservers.krishna4u.feature.collectibles

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.ui.theme.*

data class LearningActivity(
    val bookmarks: Set<String>,
    val reflections: Set<String>,
    val readSlokas: Set<String>
)

data class SacredCollectible(
    val id: String,
    val name: String,
    val quality: String,
    val meaning: String,
    val encouragement: String,
    val requirement: String,
    val target: Int,
    @DrawableRes val icon: Int,
    val progressOf: (LearningActivity) -> Int
) {
    fun progress(activity: LearningActivity): Int = progressOf(activity).coerceIn(0, target)
    fun earned(activity: LearningActivity): Boolean = progress(activity) >= target
}

val sacredCollectibles = listOf(
    SacredCollectible(
        "peacock-feather", "Peacock Feather", "Compassion",
        "Krishna’s peacock feather reminds us that gentleness is not weakness. Compassion notices another heart, protects dignity and chooses care even when no reward is offered.",
        "You earned this by keeping guidance that can help your heart or another person. Let saved wisdom become a kind action.",
        "Save one teaching, message or śloka", 1, R.drawable.icon_peacock_feather
    ) { it.bookmarks.size },
    SacredCollectible(
        "flute", "Flute", "Calmness",
        "A flute becomes music because it is open and still enough for breath to move through it. Krishna’s flute invites you to make quiet space before reacting.",
        "You earned this through steady reading. Pause before your next response and let calmness guide the words that follow.",
        "Read 5 unique Bhagavad Gita ślokas", 5, R.drawable.icon_flute
    ) { it.readSlokas.size },
    SacredCollectible(
        "chakra", "Sudarshana Chakra", "Discipline",
        "The Chakra represents clear vision, order and action directed by dharma. Discipline is not punishment; it is your energy moving toward what truly matters.",
        "You earned this through repeated learning. Protect one daily practice from distraction and keep your promise even when motivation is quiet.",
        "Read 18 unique Bhagavad Gita ślokas", 18, R.drawable.icon_chakra
    ) { it.readSlokas.size },
    SacredCollectible(
        "lotus", "Lotus", "Purity",
        "The lotus rises through muddy water without losing its beauty. Purity does not mean a life without difficulty; it means returning to honest intention within difficulty.",
        "You earned this by reflecting truthfully. Let your journal remain a private place where confusion can become clarity.",
        "Save one honest journal reflection", 1, R.drawable.icon_lotus
    ) { it.reflections.size },
    SacredCollectible(
        "conch", "Conch", "Courage",
        "The conch announces that the time for right action has arrived. Courage is not the absence of fear; it is answering duty without allowing fear to choose for you.",
        "You earned this by keeping a lesson from Krishna’s life. Carry its courage into one real decision that needs your voice.",
        "Bookmark one Krishna Life lesson", 1, R.drawable.icon_conch
    ) { activity -> activity.bookmarks.count { it.startsWith("life:") } }
)

fun sacredCollectible(id: String?): SacredCollectible = sacredCollectibles.firstOrNull { it.id == id } ?: sacredCollectibles.first()

@Composable
fun SacredCollectiblesScreen(activity: LearningActivity, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val earnedCount = sacredCollectibles.count { it.earned(activity) }
    FeatureScaffold("SACRED COLLECTIBLES", "Earned through learning · Never purchased", R.drawable.bg_04_sacred_cosmic_temple, onBack, onNavigate) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    AnimatedMandalaHalo(Modifier.size(120.dp))
                    Text("$earnedCount of ${sacredCollectibles.size} symbols awakened", color = LightGold, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                    Text("Every symbol reflects learning already completed on this device. There are no purchases and no shortcuts.", color = MutedText, textAlign = TextAlign.Center)
                    LinearProgressIndicator(
                        progress = { earnedCount / sacredCollectibles.size.toFloat() },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = AntiqueGold,
                        trackColor = SoftWhite.copy(.15f),
                        drawStopIndicator = {}
                    )
                }
            }
        }
        items(sacredCollectibles.chunked(2)) { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { collectible ->
                    val earned = collectible.earned(activity)
                    val progress = collectible.progress(activity)
                    GlassCard(Modifier.weight(1f).height(210.dp), onClick = { onNavigate("collectible/${collectible.id}") }) {
                        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            SacredIcon(collectible.icon, collectible.name, Modifier.size(68.dp).alpha(if (earned) 1f else .38f))
                            Text(collectible.name, color = if (earned) LightGold else MutedText, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                            Text(collectible.quality, color = if (earned) AntiqueGold else MutedText, textAlign = TextAlign.Center)
                            Spacer(Modifier.height(6.dp))
                            Text(if (earned) "AWAKENED" else "$progress / ${collectible.target}", color = if (earned) LightGold else MutedText, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        item { SacredListCard("How earning works", "Read, reflect and save meaningful guidance. Progress is calculated locally from your real activity.", R.drawable.icon_dharma) }
    }
}

@Composable
fun SacredCollectibleDetailsScreen(collectibleId: String?, activity: LearningActivity, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val collectible = sacredCollectible(collectibleId)
    val earned = collectible.earned(activity)
    val progress = collectible.progress(activity)
    FeatureScaffold(collectible.name, collectible.quality.uppercase(), R.drawable.bg_08_minimal_starfield, onBack, onNavigate, showBottomBar = false) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    CollectibleReveal(collectible, earned, Modifier.size(230.dp))
                    Text(if (earned) "SYMBOL AWAKENED" else "SYMBOL WAITING", color = if (earned) AntiqueGold else MutedText, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    Text(collectible.quality, color = LightGold, style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
        item { SacredListCard("What this symbol means", collectible.meaning, collectible.icon) }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(if (earned) "Why you earned it" else "How to awaken it", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    Text(if (earned) collectible.encouragement else collectible.requirement, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                    LinearProgressIndicator(
                        progress = { progress / collectible.target.toFloat() },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = AntiqueGold,
                        trackColor = SoftWhite.copy(.15f),
                        drawStopIndicator = {}
                    )
                    Text(if (earned) "Complete" else "$progress of ${collectible.target} completed", color = MutedText)
                }
            }
        }
        item { PrimaryGoldButton(if (earned) "Continue Learning" else "Begin Earning", { onNavigate(if (collectible.id == "lotus") "26" else if (collectible.id == "conch") "06" else "12") }, Modifier.fillMaxWidth()) }
        item { SecondarySacredButton("All Collectibles", onBack, Modifier.fillMaxWidth()) }
    }
}

@Composable
private fun CollectibleReveal(collectible: SacredCollectible, earned: Boolean, modifier: Modifier = Modifier) {
    val reducedMotion = LocalReducedMotion.current
    val transition = rememberInfiniteTransition(label = "${collectible.id}Reveal")
    val pulse by transition.animateFloat(.92f, 1.07f, infiniteRepeatable(tween(1800), RepeatMode.Reverse), label = "collectiblePulse")
    val sway by transition.animateFloat(-5f, 5f, infiniteRepeatable(tween(2400), RepeatMode.Reverse), label = "collectibleSway")
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(12000, easing = LinearEasing)), label = "collectibleRotation")
    Box(modifier, contentAlignment = Alignment.Center) {
        BreathingLotusOrb(Modifier.matchParentSize().alpha(if (earned) .8f else .28f))
        SacredIcon(
            collectible.icon,
            collectible.name,
            Modifier.fillMaxSize(.58f).alpha(if (earned) 1f else .35f).graphicsLayer {
                if (!reducedMotion && earned) {
                    scaleX = pulse
                    scaleY = pulse
                    rotationZ = when (collectible.id) {
                        "chakra" -> rotation
                        "peacock-feather", "flute", "conch" -> sway
                        else -> 0f
                    }
                    translationY = if (collectible.id == "flute") sway else 0f
                }
            }
        )
    }
}
