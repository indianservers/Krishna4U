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
    val readSlokas: Set<String>,
    val commitmentPractices: Set<String> = emptySet()
) {
    fun commitmentPracticeCount(commitmentId: String): Int =
        commitmentPractices.count { it.substringAfter(':', it) == commitmentId }
}

data class SacredCollectible(
    val id: String,
    val name: String,
    val quality: String,
    val meaning: String,
    val encouragement: String,
    val requirement: String,
    val target: Int,
    @param:DrawableRes val icon: Int,
    val earningRoute: String = "12",
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
        "Save one honest journal reflection", 1, R.drawable.icon_lotus, "26"
    ) { it.reflections.size },
    SacredCollectible(
        "conch", "Conch", "Courage",
        "The conch announces that the time for right action has arrived. Courage is not the absence of fear; it is answering duty without allowing fear to choose for you.",
        "You earned this by keeping a lesson from Krishna’s life. Carry its courage into one real decision that needs your voice.",
        "Bookmark one Krishna Life lesson", 1, R.drawable.icon_conch, "06"
    ) { activity -> activity.bookmarks.count { it.startsWith("life:") } },
    dharmaCollectible(
        "dharma-wheel", "Dharma Wheel", "Righteous Choice", R.drawable.icon_dharma, "dharma",
        "The Dharma Wheel turns when values become action. It represents choosing what is right even when the easier path offers praise, comfort or advantage.",
        "You earned this by practising your promise to follow dharma. Let the next difficult choice also be guided by truth rather than convenience."
    ),
    dharmaCollectible(
        "sacred-tree", "Sacred Tree", "Care for Creation", R.drawable.icon_compassion, "nature",
        "The Sacred Tree offers shade without asking who deserves it. It represents protecting animals, plants, water, food and the living world entrusted to your care.",
        "You earned this through a real act of care for nature or animals. Continue leaving every place healthier than you found it."
    ),
    dharmaCollectible(
        "guru-paduka", "Guru Paduka", "Respect for Guidance", R.drawable.icon_relationships, "guides",
        "The Guru Paduka represents gratitude toward parents, grandparents, teachers and elders whose experience helps us walk with fewer avoidable mistakes.",
        "You earned this by honouring someone who guides you. Listen with respect, ask with humility and express thanks while they can receive it."
    ),
    dharmaCollectible(
        "tulsi-leaf", "Tulsi Leaf", "Dignity for Every Soul", R.drawable.icon_love, "respect",
        "The Tulsi Leaf is small yet sacred. It reminds us that dignity does not depend on age, gender, status, wealth, ability, faith or usefulness.",
        "You earned this by treating another person with true respect. Keep seeing Krishna’s presence in every soul you meet."
    ),
    dharmaCollectible(
        "truth-lamp", "Lamp of Truth", "Gentle Honesty", R.drawable.icon_teachings, "truth",
        "A lamp reveals without striking. The Lamp of Truth represents honest words spoken gently, without rumours, humiliation or the misuse of another person’s private story.",
        "You earned this by choosing truthful and kind speech. Let your words continue to bring clarity without creating needless wounds."
    ),
    dharmaCollectible(
        "guardian-shield", "Guardian Shield", "Protection", R.drawable.icon_courage, "protect",
        "The Guardian Shield belongs to the person who refuses to be silent when someone is bullied, humiliated, exploited or placed in danger.",
        "You earned this by protecting someone from harm or injustice. Courage becomes devotion when strength is used for another person’s safety."
    ),
    dharmaCollectible(
        "arjuna-bow", "Arjuna’s Bow", "Sincere Duty", R.drawable.icon_karma, "duty",
        "Arjuna’s Bow represents focused duty. It is earned through honest study or work completed without cheating, careless excuses or running away from responsibility.",
        "You earned this by fulfilling a real duty sincerely. Keep giving full attention to the work that is truly yours."
    ),
    dharmaCollectible(
        "cooling-moon", "Cooling Moon", "Mastery of Anger", R.drawable.icon_mind, "anger",
        "The Cooling Moon represents the pause that saves wisdom from anger. It shines when you calm the body and choose words that protect truth without trying to hurt.",
        "You earned this by mastering one angry reaction. Remember that the pause before speaking is a form of inner strength."
    ),
    dharmaCollectible(
        "friendship-garland", "Friendship Garland", "Sacred Trust", R.drawable.icon_friendship, "friendship",
        "A garland is made by holding many flowers together without crushing them. It represents listening, loyalty, honest communication and respect for a friend’s trust.",
        "You earned this by being a true friend when your presence mattered. Keep friendship free from gossip, control and selfish use."
    ),
    dharmaCollectible(
        "sacred-mirror", "Sacred Mirror", "Honest Correction", R.drawable.icon_life_journey, "mistakes",
        "The Sacred Mirror asks you to see a mistake without excuses or self-hate. Its lesson is to admit, repair, learn and begin again with wiser action.",
        "You earned this by correcting a real mistake. Carry the lesson forward, but do not keep carrying shame after honest repair."
    ),
    dharmaCollectible(
        "open-palm", "Open Palm", "Wise Forgiveness", R.drawable.icon_inner_peace, "forgive",
        "The Open Palm releases revenge while keeping wise boundaries. Forgiveness protects your peace; it does not require trusting repeated harm or denying what happened.",
        "You earned this through a sincere act of forgiveness. Keep peace in your heart and wisdom at your boundaries."
    ),
    dharmaCollectible(
        "seva-bowl", "Seva Bowl", "Selfless Service", R.drawable.icon_compassion, "serve",
        "The Seva Bowl represents food shared, loneliness noticed and help offered to children, elders or anyone truly in need without seeking applause.",
        "You earned this through a real act of service. Let your hands remain available wherever care is needed most."
    ),
    dharmaCollectible(
        "temple-bell", "Temple Bell", "Healthy Self-Control", R.drawable.icon_meditation, "health",
        "The Temple Bell calls the mind back to what is sacred. It represents protecting your body and mind from tobacco, alcohol, drugs and every habit that damages judgment or health.",
        "You earned this by making a healthy choice for your mind or body. Continue choosing freedom over any habit that tries to control you."
    ),
    dharmaCollectible(
        "sacred-kalasha", "Sacred Kalasha", "Responsible Resources", R.drawable.icon_strategy, "resources",
        "The Sacred Kalasha holds its contents with care. It represents using money, food, time, phones and technology honestly, without waste, addiction or harm.",
        "You earned this by using a resource responsibly. Let everything in your care serve a clean and helpful purpose."
    ),
    dharmaCollectible(
        "promise-thread", "Promise Thread", "Integrity", R.drawable.icon_check, "promises",
        "The Promise Thread represents the trust carried by your word. Integrity means keeping a promise or speaking honestly and early when circumstances truly prevent it.",
        "You earned this by keeping your word when it required effort. Let people continue to feel safe trusting what you say."
    )
)

private fun dharmaCollectible(
    id: String,
    name: String,
    quality: String,
    @DrawableRes icon: Int,
    commitmentId: String,
    meaning: String,
    encouragement: String
) = SacredCollectible(
    id = id,
    name = name,
    quality = quality,
    meaning = meaning,
    encouragement = encouragement,
    requirement = "First achieve this in real life, then mark ‘${commitmentTitle(commitmentId)}’ as practised in My 18 Commitments",
    target = 1,
    icon = icon,
    earningRoute = "commitments"
) { it.commitmentPracticeCount(commitmentId) }

private fun commitmentTitle(id: String): String = when (id) {
    "dharma" -> "Follow Dharma"
    "nature" -> "Protect Nature and Life"
    "guides" -> "Honour Those Who Guide Me"
    "respect" -> "Respect Every Person"
    "truth" -> "Speak Truth with Kindness"
    "protect" -> "Protect Others from Harm"
    "duty" -> "Fulfil My Duty Honestly"
    "anger" -> "Master Anger and Speech"
    "friendship" -> "Keep Friendship Sacred"
    "mistakes" -> "Learn, Correct and Begin Again"
    "forgive" -> "Forgive with Wisdom"
    "serve" -> "Serve Those in Need"
    "health" -> "Protect My Mind and Body"
    "resources" -> "Use Wealth and Technology Wisely"
    "promises" -> "Keep My Word"
    else -> error("Unknown Krishna commitment: $id")
}

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
                    Text("Every symbol reflects learning or a dharma action completed on this device. Mark a commitment only after practising it in real life. There are no purchases and no shortcuts.", color = MutedText, textAlign = TextAlign.Center)
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
        item { SacredListCard("How earning works", "Read, reflect and practise dharma in real life. Open My 18 Commitments and mark an action only after you truly do it. Progress stays local on this device.", R.drawable.icon_dharma) }
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
        item { PrimaryGoldButton(if (earned) "Continue the Practice" else "Achieve This", { onNavigate(collectible.earningRoute) }, Modifier.fillMaxWidth()) }
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
