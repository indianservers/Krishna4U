package com.indianservers.krishna4u.core.design

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.CelestialCyan
import com.indianservers.krishna4u.ui.theme.CosmicMidnight
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.SoftWhite
import com.indianservers.krishna4u.ui.theme.WarmGold

@Composable
fun KrishnaCosmicBackground(
    @DrawableRes background: Int = R.drawable.bg_01_cosmic_mandala,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier.fillMaxSize().background(CosmicMidnight)) {
        Image(painterResource(background), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, CosmicMidnight.copy(.25f), CosmicMidnight.copy(.88f)))))
        content()
    }
}

@Composable
fun StardustOverlay(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "stardust")
    val alpha by transition.animateFloat(.35f, .72f, infiniteRepeatable(tween(4200), RepeatMode.Reverse), label = "alpha")
    Image(painterResource(R.drawable.effect_03_stardust_particles), null, modifier.fillMaxSize().alpha(alpha), contentScale = ContentScale.Crop)
}

@Composable
fun AnimatedMandalaHalo(modifier: Modifier = Modifier, durationMillis: Int = 60000) {
    val transition = rememberInfiniteTransition(label = "mandala")
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(durationMillis, easing = LinearEasing)), label = "rotation")
    Image(painterResource(R.drawable.effect_02_rotating_mandala_halo), null, modifier.rotate(rotation), contentScale = ContentScale.Fit)
}

@Composable
fun BreathingLotusOrb(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "breathing")
    val scale by transition.animateFloat(.94f, 1.06f, infiniteRepeatable(tween(2000), RepeatMode.Reverse), label = "scale")
    val alpha by transition.animateFloat(.78f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Reverse), label = "alpha")
    Image(painterResource(R.drawable.effect_01_breathing_lotus_orb), null, modifier.scale(scale).alpha(alpha), contentScale = ContentScale.Fit)
}

@Composable
fun LoadingSacredState(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "loading")
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(1400, easing = LinearEasing)), label = "loadingRotation")
    Image(painterResource(R.drawable.ui_loading_ring), "Loading", modifier.rotate(rotation), contentScale = ContentScale.Fit)
}

@Composable
fun GoldDivider(modifier: Modifier = Modifier) {
    Image(painterResource(R.drawable.ui_gold_divider), null, modifier.fillMaxWidth().height(22.dp), contentScale = ContentScale.FillWidth)
}

@Composable
fun SacredScreenHeader(title: String, subtitle: String? = null, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, style = MaterialTheme.typography.headlineLarge, color = LightGold, textAlign = TextAlign.Center)
        GoldDivider(Modifier.padding(horizontal = 54.dp))
        if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodyLarge, color = SoftWhite.copy(.85f), textAlign = TextAlign.Center)
    }
}

@Composable
fun GlassCard(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null, content: @Composable BoxScope.() -> Unit) {
    val shape = RoundedCornerShape(18.dp)
    val click = if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier
    Box(modifier.then(click).clip(shape).background(Brush.linearGradient(listOf(Color(0xCC07132F), Color(0xB50B2A69)))).border(BorderStroke(1.dp, Brush.linearGradient(listOf(LightGold, CelestialCyan.copy(.55f), WarmGold))), shape).padding(16.dp), content = content)
}

@Composable
fun PrimaryGoldButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick, modifier.height(58.dp), shape = RoundedCornerShape(30.dp), border = BorderStroke(1.dp, LightGold), colors = ButtonDefaults.buttonColors(containerColor = AntiqueGold, contentColor = CosmicMidnight)) {
        Text(text, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
    }
}

@Composable
fun SecondarySacredButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick, modifier.height(52.dp), shape = RoundedCornerShape(28.dp), border = BorderStroke(1.dp, AntiqueGold), colors = ButtonDefaults.buttonColors(containerColor = CosmicMidnight.copy(.7f), contentColor = LightGold)) {
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun SpiritualChip(label: String, @DrawableRes icon: Int, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val border = if (selected) LightGold else CelestialCyan.copy(.55f)
    Row(modifier.height(54.dp).clip(RoundedCornerShape(28.dp)).background(if (selected) AntiqueGold.copy(.14f) else CosmicMidnight.copy(.7f)).border(1.dp, border, RoundedCornerShape(28.dp)).clickable(onClick = onClick).padding(horizontal = 15.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painterResource(icon), null, Modifier.size(28.dp))
        Spacer(Modifier.size(8.dp))
        Text(label, color = if (selected) LightGold else SoftWhite, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun SacredIcon(@DrawableRes icon: Int, description: String?, modifier: Modifier = Modifier) {
    Image(painterResource(icon), description, modifier, contentScale = ContentScale.Fit)
}
