package com.indianservers.krishna4u.feature.wisdom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.audio.EnglishAudioIcon
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.data.repository.OfflineGitaRepository
import com.indianservers.krishna4u.ui.theme.*

data class WisdomTeaching(val chapter: Int, val verse: Int, val title: String)

data class WisdomTheme(
    val id: String,
    val title: String,
    val description: String,
    val practice: String,
    val icon: Int,
    val illustration: Int,
    val teachings: List<WisdomTeaching>
)

private fun teachings(vararg items: Triple<Int, Int, String>) = items.map { WisdomTeaching(it.first, it.second, it.third) }

val wisdomThemes = listOf(
    WisdomTheme("mind", "Mind Control", "Elevate yourself through the mind; whenever it wanders, gently bring it back.", "Guide the mind patiently; do not fight it. Every gentle return builds self-mastery.", R.drawable.icon_mind, R.drawable.illustration_06_meditating_seeker, teachings(
        Triple(6, 5, "Elevate yourself through the mind"), Triple(6, 26, "Gently bring the mind back"), Triple(6, 6, "Friend or enemy within"), Triple(6, 19, "A flame that does not flicker"), Triple(6, 35, "Practice and detachment")
    )),
    WisdomTheme("dharma", "Dharma and Duty", "Perform your necessary duty; your own imperfect path is better than imitating another’s.", "Ask what is truly yours to do, act sincerely, and release the need to control every result.", R.drawable.icon_dharma, R.drawable.illustration_03_krishna_arjuna_chariot, teachings(
        Triple(3, 8, "Perform your necessary duty"), Triple(18, 47, "Your own path is better"), Triple(2, 31, "Stand within your duty"), Triple(3, 19, "Perform duty without attachment"), Triple(18, 46, "Let work become worship")
    )),
    WisdomTheme("leadership", "Leadership and Action", "People follow the example set by leaders; the wise work for collective welfare.", "Before asking others to change, embody the standard you hope they will follow.", R.drawable.icon_leadership, R.drawable.illustration_03_krishna_arjuna_chariot, teachings(
        Triple(3, 21, "Set an example through action"), Triple(3, 25, "Work for collective welfare"), Triple(3, 20, "Lead through selfless work"), Triple(18, 43, "Strength with responsibility"), Triple(18, 46, "Let work become worship")
    )),
    WisdomTheme("strength", "Confidence and Inner Strength", "Do not surrender to weakness; pleasure and pain are temporary and must be endured.", "Name one fear, take one honest step toward it, and let courage grow through action.", R.drawable.icon_courage, R.drawable.illustration_01_krishna_full_body, teachings(
        Triple(2, 3, "Do not surrender to weakness"), Triple(2, 14, "Pleasure and pain are temporary"), Triple(2, 15, "Remain steady through opposites"), Triple(6, 5, "Lift yourself"), Triple(18, 33, "Hold firmly to your purpose")
    )),
    WisdomTheme("compassion", "Compassion and Love", "Neither disturb the world nor be disturbed by it; see every being with equal vision.", "Offer one act of kindness today without asking whether it will be noticed or returned.", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, teachings(
        Triple(12, 15, "Neither disturb nor be disturbed"), Triple(5, 18, "See every being equally"), Triple(12, 13, "Be friendly to every being"), Triple(12, 14, "Let forgiveness steady you"), Triple(6, 32, "Feel another's joy and pain")
    )),
    WisdomTheme("wisdom", "Wisdom and Knowledge", "Seek knowledge with humility and sincere inquiry; nothing purifies like true knowledge.", "Pause before reacting. Ask what is temporary, what is true, and what response reflects your deepest values.", R.drawable.icon_teachings, R.drawable.illustration_07_open_gita, teachings(
        Triple(4, 34, "Seek knowledge with humility"), Triple(4, 38, "Nothing purifies like knowledge"), Triple(2, 11, "Wisdom begins beyond grief"), Triple(2, 16, "Know the real and unreal"), Triple(4, 18, "See action within stillness")
    )),
    WisdomTheme("desires", "Overcoming Desires", "Uncontrolled anger destroys judgment; desire and anger are powerful inner enemies.", "Notice one desire without obeying it immediately. Give yourself space to choose rather than react.", R.drawable.icon_karma, R.drawable.illustration_06_meditating_seeker, teachings(
        Triple(2, 63, "Anger destroys judgment"), Triple(3, 37, "Desire and anger are inner enemies"), Triple(2, 62, "Attachment begins in attention"), Triple(2, 70, "Become steady like the ocean"), Triple(16, 21, "Leave desire, anger and greed")
    )),
    WisdomTheme("unity", "Unity with the Divine", "See the Self in every being and recognize that all existence rests in the Divine.", "Look at someone unlike you and consciously remember the dignity and consciousness you share.", R.drawable.icon_relationships, R.drawable.illustration_08_wisdom_tree, teachings(
        Triple(6, 29, "See the Self in every being"), Triple(9, 4, "All existence rests in the Divine"), Triple(6, 30, "See Krishna everywhere"), Triple(6, 31, "Live within divine unity"), Triple(13, 28, "One presence in many forms")
    )),
    WisdomTheme("fear", "Fear and Anxiety", "Difficult experiences pass; surrender anxiety and seek refuge in the Divine.", "Breathe, name what is within your control, and offer the rest without rehearsing every feared outcome.", R.drawable.icon_courage, R.drawable.illustration_02_krishna_portrait, teachings(
        Triple(2, 14, "Difficult experiences pass"), Triple(18, 66, "Take refuge in the Divine"), Triple(2, 3, "Do not yield to despair"), Triple(4, 10, "Be free from fear and anger"), Triple(16, 1, "Fearlessness is a divine quality")
    )),
    WisdomTheme("faith", "Faith and Devotion", "Dedicated devotion carries one beyond fear; keep the mind and heart centred on Krishna.", "Offer one ordinary action today—your meal, work or care for someone—as a quiet act of devotion.", R.drawable.icon_om, R.drawable.illustration_02_krishna_portrait, teachings(
        Triple(12, 6, "Dedicate every action"), Triple(12, 7, "Devotion carries you beyond fear"), Triple(9, 34, "Centre mind and heart on Krishna"), Triple(9, 22, "Trust the sustaining presence"), Triple(9, 26, "Offer with love")
    )),
    WisdomTheme("equality", "Equality and Oneness", "The wise recognize the same spiritual reality dwelling in every form.", "Meet one person today without measuring status, usefulness or similarity. Look first for shared dignity.", R.drawable.icon_relationships, R.drawable.illustration_08_wisdom_tree, teachings(
        Triple(5, 18, "See every being with equal vision"), Triple(13, 27, "One reality dwells in every form"), Triple(6, 29, "See the Self in all"), Triple(6, 32, "Understand another as yourself"), Triple(13, 28, "The imperishable within all beings")
    )),
    WisdomTheme("forgiveness", "Forgiveness and Tolerance", "Endure life’s opposites and remain compassionate, patient and free from hatred.", "Release the need to replay one hurt today. Keep the lesson and boundary, but loosen resentment's hold.", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, teachings(
        Triple(2, 14, "Endure life's changing opposites"), Triple(12, 13, "Remain compassionate and free from hatred"), Triple(12, 14, "Let patience and forgiveness steady you"), Triple(16, 2, "Gentleness is strength"), Triple(2, 15, "Remain unshaken by adversity")
    )),
    WisdomTheme("sacrifice", "Sacrifice and Renunciation", "Work as an offering; true renunciation means giving up selfish desire and attachment to results.", "Turn one necessary task into an offering by doing it well without demanding recognition or reward.", R.drawable.icon_karma, R.drawable.illustration_03_krishna_arjuna_chariot, teachings(
        Triple(3, 9, "Let work become an offering"), Triple(18, 2, "Renounce selfish desire and results"), Triple(2, 47, "Your right is to action"), Triple(5, 10, "Remain like the lotus"), Triple(18, 11, "Renounce attachment, not all action")
    )),
    WisdomTheme("happiness", "Happiness and Contentment", "Lasting happiness grows from inner discipline, devotion and clarity—not immediate pleasure.", "Notice one quiet source of contentment already present instead of postponing happiness until the next achievement.", R.drawable.icon_inner_peace, R.drawable.illustration_09_peacock_feather, teachings(
        Triple(12, 14, "Contentment through devotion"), Triple(18, 37, "Lasting happiness begins with discipline"), Triple(5, 24, "Find joy and light within"), Triple(6, 20, "Rest in the Self"), Triple(2, 64, "Self-control brings peace")
    )),
    WisdomTheme("discipline", "Discipline and Control", "Balance food, sleep, work and recreation; repeatedly guide the wandering mind back.", "Choose a sustainable rhythm for today: balanced food, focused work, proper rest and three quiet minutes.", R.drawable.icon_meditation, R.drawable.illustration_06_meditating_seeker, teachings(
        Triple(6, 16, "Avoid extremes in food and sleep"), Triple(6, 17, "Balance work, rest and recreation"), Triple(6, 26, "Guide the wandering mind back"), Triple(6, 11, "Prepare a steady seat"), Triple(6, 35, "Practice and detachment")
    )),
    WisdomTheme("detachment", "Attachment and Detachment", "Act with equanimity and dedicate your work without anxiety, ego or possessiveness.", "Release one outcome you cannot control while continuing the action that remains yours.", R.drawable.icon_lotus, R.drawable.illustration_09_peacock_feather, teachings(
        Triple(2, 48, "Act with equanimity"), Triple(3, 30, "Dedicate work without anxiety or ego"), Triple(2, 71, "Walk free of possessiveness"), Triple(5, 3, "Move beyond attraction and aversion"), Triple(12, 12, "Peace follows surrender")
    )),
    WisdomTheme("soul", "Nature of the Soul", "The conscious Self is imperishable, eternal, unchanging and beyond material destruction.", "Observe a changing feeling without calling it your identity. You are the awareness that can witness it.", R.drawable.icon_om, R.drawable.illustration_07_open_gita, teachings(
        Triple(2, 17, "The conscious Self is imperishable"), Triple(2, 24, "Eternal and unchanging"), Triple(2, 20, "Never born, never dying"), Triple(2, 22, "Changing garments"), Triple(2, 23, "Beyond material destruction")
    )),
    WisdomTheme("action", "Action and Inaction", "Understand action, wrong action and inaction; pure action is performed without attachment or selfish desire.", "Before acting, examine intention, consequence and attachment. Then choose the action that serves without feeding ego.", R.drawable.icon_karma, R.drawable.illustration_03_krishna_arjuna_chariot, teachings(
        Triple(4, 17, "Understand action, wrong action and inaction"), Triple(18, 23, "Pure action is free from attachment"), Triple(4, 18, "See inaction within action"), Triple(3, 19, "Act without selfish desire"), Triple(5, 10, "Offer action and remain untouched")
    ))
)

fun wisdomTheme(id: String): WisdomTheme = wisdomThemes.firstOrNull { it.id == id } ?: wisdomThemes.first()

@Composable
private fun rememberWisdomGitaRepository(): OfflineGitaRepository {
    val context = LocalContext.current
    return remember { OfflineGitaRepository(context.applicationContext) }
}

@Composable
fun WisdomForLifeScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val repository = rememberWisdomGitaRepository()
    var query by remember { mutableStateOf("") }
    val filtered = remember(query) {
        wisdomThemes.filter { theme ->
            query.isBlank() || theme.title.contains(query, true) || theme.description.contains(query, true) ||
                theme.teachings.any { teaching -> teaching.title.contains(query, true) || repository.verse(teaching.chapter, teaching.verse)?.englishSummary?.contains(query, true) == true }
        }
    }
    FeatureScaffold("Wisdom for Life", "18 paths from the Bhagavad Gita", R.drawable.bg_08_minimal_starfield, onBack, onNavigate) {
        item {
            GlassCard(Modifier.fillMaxWidth().height(260.dp), onClick = { onNavigate("wisdom_theme/mind") }) {
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.illustration_02_krishna_portrait), "Krishna playing the flute", Modifier.weight(.9f).fillMaxHeight(), contentScale = ContentScale.Fit)
                    Text("What guidance\ndo you seek today?", Modifier.weight(1.1f), color = LightGold, style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
                }
            }
        }
        item {
            SacredListCard(
                "Krishna & Emotional Intelligence",
                "Understand feelings, calm reactions, express needs, practise empathy and resolve conflict.",
                R.drawable.icon_relationships,
                { onNavigate("emotional_intelligence") }
            )
        }
        item {
            SacredListCard(
                "Emotion Wheel",
                "Choose afraid, angry, lonely, guilty or confused for a matching letter, Gita verse and calming action.",
                R.drawable.icon_inner_peace,
                { onNavigate("emotion_wheel") }
            )
        }
        item {
            SacredListCard(
                "Sacred Collectibles",
                "Awaken the Peacock Feather, Flute, Chakra, Lotus and Conch through real learning—not purchases.",
                R.drawable.icon_chakra,
                { onNavigate("collectibles") }
            )
        }
        item {
            SacredListCard(
                "Krishna’s Letters to You",
                "Longer personal messages for failure, loneliness, grief, rejection, guilt and uncertainty.",
                R.drawable.icon_compassion,
                { onNavigate("krishna_letters") }
            )
        }
        item {
            SacredListCard(
                "One-Minute Krishna Stories",
                "108 concise stories, each with one memorable moral and one action for today.",
                R.drawable.icon_flute,
                { onNavigate("one_minute_stories") }
            )
        }
        item {
            SacredListCard(
                "Dharma Decision Stories",
                "Face realistic dilemmas, choose what you would do and reveal Krishna-inspired guidance.",
                R.drawable.icon_dharma,
                { onNavigate("dharma_decisions") }
            )
        }
        item {
            SacredListCard(
                "Difficult Questions About Krishna",
                "Examine war, strategy, punishment, divine play and morally complex Mahabharata events without easy answers.",
                R.drawable.icon_teachings,
                { onNavigate("difficult_questions") }
            )
        }
        item {
            SacredListCard(
                "Parenting with Krishna’s Values",
                "Stories, conversation prompts and family activities for honesty, responsibility, compassion and courage.",
                R.drawable.icon_relationships,
                { onNavigate("parenting_values") }
            )
        }
        item { Text("More ways to grow", color = LightGold, style = MaterialTheme.typography.headlineMedium) }
        item { SacredListCard("The Krishna Within", "Awaken wisdom, playfulness, courage, friendship and compassion.", R.drawable.icon_lotus, { onNavigate("10") }) }
        item { SacredListCard("What Is Dharma?", "Understand truth, duty, compassion and courageous right action.", R.drawable.icon_dharma, { onNavigate("21") }) }
        item { SacredListCard("Questions of the Heart", "Simple answers about dharma, duty, suffering, detachment and the mind.", R.drawable.icon_ask_krishna, { onNavigate("20") }) }
        item { SacredListCard("When Life Feels…", "Choose what you feel and receive a calm next step.", R.drawable.icon_mind, { onNavigate("23") }) }
        item { SacredListCard("Today with Krishna", "A four-step daily practice: listen, understand, reflect and act.", R.drawable.icon_calendar, { onNavigate("24") }) }
        item { SacredListCard("Gita Study Mode", "Slow down and study one teaching with attention.", R.drawable.icon_gita, { onNavigate("19") }) }
        item { OutlinedTextField(query, { query = it }, label = { Text("Search wisdom, emotion or situation") }, leadingIcon = { SacredIcon(R.drawable.icon_search, null, Modifier.size(26.dp)) }, singleLine = true, modifier = Modifier.fillMaxWidth()) }
        item { Text(if (query.isBlank()) "Explore 18 themes" else "${filtered.size} matching themes", color = LightGold, style = MaterialTheme.typography.headlineMedium) }
        items(filtered.chunked(2)) { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { theme ->
                    GlassCard(Modifier.weight(1f).height(165.dp), onClick = { onNavigate("wisdom_theme/${theme.id}") }) {
                        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            SacredIcon(theme.icon, null, Modifier.size(58.dp))
                            Text(theme.title, color = LightGold, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, maxLines = 2)
                            Text("5 teachings", color = MutedText)
                        }
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        if (filtered.isEmpty()) item { GlassCard(Modifier.fillMaxWidth()) { Text("No wisdom themes match “$query”. Try a feeling, situation, or verse idea.", color = MutedText) } }
    }
}

@Composable
fun WisdomThemeScreen(themeId: String, onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val theme = wisdomTheme(themeId)
    val repository = rememberWisdomGitaRepository()
    var tab by remember { mutableStateOf("Essential") }
    val visibleTeachings = if (tab == "Essential") theme.teachings.take(3) else theme.teachings
    FeatureScaffold(theme.title, "WISDOM THEME", R.drawable.bg_07_gita_wisdom, onBack, onNavigate) {
        item {
            GlassCard(Modifier.fillMaxWidth().height(330.dp)) {
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                        AnimatedMandalaHalo(Modifier.size(210.dp))
                        Image(painterResource(theme.illustration), null, Modifier.size(190.dp), contentScale = ContentScale.Fit)
                    }
                    Column(Modifier.weight(1.15f), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(theme.description, color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(18.dp))
                        Text("5 teachings", color = SoftWhite)
                        LinearProgressIndicator(progress = { 1f }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp), color = AntiqueGold, trackColor = CelestialCyan.copy(.2f))
                    }
                }
            }
        }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) { listOf("Essential", "All Verses", "Practice").forEach { name -> SpiritualChip(name, if (name == "Practice") R.drawable.icon_meditation else R.drawable.icon_gita, tab == name, { tab = name }, Modifier.weight(1f)) } } }
        if (tab == "Practice") {
            item { SacredHero(R.drawable.illustration_06_meditating_seeker, "Daily practice", theme.practice) }
            item { PrimaryGoldButton("Begin 3-minute reflection", { onNavigate("25") }, Modifier.fillMaxWidth()) }
        } else {
            item { Text(if (tab == "Essential") "Important ślokas" else "All verses", color = LightGold, style = MaterialTheme.typography.headlineMedium) }
            items(visibleTeachings) { teaching ->
                val verse = repository.verse(teaching.chapter, teaching.verse)
                SacredListCard("${teaching.chapter}.${teaching.verse} · ${teaching.title}", verse?.englishSummary ?: "Open this teaching", theme.icon, { onNavigate("wisdom_sloka/${theme.id}/${teaching.chapter}/${teaching.verse}") })
            }
            item { SacredListCard("Daily practice", "3-minute reflection", R.drawable.icon_meditation, { tab = "Practice" }) }
        }
    }
}

@Composable
fun WisdomSlokaScreen(
    themeId: String,
    chapterNumber: Int,
    verseNumber: Int,
    bookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = rememberWisdomGitaRepository()
    val theme = wisdomTheme(themeId)
    val verse = repository.verse(chapterNumber, verseNumber) ?: return
    val teachingIndex = theme.teachings.indexOfFirst { it.chapter == chapterNumber && it.verse == verseNumber }.coerceAtLeast(0)
    FeatureScaffold("Śloka $chapterNumber.$verseNumber", repository.chapter(chapterNumber).title, R.drawable.bg_07_gita_wisdom, onBack, onNavigate, false) {
        item { SacredHero(R.drawable.illustration_07_open_gita, verse.sanskrit, verse.transliteration) }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(8.dp)) { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Text("English meaning", Modifier.weight(1f), color = LightGold, style = MaterialTheme.typography.headlineMedium); EnglishAudioIcon(verse.englishSummary, Modifier.size(44.dp)) }; Text(verse.englishSummary, color = SoftWhite, style = MaterialTheme.typography.bodyLarge); Text("Translation: ${verse.translator}", color = MutedText) } } }
        item { GlassCard(Modifier.fillMaxWidth()) { Column(verticalArrangement = Arrangement.spacedBy(8.dp)) { Text("Life insight", color = LightGold, style = MaterialTheme.typography.headlineMedium); Text(theme.practice, color = SoftWhite, style = MaterialTheme.typography.bodyLarge) } } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SecondarySacredButton(if (bookmarked) "✓ Saved" else "☆ Bookmark", onToggleBookmark, Modifier.weight(1f))
            SecondarySacredButton("Share", { shareSacredText(context, "Bhagavad Gita $chapterNumber.$verseNumber", "Bhagavad Gita $chapterNumber.$verseNumber\n\n${verse.sanskrit}\n\n${verse.transliteration}\n\nEnglish meaning:\n${verse.englishSummary}\n\nLife insight:\n${theme.practice}\n\nTranslation: ${verse.translator}\nShared from Krishna For You") }, Modifier.weight(1f))
        } }
        item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SecondarySacredButton("Previous", { if (teachingIndex > 0) theme.teachings[teachingIndex - 1].let { onNavigate("wisdom_sloka/${theme.id}/${it.chapter}/${it.verse}") } }, Modifier.weight(1f))
            SecondarySacredButton("All ślokas", { onNavigate("wisdom_theme/${theme.id}") }, Modifier.weight(1f))
            PrimaryGoldButton("Next", { if (teachingIndex < theme.teachings.lastIndex) theme.teachings[teachingIndex + 1].let { onNavigate("wisdom_sloka/${theme.id}/${it.chapter}/${it.verse}") } }, Modifier.weight(1f))
        } }
    }
}
