package com.indianservers.krishna4u.feature.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.ui.theme.*

data class MockupEntry(val number: String, val name: String, val implemented: Boolean) { val filename get() = "${number}_${name.replace(' ', '_')}.png" }

val mockupEntries = listOf(
    "Splash", "Divine Onboarding", "Choose Language", "Personalise Journey", "Home", "Krishna Life Journey", "Life Event Details", "Teachings Library", "Teaching Details", "Lessons From Krishna", "Apply To Your Life", "Gita Overview", "Chapter Explorer", "Chapter Summary", "All Slokas", "Individual Sloka", "Sloka Explanation", "Listen To Gita", "Gita Study Mode", "FAQ Library", "What Is Dharma", "Ask Krishna", "Life Situations", "Today With Krishna", "Meditation And Chanting", "Bookmarks Notes Reflections", "Learning Progress", "Profile Settings"
).mapIndexed { index, name -> MockupEntry((index + 1).toString().padStart(2, '0'), name, index < 5) }

@Composable
fun MockupGalleryScreen(onOpen: (String) -> Unit) {
    KrishnaCosmicBackground(R.drawable.bg_08_minimal_starfield) {
        Column(Modifier.fillMaxSize().statusBarsPadding().padding(horizontal = 16.dp)) {
            SacredScreenHeader("Mockup Gallery", "Debug-only direct access to all 28 screens")
            LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                items(mockupEntries) { item ->
                    GlassCard(Modifier.fillMaxWidth(), onClick = { onOpen(item.number) }) {
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text(item.number, color = AntiqueGold, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                                Text(item.name, color = SoftWhite, style = MaterialTheme.typography.titleLarge)
                                Text(item.filename, color = MutedText, style = MaterialTheme.typography.bodyMedium)
                                Text(if (item.implemented) "Implemented" else "Foundation pending", color = if (item.implemented) AntiqueGold else MutedText)
                            }
                            Image(painterResource(R.drawable.icon_next), "Open ${item.name}", Modifier.size(32.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PendingMockupScreen(entry: MockupEntry, onBack: () -> Unit) {
    KrishnaCosmicBackground(R.drawable.bg_08_minimal_starfield) {
        Column(Modifier.fillMaxSize().statusBarsPadding().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(painterResource(R.drawable.illustration_09_peacock_feather), null, Modifier.size(180.dp))
            Text("${entry.number} · ${entry.name}", color = AntiqueGold, style = MaterialTheme.typography.headlineLarge)
            Text("The route is wired for gallery navigation. Full mockup reconstruction is scheduled in the next implementation phase.", color = SoftWhite, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.size(24.dp))
            PrimaryGoldButton("Back to Gallery", onBack, Modifier.fillMaxWidth())
        }
    }
}
