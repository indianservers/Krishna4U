package com.indianservers.krishna4u.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.localization.appLanguage
import com.indianservers.krishna4u.core.localization.appLanguages
import com.indianservers.krishna4u.core.design.AnimatedMandalaHalo
import com.indianservers.krishna4u.core.design.BreathingLotusOrb
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.GoldDivider
import com.indianservers.krishna4u.core.design.KrishnaCosmicBackground
import com.indianservers.krishna4u.core.design.LoadingSacredState
import com.indianservers.krishna4u.core.design.PrimaryGoldButton
import com.indianservers.krishna4u.core.design.SacredScreenHeader
import com.indianservers.krishna4u.core.design.SecondarySacredButton
import com.indianservers.krishna4u.core.design.SpiritualChip
import com.indianservers.krishna4u.core.design.StardustOverlay
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.CelestialCyan
import com.indianservers.krishna4u.ui.theme.CosmicMidnight
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) { delay(3000); onFinished() }
    KrishnaCosmicBackground {
        StardustOverlay()
        AnimatedMandalaHalo(Modifier.align(Alignment.TopCenter).padding(top = 78.dp).size(340.dp))
        Image(painterResource(R.drawable.illustration_01_krishna_full_body), null, Modifier.align(Alignment.TopCenter).padding(top = 70.dp).height(560.dp), contentScale = ContentScale.Fit)
        Column(Modifier.align(Alignment.BottomCenter).navigationBarsPadding().padding(horizontal = 24.dp, vertical = 24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("K R I S H N A   F O R   Y O U", color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
            GoldDivider(Modifier.padding(horizontal = 36.dp))
            Text("Timeless wisdom. For your life.", color = SoftWhite, fontSize = 17.sp)
            Spacer(Modifier.height(24.dp))
            LoadingSacredState(Modifier.size(42.dp))
            Spacer(Modifier.height(18.dp))
            Text("from Sai Satish", color = AntiqueGold, style = MaterialTheme.typography.titleLarge)
            Text("Powered by Indian Servers Pvt Ltd", color = AntiqueGold, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private data class OnboardingPage(val title: String, val body: String, val background: Int, val illustration: Int)

@Composable
fun DivineOnboardingScreen(onSkip: () -> Unit, onBegin: () -> Unit) {
    val pages = remember { listOf(
        OnboardingPage("Wisdom for every\nseason of life", "Walk with Krishna through timeless stories, teachings and the Bhagavad Gita.", R.drawable.bg_03_kurukshetra_cosmos, R.drawable.illustration_03_krishna_arjuna_chariot),
        OnboardingPage("A companion for\nyour inner journey", "Discover teachings for courage, peace, relationships and purpose.", R.drawable.bg_02_vrindavan_dawn, R.drawable.illustration_02_krishna_portrait),
        OnboardingPage("Ancient truth.\nPresent-day clarity.", "Study, reflect and build a gentle daily spiritual practice.", R.drawable.bg_07_gita_wisdom, R.drawable.illustration_07_open_gita)
    ) }
    val pager = rememberPagerState(pageCount = { pages.size })
    Box(Modifier.fillMaxSize().background(CosmicMidnight)) {
        HorizontalPager(pager, Modifier.fillMaxSize()) { index ->
            val page = pages[index]
            KrishnaCosmicBackground(page.background) {
                Image(painterResource(page.illustration), null, Modifier.align(Alignment.TopCenter).padding(top = 110.dp).fillMaxWidth().height(470.dp), contentScale = ContentScale.Fit)
                Column(Modifier.align(Alignment.BottomCenter).navigationBarsPadding().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(page.title, color = LightGold, style = MaterialTheme.typography.displayLarge, textAlign = TextAlign.Center)
                    GoldDivider(Modifier.padding(horizontal = 42.dp))
                    Text(page.body, color = SoftWhite, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(24.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SecondarySacredButton("Skip", onSkip, Modifier.weight(.72f))
                        PrimaryGoldButton(if (index == 2) "Continue" else "Begin Journey", onBegin, Modifier.weight(1.5f))
                    }
                    Spacer(Modifier.height(18.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) { repeat(3) { dot -> Box(Modifier.size(9.dp).clip(CircleShape).background(if (dot == index) AntiqueGold else CelestialCyan.copy(.35f))) } }
                }
            }
        }
        Text("${pager.currentPage + 1} of 3", Modifier.align(Alignment.TopCenter).statusBarsPadding().padding(top = 18.dp), color = LightGold, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ChooseLanguageScreen(selected: String, onSelected: (String) -> Unit, onContinue: () -> Unit) {
    val selectedLanguage = appLanguage(selected)
    KrishnaCosmicBackground(R.drawable.bg_08_minimal_starfield) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).statusBarsPadding().navigationBarsPadding().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.illustration_09_peacock_feather), null, Modifier.height(118.dp), contentScale = ContentScale.Fit)
            SacredScreenHeader("Choose your language", "Experience Krishna’s wisdom in the words\nclosest to your heart.")
            Spacer(Modifier.height(22.dp))
            appLanguages.forEach { language ->
                GlassCard(
                    Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    onClick = if (language.available) ({ onSelected(language.code) }) else null
                ) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(language.nativeName, color = if (language.available) SoftWhite else MutedText, style = MaterialTheme.typography.titleLarge)
                            if (language.englishName != language.nativeName) Text(language.englishName, color = MutedText, style = MaterialTheme.typography.bodyMedium)
                        }
                        if (selectedLanguage.code == language.code && language.available) {
                            Image(painterResource(R.drawable.icon_check), "Selected", Modifier.size(34.dp))
                        } else if (!language.available) {
                            Text("Coming soon", color = AntiqueGold, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
            GlassCard(Modifier.fillMaxWidth().padding(top = 6.dp)) { Row(verticalAlignment = Alignment.CenterVertically) { Image(painterResource(R.drawable.icon_language), null, Modifier.size(30.dp)); Spacer(Modifier.width(12.dp)); Text("English is available now. More translations are coming soon.", color = MutedText) } }
            Spacer(Modifier.height(26.dp))
            PrimaryGoldButton("Continue", onContinue, Modifier.fillMaxWidth())
        }
    }
}

private data class Interest(val title: String, val icon: Int)

@Composable
fun PersonaliseJourneyScreen(onComplete: (Set<String>) -> Unit) {
    val interests = remember { listOf(
        Interest("Distressed", R.drawable.icon_inner_peace), Interest("Anxious", R.drawable.icon_meditation),
        Interest("Lonely", R.drawable.icon_om), Interest("Heartbreak", R.drawable.icon_relationships),
        Interest("Grief", R.drawable.icon_lotus), Interest("Self-Doubt", R.drawable.icon_courage),
        Interest("Failure", R.drawable.icon_karma), Interest("Relationships", R.drawable.icon_relationships),
        Interest("Family", R.drawable.icon_home), Interest("Career & Study", R.drawable.icon_teachings),
        Interest("Purpose", R.drawable.icon_purpose), Interest("Inner Peace", R.drawable.icon_dharma)
    ) }
    var selected by remember { mutableStateOf(setOf("Inner Peace")) }
    KrishnaCosmicBackground(R.drawable.bg_01_cosmic_mandala) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).statusBarsPadding().navigationBarsPadding().padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.illustration_09_peacock_feather), null, Modifier.height(75.dp), contentScale = ContentScale.Fit)
            Text("K R I S H N A  ·  F O R  Y O U", color = LightGold, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(20.dp))
            SacredScreenHeader("What do you need today?", "Choose what you wish to understand,\nheal or strengthen.")
            Box(Modifier.fillMaxWidth().height(260.dp), contentAlignment = Alignment.Center) {
                BreathingLotusOrb(Modifier.size(260.dp))
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                interests.chunked(2).forEach { row -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    row.forEach { item -> SpiritualChip(item.title, item.icon, item.title in selected, { selected = if (item.title in selected) selected - item.title else selected + item.title }, Modifier.weight(1f)) }
                    if (row.size == 1) Spacer(Modifier.weight(1f))
                } }
            }
            Spacer(Modifier.height(24.dp))
            PrimaryGoldButton("Hear Krishna's Message", { onComplete(selected.ifEmpty { setOf("Inner Peace") }) }, Modifier.fillMaxWidth())
            Spacer(Modifier.height(14.dp))
            Text("You can change this anytime.", color = MutedText)
        }
    }
}
