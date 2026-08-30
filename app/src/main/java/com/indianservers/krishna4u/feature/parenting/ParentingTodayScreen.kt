package com.indianservers.krishna4u.feature.parenting

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.core.design.FeatureScaffold
import com.indianservers.krishna4u.core.design.GlassCard
import com.indianservers.krishna4u.core.design.PrimaryGoldButton
import com.indianservers.krishna4u.core.design.SacredHero
import com.indianservers.krishna4u.core.design.SacredListCard

data class ParentingTodayIssue(
    val id: String,
    val title: String,
    val subtitle: String,
    val parentFirst: String,
    val sayThis: String,
    val avoidThis: String,
    val firstStep: String,
    val recommendedSessionId: String,
    @param:DrawableRes val icon: Int
)

val parentingTodayIssues = listOf(
    ParentingTodayIssue(
        "child-lied", "My child lied", "Restore truth without turning one lie into their identity.",
        "Calm your face and voice before asking questions. Fear makes another lie more likely; safety makes honest correction possible.",
        "I want to understand what made telling the truth feel difficult. You can tell me what happened, and we will solve it together.",
        "Do not say, ‘You are a liar,’ threaten a punishment before hearing the truth or demand a confession while shouting.",
        "Separate the original mistake from the lie. Thank honesty, agree on a fair repair and keep any consequence connected to what happened.",
        "honesty-broken-item", R.drawable.icon_teachings
    ),
    ParentingTodayIssue(
        "not-studying", "They are not studying", "Find the barrier before calling it laziness.",
        "Pause the lecture. Check whether the real problem is confusion, fear of failure, tiredness, distraction, missing skills or a task that feels too large.",
        "What feels hardest about beginning this work? Let us choose one small part and make a plan you can actually follow.",
        "Avoid comparisons, predicting a ruined future, taking over every task or using love and approval as rewards for marks.",
        "Choose one twenty-minute study block, remove one distraction and ask the teenager what support—not control—would help.",
        "teen-marks-comparison", R.drawable.icon_gita
    ),
    ParentingTodayIssue(
        "disrespect", "They spoke disrespectfully", "Hold the boundary without returning the wound.",
        "Lower your own voice. Decide whether the conversation can continue safely now or needs a short pause. Respect cannot be taught through humiliation.",
        "I want to hear what upset you, but I will not continue while we insult each other. Let us pause and try those words again.",
        "Do not mock their tone, bring up old mistakes, demand instant obedience in public or use harsher disrespect to prove authority.",
        "After both people are calm, identify the concern beneath the words, require a respectful repair and model the same standard yourself.",
        "teen-respectful-disagreement", R.drawable.icon_relationships
    ),
    ParentingTodayIssue(
        "siblings-fighting", "Siblings are fighting", "Protect safety, hear both children and repair the relationship.",
        "Separate them if anyone may be hurt. Calm bodies before deciding who started it. Each child needs a chance to speak without interruption.",
        "I will listen to each of you. First we make everyone safe; then we understand what happened and decide how to repair it.",
        "Avoid permanent labels such as ‘the difficult one,’ forcing an immediate apology or automatically making the older child responsible.",
        "Let each child name the fact, feeling and need. Agree on repair, restore any damaged item and create one clear rule for the next conflict.",
        "responsibility-govardhan", R.drawable.icon_compassion
    ),
    ParentingTodayIssue(
        "screen-addiction", "Screen use is taking over", "Restore balance without making technology the enemy.",
        "Look at sleep, school, movement, mood and connection before using the word addiction. Adults should examine their own phone habits too.",
        "I can see that stopping is difficult. Let us protect your sleep and duties while keeping reasonable time for friends and enjoyment.",
        "Do not suddenly destroy the device, shame online friendships, create rules adults ignore or remove essential access without a safety plan.",
        "Agree on one phone-free time and one overnight charging place. Review the effect after seven days using facts rather than blame.",
        "teen-late-phone", R.drawable.icon_mind
    ),
    ParentingTodayIssue(
        "bullying", "Bullying has happened", "Find out whether the child was harmed, caused harm or witnessed it.",
        "Listen without blame and check immediate safety. Save relevant evidence without reposting it. Do not send a child to confront danger alone.",
        "Thank you for telling me. This is not something you must handle alone. I will listen first, and we will choose the safest next step together.",
        "Avoid asking why they did not fight back, promising secrecy you cannot keep, arranging angry confrontation or minimising repeated humiliation as teasing.",
        "Write what happened, when, where and who was involved. Contact the responsible school or safeguarding adult and make a follow-up safety plan.",
        "compassion-draupadi", R.drawable.icon_courage
    ),
    ParentingTodayIssue(
        "exam-fear", "They are afraid of exams", "Calm the frightened mind before improving the study plan.",
        "Treat the fear as real without agreeing that disaster is certain. Check sleep, physical symptoms, preparation gaps and whether expectations have become unbearable.",
        "This exam matters, but it cannot measure your whole worth. Let us find the next part you can prepare and the support you need.",
        "Do not compare marks, call fear weakness, repeat the cost of failure or keep increasing study time when exhaustion is the main problem.",
        "Make a short plan with study blocks, breaks, sleep and one practice paper. Seek school or professional support if anxiety seriously disrupts daily life.",
        "teen-marks-comparison", R.drawable.icon_courage
    ),
    ParentingTodayIssue(
        "refuses-responsibility", "They refuse responsibility", "Make the duty clear, fair and possible to complete.",
        "Check that the task is age-appropriate, understood and shared fairly. Refusal may come from confusion, overload, resentment or lack of practice.",
        "This duty helps our family. Would you like to do it now or at the agreed time? Tell me what part needs help.",
        "Avoid vague commands, repeated reminders from across the room, doing the task while complaining or using unrelated punishment.",
        "Define what completion looks like, offer a limited choice and allow a safe natural consequence. Notice reliability when it happens.",
        "responsibility-cows", R.drawable.icon_karma
    ),
    ParentingTodayIssue(
        "jealous", "They feel jealous", "Treat jealousy as information, not bad character.",
        "Help the child name what they fear losing or wish they had. Jealousy often hides hurt, comparison, insecurity or a need for connection.",
        "It is okay to tell me that this hurts. What do you wish were different, and what can we do without taking away someone else’s happiness?",        "Do not shame the feeling, compare again, force false praise or give unfair rewards merely to stop discomfort.",
        "Offer individual attention, identify one personal goal and practise celebrating another person without using their success against the child.",
        "compassion-fruit-vendor", R.drawable.icon_love
    ),
    ParentingTodayIssue(
        "serious-mistake", "They made a serious mistake", "Protect safety, seek truth and build a real repair.",
        "Regulate your first reaction and establish whether anyone is in danger. A serious mistake needs accountability, but panic and humiliation can hide essential facts.",
        "Tell me the whole truth, including the part you are afraid to say. Safety comes first. Then we will face the consequence and repair what we can.",
        "Do not promise there will be no consequence, interrogate for hours, expose the child publicly or focus on family reputation before another person’s safety.",
        "Record the facts, stop ongoing harm, involve the appropriate responsible adult or professional and create a specific repair and prevention plan.",
        "honesty-broken-item", R.drawable.icon_life_journey
    )
)

@Composable
fun ParentingTodayScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    var selectedIssueId by remember { mutableStateOf<String?>(null) }
    FeatureScaffold(
        "What happened today?",
        "Calm first · Understand · Guide · Repair",
        R.drawable.bg_05_moonlit_sacred_river,
        onBack,
        onNavigate
    ) {
        item {
            SacredHero(
                R.drawable.illustration_02_krishna_portrait,
                "Begin with connection, then correction",
                "Choose what happened. You will receive words for this moment and a complete Krishna-inspired family session."
            )
        }
        items(parentingTodayIssues.size) { index ->
            val issue = parentingTodayIssues[index]
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SacredListCard(issue.title, issue.subtitle, issue.icon, { selectedIssueId = if (selectedIssueId == issue.id) null else issue.id })
                if (selectedIssueId == issue.id) {
                    GlassCard(Modifier.fillMaxWidth().padding(horizontal = 2.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            SacredListCard("Before you respond", issue.parentFirst, R.drawable.icon_inner_peace)
                            SacredListCard("Say this", "“${issue.sayThis}”", R.drawable.icon_compassion)
                            SacredListCard("Avoid this", issue.avoidThis, R.drawable.icon_previous)
                            SacredListCard("First right step", issue.firstStep, R.drawable.icon_check)
                            PrimaryGoldButton(
                                "Open the recommended family session",
                                { onNavigate("parenting_values/${issue.recommendedSessionId}") },
                                Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
        item {
            SacredListCard(
                "When safety is involved",
                "Abuse, self-harm, violence, dangerous substance use or immediate risk requires help from a trusted safeguarding adult, emergency service or qualified professional. Spiritual reflection should not delay protection.",
                R.drawable.icon_courage
            )
        }
    }
}
