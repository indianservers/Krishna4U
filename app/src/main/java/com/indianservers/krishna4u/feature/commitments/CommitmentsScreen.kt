package com.indianservers.krishna4u.feature.commitments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.FeatureScaffold
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.PrimaryGoldButton
import com.indianservers.krishna4u.core.design.SacredIcon
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.CelestialCyan
import com.indianservers.krishna4u.ui.theme.CosmicMidnight
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite

@Composable
fun KrishnaCommitmentsScreen(
    practisedToday: Set<String>,
    renewedToday: Boolean,
    onTogglePractised: (String) -> Unit,
    onRenew: () -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    FeatureScaffold(
        title = "My 18 Commitments to Krishna",
        subtitle = "A promise renewed through action",
        background = R.drawable.bg_08_minimal_starfield,
        onBack = onBack,
        onNavigate = onNavigate
    ) {
        item {
            GlassCard(Modifier.fillMaxWidth().height(230.dp)) {
                Image(
                    painterResource(R.drawable.illustration_10_flute_feather),
                    null,
                    Modifier.align(Alignment.CenterStart).fillMaxHeight().fillMaxWidth(.48f),
                    contentScale = ContentScale.Fit
                )
                Column(
                    Modifier.align(Alignment.CenterEnd).fillMaxWidth(.64f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "A promise renewed\nthrough action",
                        color = LightGold,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("18 vows · One conscious life", color = SoftWhite, textAlign = TextAlign.Center)
                }
            }
        }
        item {
            PrimaryGoldButton(
                text = if (renewedToday) "Commitments Renewed Today ✓" else "Renew My Commitments",
                onClick = onRenew,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (renewedToday) {
            item {
                Text(
                    "Your promise is remembered on this device. Now let each action give it life.",
                    color = CelestialCyan,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                )
            }
        }
        item {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("The Commitments", color = LightGold, style = MaterialTheme.typography.headlineMedium)
                Text(
                    "Tap a commitment when you practise it today · ${practisedToday.size} of 18",
                    color = MutedText,
                    textAlign = TextAlign.Center
                )
            }
        }
        items(krishnaCommitments.size, key = { krishnaCommitments[it].id }) { index ->
            val commitment = krishnaCommitments[index]
            CommitmentCard(
                number = index + 1,
                commitment = commitment,
                practised = commitment.id in practisedToday,
                onToggle = { onTogglePractised(commitment.id) }
            )
        }
        item {
            GlassCard(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    SacredIcon(R.drawable.icon_lotus, null, Modifier.size(48.dp))
                    Text("A vow grows through practice", color = LightGold, style = MaterialTheme.typography.titleLarge)
                    Text(
                        "If you miss a day, return without guilt. Krishna asks for sincere effort, not perfection.",
                        color = SoftWhite,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun CommitmentCard(
    number: Int,
    commitment: KrishnaCommitment,
    practised: Boolean,
    onToggle: () -> Unit
) {
    GlassCard(Modifier.fillMaxWidth(), onClick = onToggle) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(CosmicMidnight.copy(alpha = .82f))
                    .border(1.dp, if (practised) CelestialCyan else AntiqueGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(number.toString().padStart(2, '0'), color = LightGold, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(10.dp))
            SacredIcon(commitment.icon, null, Modifier.size(42.dp))
            Column(Modifier.weight(1f).padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(commitment.title, color = LightGold, style = MaterialTheme.typography.titleLarge)
                Text(commitment.promise, color = SoftWhite, style = MaterialTheme.typography.bodyMedium)
                commitment.practicePrompt?.let { practice ->
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "PRACTICE TODAY",
                        color = CelestialCyan,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(practice, color = MutedText, style = MaterialTheme.typography.bodySmall)
                }
            }
            Column(
                Modifier.width(50.dp).clickable(onClick = onToggle),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SacredIcon(
                    if (practised) R.drawable.icon_check else R.drawable.icon_lotus,
                    if (practised) "Practised today" else "Mark as practised today",
                    Modifier.size(31.dp)
                )
                Text(
                    if (practised) "Today" else "Mark",
                    color = if (practised) CelestialCyan else MutedText,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
