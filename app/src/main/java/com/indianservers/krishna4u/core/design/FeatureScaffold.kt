package com.indianservers.krishna4u.core.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite
import com.indianservers.krishna4u.ui.theme.LocalReducedMotion
import kotlinx.coroutines.delay

@Composable
fun FeatureScaffold(
    title: String,
    subtitle: String,
    @DrawableRes background: Int,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit,
    showBottomBar: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    KrishnaCosmicBackground(background) {
        Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().statusBarsPadding()) {
            Row(Modifier.fillMaxWidth().heightIn(min = 64.dp).padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(48.dp).clickable(onClick = onBack), contentAlignment = Alignment.Center) {
                    SacredIcon(R.drawable.icon_previous, "Back", Modifier.size(28.dp))
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(title, color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                    Text(subtitle, color = MutedText, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                }
                AnimatedPeacockFeather("$title|$subtitle", Modifier.size(48.dp).padding(7.dp))
            }
            GoldDivider(Modifier.padding(horizontal = 40.dp))
            LazyColumn(
                Modifier.weight(1f).then(if (showBottomBar) Modifier else Modifier.navigationBarsPadding()),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                content = content
            )
            if (showBottomBar) SacredBottomNavigation(onNavigate)
        }
        val reducedMotion = LocalReducedMotion.current
        var revealLotus by remember(title) { mutableStateOf(false) }
        LaunchedEffect(title, reducedMotion) {
            if (!reducedMotion) {
                revealLotus = true
                delay(620)
                revealLotus = false
            }
        }
        AnimatedVisibility(
            visible = revealLotus,
            modifier = Modifier.align(Alignment.TopCenter).statusBarsPadding().padding(top = 62.dp),
            enter = fadeIn() + scaleIn(initialScale = .25f),
            exit = fadeOut() + scaleOut(targetScale = 1.25f)
        ) {
            SacredIcon(R.drawable.icon_lotus, null, Modifier.size(58.dp))
        }
        }
    }
}

@Composable
fun SacredHero(
    @DrawableRes illustration: Int,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
    imageHeight: Dp = 230.dp
) {
    GlassCard(modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(illustration), null, Modifier.fillMaxWidth().height(imageHeight), contentScale = ContentScale.Fit)
            Text(title, color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
            Text(body, color = SoftWhite.copy(.88f), style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun SacredListCard(title: String, body: String, @DrawableRes icon: Int, onClick: (() -> Unit)? = null, modifier: Modifier = Modifier) {
    GlassCard(modifier.fillMaxWidth(), onClick) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            SacredIcon(icon, null, Modifier.size(42.dp))
            Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(title, color = LightGold, style = MaterialTheme.typography.titleLarge)
                Text(body, color = MutedText, style = MaterialTheme.typography.bodyMedium)
            }
            if (onClick != null) {
                SacredIcon(R.drawable.icon_next, "Open", Modifier.size(26.dp))
            }
        }
    }
}

@Composable
fun SacredBottomNavigation(onNavigate: (String) -> Unit) {
    val items = listOf(
        Triple("Home", R.drawable.icon_home, "05"), Triple("Explore", R.drawable.icon_explore, "wisdom"),
        Triple("Gita", R.drawable.icon_gita, "12"), Triple("Journal", R.drawable.icon_journal, "26")
    )
    Box(Modifier.fillMaxWidth().navigationBarsPadding()) {
        Row(
            Modifier.fillMaxWidth().height(68.dp).padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (label, icon, route) ->
                Column(
                    Modifier.weight(1f).fillMaxHeight().clickable { onNavigate(route) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SacredIcon(icon, label, Modifier.size(30.dp))
                    Spacer(Modifier.height(2.dp))
                    Text(label, color = MutedText, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                }
            }
        }
    }
}
