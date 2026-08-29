package com.indianservers.krishna4u.feature.emotions

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.ui.theme.*

data class EmotionWheelItem(
    val id: String,
    val emotion: String,
    val reassurance: String,
    val letterId: String,
    val verse: String,
    val verseMeaning: String,
    val calmingAction: String,
    @DrawableRes val icon: Int
)

val emotionWheelItems = listOf(
    EmotionWheelItem("afraid", "Afraid", "You can feel fear and still take one safe, brave step.", "future-fear", "Bhagavad Gita 2.14", "Difficult feelings arrive and pass like changing seasons. Meet this moment patiently.", "Place both feet on the floor. Breathe in for four counts and out for six, five times. Then name one thing that is safe right now.", R.drawable.icon_courage),
    EmotionWheelItem("angry", "Angry", "Your anger can protect truth without controlling your words.", "anger", "Bhagavad Gita 2.63", "Uncontrolled anger clouds memory and judgment. A pause protects your wisdom.", "Step away for ten minutes. Cool your hands with water, then write: What happened? What did I need? What can I say without causing harm?", R.drawable.icon_mind),
    EmotionWheelItem("lonely", "Lonely", "The quiet is painful, but it does not mean you are forgotten.", "loneliness", "Bhagavad Gita 6.30", "The one who sees the Divine everywhere is never truly outside the presence of Krishna.", "Send one honest message to a safe person: ‘I feel lonely today. Can we talk for ten minutes?’", R.drawable.icon_friendship),
    EmotionWheelItem("guilty", "Guilty", "Let regret become repair, not a punishment without an end.", "guilt", "Bhagavad Gita 4.36", "Even great mistakes can be crossed through honest understanding and transformed action.", "Write one truth you must admit, one repair you can make, and one habit that will prevent the same harm.", R.drawable.icon_dharma),
    EmotionWheelItem("confused", "Confused", "You do not need the whole path—only the next right step.", "uncertainty", "Bhagavad Gita 2.7", "When Arjuna could not see clearly, he stopped pretending and sincerely asked Krishna for guidance.", "Divide a page into three parts: What I know, what I fear, and what I can do today. Choose one safe action from the last part.", R.drawable.icon_strategy)
)

@Composable
fun EmotionWheelScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var selectedId by remember { mutableStateOf<String?>(null) }
    val selected = emotionWheelItems.firstOrNull { it.id == selectedId }
    val targetRotation = selected?.let { emotionWheelItems.indexOf(it) * 72f } ?: 0f
    val wheelRotation by animateFloatAsState(targetRotation, tween(650), label = "emotionWheelRotation")

    FeatureScaffold(
        "EMOTION WHEEL",
        "Name what you feel. Find one gentle next step.",
        R.drawable.bg_05_moonlit_sacred_river,
        onBack,
        onNavigate
    ) {
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("What is your heart carrying?", color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                    Text("Tap one feeling. The wheel will guide you to a matching letter, Gita verse and calming action.", color = MutedText, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.fillMaxWidth().height(330.dp), contentAlignment = Alignment.Center) {
                        AnimatedMandalaHalo(
                            Modifier.size(238.dp).graphicsLayer { rotationZ = wheelRotation },
                            durationMillis = 90000
                        )
                        Box(
                            Modifier.size(92.dp).clip(CircleShape)
                                .background(Brush.radialGradient(listOf(AntiqueGold.copy(.4f), CosmicMidnight)))
                                .border(1.5.dp, LightGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                SacredIcon(R.drawable.icon_lotus, null, Modifier.size(38.dp))
                                Text(selected?.emotion ?: "Choose", color = LightGold, fontWeight = FontWeight.Bold)
                            }
                        }
                        EmotionNode(emotionWheelItems[0], selectedId, { selectedId = it }, Modifier.align(Alignment.TopCenter))
                        EmotionNode(emotionWheelItems[1], selectedId, { selectedId = it }, Modifier.align(Alignment.CenterEnd))
                        EmotionNode(emotionWheelItems[2], selectedId, { selectedId = it }, Modifier.align(Alignment.BottomEnd).offset(x = (-34).dp, y = (-4).dp))
                        EmotionNode(emotionWheelItems[3], selectedId, { selectedId = it }, Modifier.align(Alignment.BottomStart).offset(x = 34.dp, y = (-4).dp))
                        EmotionNode(emotionWheelItems[4], selectedId, { selectedId = it }, Modifier.align(Alignment.CenterStart))
                    }
                }
            }
        }
        item {
            AnimatedVisibility(
                visible = selected != null,
                enter = fadeIn() + slideInVertically { it / 3 },
                exit = fadeOut() + slideOutVertically { it / 3 }
            ) {
                selected?.let { item ->
                    GlassCard(Modifier.fillMaxWidth()) {
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.weight(1f)) {
                                    Text("When you feel ${item.emotion.lowercase()}", color = LightGold, style = MaterialTheme.typography.titleLarge)
                                    Text(item.reassurance, color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
                                }
                                EnglishAudioIcon("${item.reassurance} ${item.verse}. ${item.verseMeaning} ${item.calmingAction}", Modifier.size(46.dp))
                            }
                            GoldDivider()
                            Text(item.verse, color = AntiqueGold, style = MaterialTheme.typography.titleMedium)
                            Text(item.verseMeaning, color = SoftWhite)
                            Text("Calming action", color = LightGold, style = MaterialTheme.typography.titleMedium)
                            Text(item.calmingAction, color = SoftWhite)
                            PrimaryGoldButton("Read Krishna’s Letter", { onNavigate("krishna_letters/${item.letterId}") }, Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Text("This wheel offers reflective support, not medical care. If you feel unsafe or overwhelmed for a long time, please speak with a trusted person or qualified professional.", color = MutedText, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun EmotionNode(item: EmotionWheelItem, selectedId: String?, onSelect: (String) -> Unit, modifier: Modifier = Modifier) {
    val selected = item.id == selectedId
    val scale by animateFloatAsState(if (selected) 1.1f else 1f, tween(220), label = "${item.id}Scale")
    Column(
        modifier.size(82.dp).graphicsLayer { scaleX = scale; scaleY = scale }
            .clip(CircleShape)
            .background(if (selected) AntiqueGold.copy(.3f) else CosmicMidnight.copy(.94f))
            .border(if (selected) 2.dp else 1.dp, if (selected) LightGold else CelestialCyan.copy(.65f), CircleShape)
            .clickable { onSelect(item.id) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SacredIcon(item.icon, null, Modifier.size(29.dp))
        Text(item.emotion, color = if (selected) LightGold else SoftWhite, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
    }
}
