package com.indianservers.krishna4u.feature.emotions

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

data class EmotionalIntelligenceLesson(
    val id: String,
    val title: String,
    val subtitle: String,
    val openingQuestion: String,
    val krishnaConnection: String,
    val emotionalSkill: String,
    val steps: List<String>,
    val kidsExample: String,
    val teensExample: String,
    val adultsExample: String,
    val reflection: String,
    val practice: String,
    @DrawableRes val icon: Int
) {
    fun exampleFor(readingMode: String): String = when (readingMode) {
        "kids" -> kidsExample
        "adults" -> adultsExample
        else -> teensExample
    }
}

val emotionalIntelligenceLessons = listOf(
    EmotionalIntelligenceLesson(
        id = "identify-emotions",
        title = "Name What You Feel",
        subtitle = "Identifying emotions without becoming trapped by them",
        openingQuestion = "Have you ever said “I’m fine” when many feelings were moving inside you?",
        krishnaConnection = "At Kurukshetra, Krishna did not begin by dismissing Arjuna’s distress. Arjuna described his trembling body, confusion, grief and fear. Once the struggle was honestly seen, wise guidance could begin. An emotion is a messenger—not your identity and not always an instruction.",
        emotionalSkill = "Pause and name the emotion precisely: sad, disappointed, worried, jealous, embarrassed, lonely or angry. Then notice where it appears in your body and what need or value may be underneath it.",
        steps = listOf("Pause before explaining or acting", "Name the feeling with one accurate word", "Notice the body signal", "Ask what the feeling may be protecting", "Choose an action that matches your values"),
        kidsExample = "Your friend plays with someone else and you feel angry. Under the anger, you may also feel left out. Naming both feelings helps you ask to join instead of pushing someone.",
        teensExample = "A classmate receives the opportunity you wanted. Naming disappointment and jealousy honestly prevents those feelings from turning into gossip or self-hatred.",
        adultsExample = "Critical feedback may produce embarrassment, fear and defensiveness at once. Naming each response creates room to separate useful information from wounded pride.",
        reflection = "Which emotion is easiest for you to show? Which one do you usually hide beneath anger or silence?",
        practice = "Use this sentence once today: “I notice I feel ___ because ___ matters to me.”",
        icon = R.drawable.icon_mind
    ),
    EmotionalIntelligenceLesson(
        id = "calm-reactions",
        title = "Create Space Before Reaction",
        subtitle = "Calming the body so wisdom can return",
        openingQuestion = "What becomes possible in the few seconds between a feeling and your response?",
        krishnaConnection = "Krishna explains that unchecked attachment can move into anger, confusion and loss of judgment. The teaching is not to suppress emotion but to interrupt the chain before it controls action. A calm body gives discernment a chance to speak.",
        emotionalSkill = "Regulation begins physically. Slow the breath, relax the jaw, lower the voice and delay messages or decisions made at the emotional peak. Calm first; solve second.",
        steps = listOf("Stop speaking or typing for a moment", "Breathe out more slowly than you breathe in", "Relax one tense part of the body", "Move away briefly if the situation is safe", "Return when you can choose words deliberately"),
        kidsExample = "When your game is interrupted, place your hands on your stomach and take five slow breaths before deciding what to say.",
        teensExample = "When an upsetting message arrives, do not reply immediately or post a screenshot. Put the phone down, walk, breathe and reread it when your body is calmer.",
        adultsExample = "During a tense meeting, ask for a short pause rather than responding from humiliation or anger. Regulation protects both the relationship and the decision.",
        reflection = "What is your earliest warning sign—tight shoulders, fast speech, heat, racing thoughts or an urge to send a message?",
        practice = "Practise the 3–3 pause: three slow breaths and three questions—Is it true? Is it necessary? Can I say it kindly?",
        icon = R.drawable.icon_meditation
    ),
    EmotionalIntelligenceLesson(
        id = "express-needs",
        title = "Speak Needs with Clarity",
        subtitle = "Expressing yourself without blame, threat or silence",
        openingQuestion = "Can another person understand your need if you only show anger or withdraw?",
        krishnaConnection = "Arjuna eventually says clearly that he is confused and asks Krishna to guide him. Honest vulnerability opens the door to useful help. Expressing a need is not weakness; it is responsible communication when joined with respect for the other person’s freedom.",
        emotionalSkill = "Describe the observable situation, name your feeling, explain the value or need, and make a specific request. A request allows an answer; a demand uses fear or guilt.",
        steps = listOf("State what happened without exaggeration", "Use “I feel” rather than assigning motives", "Name the need or value", "Make one specific, realistic request", "Listen to the answer and negotiate respectfully"),
        kidsExample = "Instead of shouting “You never share,” say, “I feel left out when I don’t get a turn. Can I have the next turn for five minutes?”",
        teensExample = "Instead of ignoring a friend, say, “I felt hurt when the plan changed without telling me. Next time, please message me before deciding.”",
        adultsExample = "Instead of “Nobody respects my time,” say, “When meetings begin late, I feel frustrated because reliability matters to me. Can we agree on a start-time rule?”",
        reflection = "Do you usually demand, hint, stay silent or communicate directly when you need something?",
        practice = "Use the pattern once: “When ___ happens, I feel ___. I need/value ___. Would you be willing to ___?”",
        icon = R.drawable.icon_relationships
    ),
    EmotionalIntelligenceLesson(
        id = "show-empathy",
        title = "See Through Another Heart",
        subtitle = "Listening for the feeling beneath another person’s behaviour",
        openingQuestion = "Can you understand someone’s pain without agreeing with everything they do?",
        krishnaConnection = "Krishna asks the seeker to see another person’s happiness and suffering as comparable to one’s own. Empathy does not erase truth or boundaries. It helps us respond to the human being before us rather than only to our assumption about them.",
        emotionalSkill = "Listen for facts, feelings and needs. Reflect what you heard before offering advice. Understanding is not the same as agreement, and compassion does not require accepting harmful behaviour.",
        steps = listOf("Give undivided attention", "Ask rather than assume", "Reflect the feeling you heard", "Validate the experience without endorsing harm", "Ask what kind of support would help"),
        kidsExample = "A classmate snaps at you after losing a competition. You can say, “You seem really disappointed. Do you want some space?” while still saying that unkind words are not okay.",
        teensExample = "A friend who keeps cancelling may be overwhelmed rather than uncaring. Ask what is happening, listen, and still explain how repeated cancellations affect trust.",
        adultsExample = "Behind a colleague’s defensiveness may be fear of failure. Acknowledging the pressure can make accountability more constructive, without excusing missed responsibilities.",
        reflection = "When someone shares a problem, do you listen, compare it with your story, immediately advise, or try to end the discomfort?",
        practice = "In one conversation, ask: “Would you like me to listen, help you think, or help you act?”",
        icon = R.drawable.icon_compassion
    ),
    EmotionalIntelligenceLesson(
        id = "resolve-conflict",
        title = "Turn Conflict Toward Dharma",
        subtitle = "Resolving disagreement without defeating the person",
        openingQuestion = "In a conflict, are you trying to solve the problem—or prove that the other person is the problem?",
        krishnaConnection = "Before the Mahabharata war, Krishna carried a proposal for peace and dialogue. Dharma can require firmness, yet it does not rush past honest attempts at understanding and fair resolution. The aim is not false peace; it is a solution that protects dignity, truth and safety.",
        emotionalSkill = "Separate the person from the problem. Agree on facts, let each person describe the impact, identify shared needs and build a specific agreement. When safety, abuse or serious injustice is involved, seek responsible support rather than handling it alone.",
        steps = listOf("Choose a calm time and safe place", "Let each person speak without interruption", "Identify the shared problem and common goal", "Generate more than one fair solution", "Agree on actions, boundaries and a time to review"),
        kidsExample = "Two children want the same item. They can name the problem, choose turns or another shared plan, and ask an adult to help if they cannot stay respectful.",
        teensExample = "Two friends argue over a private screenshot. They need to stop forwarding it, hear the impact, remove what can be removed, apologise specifically and agree on a digital boundary.",
        adultsExample = "Family conflict about responsibilities improves when vague accusations become a written division of tasks, realistic expectations and a date to review the agreement.",
        reflection = "Which matters more to you during conflict: being understood, being right, feeling safe, receiving repair or finding a workable agreement?",
        practice = "Resolve one small tension using: “The problem is ___. It affects us by ___. A fair next step could be ___.”",
        icon = R.drawable.icon_dharma
    )
)

fun emotionalIntelligenceLesson(id: String?): EmotionalIntelligenceLesson =
    emotionalIntelligenceLessons.firstOrNull { it.id == id } ?: emotionalIntelligenceLessons.first()
