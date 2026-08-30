package com.indianservers.krishna4u.feature.guidance

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.*
import com.indianservers.krishna4u.core.sharing.shareSacredText
import com.indianservers.krishna4u.ui.theme.AntiqueGold
import com.indianservers.krishna4u.ui.theme.LightGold
import com.indianservers.krishna4u.ui.theme.MutedText
import com.indianservers.krishna4u.ui.theme.SoftWhite
import kotlin.random.Random

data class KrishnaComfortMessage(val id: String, val situation: String, val text: String)

private data class MessageSituation(
    val title: String,
    val selectedBy: Set<String>,
    val messages: List<String>
)

private fun messageLines(block: String) = block.trimIndent().lineSequence().map(String::trim).filter(String::isNotBlank).toList()

private val messageSituations = listOf(
    MessageSituation("When You Feel Distressed", setOf("Distressed", "Bhagavad Gita"), messageLines("""
        Even when your heart is heavy and your path unclear, know that I walk with you through the silence. Trust the journey, even if it begins with tears.
        The pain you carry today is not the end. It is a pause where your soul is learning its greatest strength.
        Sometimes, my child, storms do not come to hurt you, but to clear your path of what was never yours.
        You are not abandoned. Let your heart become ready for possibilities greater than those you can see today.
        Do not fear the loneliness. It is often in the quiet that you can hear your own heart most clearly.
        Even when you feel shattered, remember that gold is purified through fire. Your strength is being revealed too.
        The world may misunderstand your tears, but none of them make you weak and none need to be hidden from Me.
        What left your story cannot take away the love, courage and wisdom still living within you.
        The pain in your heart is valid. Let it become a bridge, not a barrier, to who you are becoming.
        Stop asking only why they left. Begin asking what new life is inviting you to discover.
        One day you may look back and see that this painful road also brought you closer to your deepest strength.
        Your breaking point need not be your ending. Let us begin rebuilding, one gentle piece at a time.
        Let your scars remind you not only of pain, but of every time you survived and continued.
        Not every ending is a punishment. Some endings are quiet redirections toward your purpose.
        When the world grows loud, sit with Me in silence. Remember who you are beneath every voice and expectation.
        Some storms wash away what hurts; others reveal your strength. Meet both with patience.
        Not all who leave were meant to stay forever. Some become lessons that prepare the heart for healthier love.
        Even in delay, life is moving. Do not confuse silence with absence.
        Your loneliness is not empty. Fill this space with care, prayer and the people who treat your heart gently.
        I never promised a path without pain, but you do not have to walk through it alone.
        You thought it was over. Perhaps only one chapter closed so that another could begin.
        You see brokenness; I see the first stones of something steady being built within you.
        Your tears do not weaken you. Let them fall, breathe slowly, and give your heart permission to heal.
        Even on your knees, I see the courage that brought you this far. Stay, breathe and rise when you are ready.
    """)),
    MessageSituation("When Anxiety Feels Loud", setOf("Anxious", "Inner Peace"), messageLines("""
        My child, you do not need to solve tomorrow tonight. Place this moment in your hands and breathe with Me.
        A frightened thought is not a prophecy. Let it pass before you decide what is true.
        Return to what is here: one breath, one step, one choice. That is enough for now.
        You are carrying questions that time alone can answer. Set them down and let this hour be gentle.
        Courage is not the absence of fear. It is the quiet decision that fear will not lead your life.
        When your mind runs toward every possible ending, bring it back to the one action you can take today.
        Do not mistake uncertainty for danger. Some unknown roads are simply waiting to unfold.
        Breathe in slowly. You are here, you are safe in this moment, and you are not facing it alone.
        Let preparation guide you and let worry rest. Only one of them can help you move forward.
        Your thoughts may be loud, but the steady wisdom within you is deeper than their noise.
        Give your mind the same kindness you would give a tired friend. It has been trying to protect you.
        Peace may not arrive all at once. Welcome the smallest quiet, and let it grow.
    """)),
    MessageSituation("When You Feel Lonely", setOf("Lonely", "Devotion"), messageLines("""
        My dear one, being alone in this moment does not mean you are unwanted or forgotten.
        Let this quiet become a place where you meet yourself with tenderness instead of judgment.
        Your heart was made for connection. Reach toward one safe person; a small hello can open a door.
        Do not chase company that asks you to abandon your dignity. Wait for love that lets you remain yourself.
        I am present in the silence between your thoughts and in every kindness that finds its way to you.
        Loneliness is a season, not your identity. Seasons change even when the night feels long.
        Your presence matters more than you know. The world is different because your particular light is in it.
        Make your solitude sacred today: breathe, walk, pray, create, or sit beneath the open sky.
        You do not need a crowd to be held. Begin with one honest connection and let trust grow slowly.
        The empty space beside you is not proof that love has ended. It is room for healthier love to arrive.
        Speak to yourself gently tonight. You deserve companionship from your own heart too.
        Stay open, my child. There are people you have not yet met who will be grateful that you continued.
    """)),
    MessageSituation("After Heartbreak", setOf("Heartbreak", "Relationships"), messageLines("""
        My child, someone leaving does not carry away your worth. What is sacred within you remains.
        Grieve the love honestly, but do not turn another person's choice into a sentence against yourself.
        You can miss them and still know that returning would not be kind to your heart.
        Healing does not demand that you forget. It asks that the memory stop deciding your future.
        Do not hurry to replace what ended. First return to the parts of yourself you left behind.
        Release without revenge. Peace is a stronger ending than bitterness.
        Love should not require you to beg for respect, clarity or safety.
        What you learned can travel with you; the pain itself does not have to.
        Some love teaches through staying, and some through leaving. Both can deepen wisdom.
        Your heart is wounded, not ruined. Give it time, truth and gentle boundaries.
        Do not measure the future by the person who could not meet you there.
        When you are ready, love can return—not as a copy of the past, but as something healthier.
    """)),
    MessageSituation("While Grieving a Loss", setOf("Grief"), messageLines("""
        My dear one, grief is love searching for the person or life it once held. Let it move gently through you.
        There is no correct timetable for missing someone. Heal at the pace your heart can carry.
        Tears are not a failure of faith. They are evidence that something precious mattered.
        Keep the memory with love, but give yourself permission to keep living too.
        Some days will feel steady and others will bring the ache back. Neither means you are going backward.
        Let trusted people sit beside you, even when there are no words that can repair the loss.
        You need not find a lesson in every sorrow. Today, simply care for the heart that is hurting.
        Their influence still lives in the kindness, courage and memories you carry forward.
        Eat, rest and breathe, my child. The body also needs tenderness while the heart mourns.
        Joy returning is not betrayal. It is life making room beside the love that remains.
        Speak their name when you need to. Sacred memories do not disappear when shared.
        Take this day in small pieces. You do not have to carry all of tomorrow's grief now.
    """)),
    MessageSituation("After Rejection", setOf("Self-Doubt", "Failure"), messageLines("""
        My child, one person's no is not the final truth about who you are.
        This closed door may be painful, but it cannot close every path meant for your feet.
        Do not reject yourself because someone else could not recognize what you offered.
        Keep your dignity. You never need to shrink in order to be chosen.
        Let disappointment visit, but do not give it a permanent home within you.
        Ask what can be learned, then direct your energy toward places where it can grow.
        Approval from everyone would cost you the freedom to live truthfully.
        A path that refuses you may be creating time for a path that fits you better.
        Your value existed before their decision and remains after it.
        Try again when your heart is ready, not to prove yourself, but to honour your possibility.
        Some opportunities are unsuitable, not stolen. Trust yourself enough to keep looking.
        Stand gently but firmly in your worth. Rejection cannot define a soul that continues.
    """)),
    MessageSituation("After Failure or a Mistake", setOf("Failure", "Karma"), messageLines("""
        My dear one, failure is an event in your story, not the name of its author.
        Look at the lesson without turning it into a weapon against yourself.
        A sincere beginning after failure carries more wisdom than the first attempt ever could.
        The result disappointed you, but your effort still built strength, skill and understanding.
        Do not compare your difficult chapter with another person's celebration.
        Progress often wears the clothes of mistakes before it reveals itself.
        Take responsibility for what is yours, repair what you can, and release endless punishment.
        Perfection was never required. Honest action and willingness to grow are enough.
        Rest if you must, but do not let embarrassment choose your future.
        The road did not disappear because you stumbled. Rise with better knowledge of the ground.
        Begin smaller this time, remain consistent, and let each step restore your trust in yourself.
        I see not only what went wrong, but the courage with which you are willing to try again.
    """)),
    MessageSituation("When You Doubt Yourself", setOf("Self-Doubt", "Courage"), messageLines("""
        My child, confidence is not something you must find before acting. It grows because you act.
        Remember the difficult days you already survived. Their strength did not leave you.
        You are comparing your private uncertainty with someone else's visible confidence.
        Imperfection does not cancel your ability. It only proves that you are learning.
        Speak inwardly as gently as you would speak to someone you deeply love.
        Take one courageous step before your doubt finishes making its argument.
        You do not have to be the best to be worthy of beginning.
        The wisdom you seek is not entirely outside you. Sit quietly and listen within.
        Let your values decide who you are, not the changing opinions around you.
        Small promises kept to yourself will rebuild confidence more surely than praise.
        Your voice deserves space even when it trembles.
        I have not asked you to feel fearless. I ask you to walk honestly with the strength you have today.
    """)),
    MessageSituation("When Anger Takes Over", setOf("Relationships", "Family"), messageLines("""
        Pause, my child. The words spoken in one burning moment may remain after the fire is gone.
        Your anger may be carrying hurt beneath it. Listen for what truly needs protection or healing.
        Strength is not the loudest response. Sometimes it is the breath that prevents harm.
        You may be firm without becoming cruel. Truth does not need humiliation to be powerful.
        Revenge keeps you tied to the wound. Choose a boundary that sets your spirit free.
        Forgiveness does not mean giving harmful people another opportunity to hurt you.
        Move your body, write the truth, or sit in silence before you answer.
        Ask whether you want understanding or victory. Only one can restore the relationship.
        Anger is a messenger, not a master. Receive its information and choose your own action.
        Distance can be wiser than another argument when respect has disappeared.
        Let justice guide you without letting hatred reshape your heart.
        When calm returns, speak from your values, not from the wound that was touched.
    """)),
    MessageSituation("When Guilt Follows You", setOf("Failure", "Karma"), messageLines("""
        My dear one, remorse can guide you toward goodness, but endless shame only keeps you trapped.
        Name what happened honestly. Truth is the first door through which healing enters.
        Apologize without defending yourself, and repair what is still within your power.
        You are responsible for your actions, but you are not required to remain your worst action forever.
        Let the past teach your present conduct instead of repeatedly sentencing your heart.
        Self-forgiveness does not erase the harm. It gives you strength not to repeat it.
        If another person needs space, respect it while you continue becoming better.
        The fact that you regret it reveals that your conscience is still alive and calling you forward.
        Do not confuse suffering with atonement. Changed behaviour is the truest apology.
        Carry the lesson, return the punishment, and choose integrity today.
        Compassion can hold both truths: you caused pain, and you remain capable of goodness.
        Come back to the path, my child. A sincere return matters more than a perfect history.
    """)),
    MessageSituation("When Everything Feels Too Much", setOf("Distressed", "Anxious", "Inner Peace"), messageLines("""
        My child, do not carry the entire mountain. Choose the next stone that can be moved.
        Not everything asking for your attention deserves it at the same time.
        Rest before your body must force you to stop. Rest is part of responsibility.
        Write down what is urgent, what can wait, and what was never yours to carry.
        Ask for help without shame. Even the strongest hands were not made to hold everything alone.
        Your worth is not counted by how much you complete before nightfall.
        Breathe slowly and finish one small task. Calm action will show you the next.
        Release today's unfinished work from your mind. Tomorrow can receive its own share.
        You are not failing; you are receiving a signal that your load needs kindness and change.
        Make the day smaller: water, food, one call, one task, one moment of quiet.
        Some responsibilities can be delegated, delayed or declined. Peace also requires boundaries.
        I am with you in the pause as surely as in the progress. You may slow down.
    """)),
    MessageSituation("When You Must Decide", setOf("Purpose", "Dharma"), messageLines("""
        My dear one, begin by asking which choice lets you remain truthful to your deepest values.
        Fear and intuition can sound alike. Give them silence, and notice which one speaks with steadiness.
        You may not receive perfect certainty. Wisdom often chooses with care and then learns while walking.
        Consider who may be helped or harmed, including the person you are becoming.
        Do not choose only to avoid disappointing everyone. That road eventually disappoints your own soul.
        Seek wise counsel, but do not surrender the responsibility that belongs to you.
        A peaceful choice is not always an easy choice; sometimes integrity asks for courage.
        Remove urgency that others created. Important decisions deserve an unhurried mind.
        Ask what you would choose if approval and fear were both silent.
        Once you have examined the path sincerely, step forward without reopening the decision each hour.
        If new truth appears, changing direction is wisdom, not weakness.
        Choose the next right action. The whole road can reveal itself after you begin.
    """)),
    MessageSituation("While You Are Waiting", setOf("Career & Study", "Purpose", "Inner Peace"), messageLines("""
        My child, delay is not always denial. Some seasons are preparing roots before they reveal flowers.
        Use this waiting room to strengthen what the next door will require.
        Do not pause your whole life for one answer. Continue the work that keeps your spirit alive.
        Another person's timeline is not evidence that you are late.
        Patience is active: learn, prepare, adjust and remain open.
        Silence does not mean nothing is happening. Growth is often quiet before it becomes visible.
        If the path remains closed, you are allowed to examine another path without calling it failure.
        Celebrate the movement that is easy to overlook: one application, one lesson, one brave conversation.
        Do not let waiting persuade you to accept disrespect or indefinite uncertainty from others.
        What belongs in your life should meet both your hope and your wise effort.
        Let today contain something meaningful even before the result arrives.
        I am with you between the prayer and the answer. This space also belongs to your journey.
    """)),
    MessageSituation("For Career and Study Pressure", setOf("Career & Study"), messageLines("""
        My dear one, prepare sincerely and release the belief that one result can measure your whole future.
        Your marks, title or salary describe a circumstance; they do not define your intelligence or worth.
        Study or work in small focused periods. Consistency will carry you farther than panic.
        Rest is not time stolen from success. A clear mind is part of good preparation.
        Receive useful criticism without allowing harsh voices to become your inner voice.
        Another person's promotion or score does not reduce the opportunities available to you.
        Keep integrity when pressure invites shortcuts. Character travels farther than one achievement.
        If you lose an opportunity, you have not lost the skills and courage that brought you there.
        Ask for guidance when you are stuck. Wisdom includes knowing when not to struggle alone.
        Enter the interview, classroom or examination with effort in your hands and calm in your breath.
        Your career is part of life, not the owner of it. Protect the relationships and health that sustain you.
        Give your best action today, then let the result arrive without surrendering your peace to it.
    """)),
    MessageSituation("During Family or Relationship Conflict", setOf("Family", "Relationships"), messageLines("""
        My child, listen for the hurt beneath the words before preparing your reply.
        Respect does not require accepting control, humiliation or repeated harm.
        A clear boundary can be an act of love toward yourself and the relationship.
        Do not seek to win one argument by damaging the trust you will need tomorrow.
        Apologize for what is yours without carrying blame that belongs to someone else.
        Choose a calmer time for difficult truth. Timing can protect a message from becoming another wound.
        Love cannot grow where one person must continually disappear to preserve peace.
        If every conversation becomes unsafe, distance and trusted support may be necessary.
        Speak about the behaviour and its effect rather than declaring the other person entirely bad.
        Children and relatives should not be made messengers in conflicts that adults must resolve.
        Some relationships heal through conversation; others require boundaries. Wisdom knows the difference.
        Seek help when the pattern is larger than both of you. You were never asked to solve every conflict alone.
    """)),
    MessageSituation("When You Seek Purpose or Faith", setOf("Purpose", "Devotion", "Inner Peace", "Bhagavad Gita"), messageLines("""
        My dear one, purpose is often built through faithful small actions, not discovered in one dazzling moment.
        Notice what awakens compassion in you. Service often points toward the work your heart understands.
        You do not need perfect words or perfect faith to sit with Me. Honest silence is also prayer.
        A season of doubt does not make you less spiritual. Questions can deepen roots when faced sincerely.
        Stop comparing your inner journey with another person's visible devotion.
        Your ordinary responsibilities can become sacred when carried with attention and kindness.
        Begin with the ability already in your hands and the person you can help today.
        Purpose may change as you grow. A new calling does not make the old season meaningless.
        Recognition is not the proof of meaningful work. Much that changes the world happens quietly.
        When you feel far from Me, return through one breath, one verse, one act of care.
        You are not behind. The path becomes clearer each time your actions and values become one.
        Walk gently but faithfully. Meaning will meet you while you are moving.
    """))
)

fun krishnaMessagesFor(selectedNeeds: Set<String>): List<KrishnaComfortMessage> {
    val matching = messageSituations.filter { situation -> situation.selectedBy.any(selectedNeeds::contains) }
        .ifEmpty { messageSituations.filter { "Inner Peace" in it.selectedBy } }
    val titles = matching.mapTo(mutableSetOf()) { it.title }
    return allKrishnaComfortMessages.filter { it.situation in titles }
}

val totalKrishnaComfortMessages: Int = messageSituations.sumOf { it.messages.size }
val allKrishnaComfortMessages: List<KrishnaComfortMessage> = messageSituations.flatMapIndexed { situationIndex, situation ->
    situation.messages.mapIndexed { messageIndex, text -> KrishnaComfortMessage("$situationIndex-$messageIndex", situation.title, text) }
}

fun krishnaMessageById(id: String): KrishnaComfortMessage? = allKrishnaComfortMessages.firstOrNull { it.id == id }

@Composable
fun KrishnaSpeaksScreen(
    displayName: String,
    selectedNeeds: Set<String>,
    messageIndex: Int,
    onNext: (Int) -> Unit,
    bookmarked: (String) -> Boolean,
    onToggleBookmark: (String) -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    val messages = remember(selectedNeeds) { krishnaMessagesFor(selectedNeeds) }
    val safeIndex = messageIndex.mod(messages.size)
    val message = messages[safeIndex]
    FeatureScaffold(
        title = "Krishna Speaks to You",
        subtitle = "A gentle message for this moment",
        background = R.drawable.bg_07_gita_wisdom,
        onBack = onBack,
        onNavigate = onNavigate
    ) {
        item {
            Crossfade(targetState = message, animationSpec = tween(500), label = "nextKrishnaMessage") { current ->
                GlassCard(Modifier.fillMaxWidth().animateContentSize()) {
                    Column(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.illustration_02_krishna_portrait),
                            contentDescription = "Krishna",
                            modifier = Modifier.fillMaxWidth().height(230.dp),
                            contentScale = ContentScale.Fit
                        )
                        Text(current.situation, color = AntiqueGold, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
                        GoldDivider(Modifier.padding(horizontal = 54.dp))
                        Text("My dear $displayName,", color = LightGold, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "“${current.text}”",
                            color = SoftWhite,
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        item {
            Text(
                "Message ${safeIndex + 1} of ${messages.size} selected for you",
                modifier = Modifier.fillMaxWidth(),
                color = MutedText,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                KrishnaMessageIconButton(
                    icon = R.drawable.icon_previous,
                    label = "Previous message",
                    onClick = { onNext((safeIndex - 1 + messages.size).mod(messages.size)) }
                )
                KrishnaMessageIconButton(
                    icon = R.drawable.icon_bookmark,
                    label = if (bookmarked(message.id)) "Remove saved message" else "Save message",
                    selected = bookmarked(message.id),
                    onClick = { onToggleBookmark(message.id) }
                )
                KrishnaMessageIconButton(
                    icon = R.drawable.icon_share,
                    label = "Share message",
                    onClick = { shareSacredText(context, "A Krishna-inspired message for $displayName", "My dear $displayName,\n\n“${message.text}”\n\nKrishna-inspired reflection · Shared from Krishna For You") }
                )
                KrishnaMessageIconButton(
                    icon = R.drawable.icon_chakra,
                    label = "Show a random message",
                    onClick = {
                        val randomIndex = if (messages.size <= 1) 0 else Random.nextInt(messages.size - 1).let { candidate ->
                            if (candidate >= safeIndex) candidate + 1 else candidate
                        }
                        onNext(randomIndex)
                    }
                )
                KrishnaMessageIconButton(
                    icon = R.drawable.icon_next,
                    label = "Next message",
                    highlighted = true,
                    onClick = { onNext((safeIndex + 1).mod(messages.size)) }
                )
            }
        }
        item {
            Text(
                "Krishna-inspired original reflection · not a literal scripture quotation",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                color = MutedText,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun KrishnaMessageIconButton(
    icon: Int,
    label: String,
    onClick: () -> Unit,
    selected: Boolean = false,
    highlighted: Boolean = false
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.size(52.dp),
        shape = CircleShape,
        color = when {
            highlighted -> AntiqueGold.copy(alpha = .28f)
            selected -> AntiqueGold.copy(alpha = .2f)
            else -> com.indianservers.krishna4u.ui.theme.CosmicMidnight.copy(alpha = .72f)
        },
        border = BorderStroke(if (highlighted || selected) 1.5.dp else 1.dp, if (highlighted || selected) LightGold else AntiqueGold.copy(alpha = .7f))
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SacredIcon(icon, label, Modifier.size(25.dp))
        }
    }
}
