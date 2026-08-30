package com.indianservers.krishna4u.feature.evening

import kotlin.random.Random

data class EveningReflectionPrompt(
    val id: String,
    val question: String,
    val gentleCue: String,
    val essential: Boolean = false
)

val essentialEveningReflectionPrompts = listOf(
    EveningReflectionPrompt("did-well", "What did you do well today?", "Notice sincere effort, courage or kindness—not only a perfect result.", true),
    EveningReflectionPrompt("anger", "Where did anger control you?", "Look honestly without attacking yourself. What happened just before the anger?", true),
    EveningReflectionPrompt("duty", "Did you fulfil your duty?", "Consider the responsibilities that truly belonged to you today.", true),
    EveningReflectionPrompt("thank", "Whom should you thank?", "Remember visible help and the quiet care you may have missed.", true),
    EveningReflectionPrompt("apology", "Is an apology needed?", "A sincere apology names the harm and begins a real repair.", true),
    EveningReflectionPrompt("place-with-krishna", "What can you place in Krishna’s hands tonight?", "Keep tomorrow’s duty, but release what cannot be solved by a tired mind.", true)
)

val additionalEveningReflectionPrompts = listOf(
    EveningReflectionPrompt("truth", "Were you truthful today, including with yourself?", "Notice any truth you softened, hid or avoided."),
    EveningReflectionPrompt("gentle-words", "Did your words uplift someone or wound them?", "Remember tone as well as meaning."),
    EveningReflectionPrompt("listened", "Did you truly listen when someone needed you?", "Listening means making room before offering an answer."),
    EveningReflectionPrompt("judged", "Did you judge someone before knowing their full story?", "Curiosity can replace a quick conclusion."),
    EveningReflectionPrompt("help", "Who received help because you were present today?", "Small service counts when it protects dignity."),
    EveningReflectionPrompt("ignored-help", "Did you notice someone who quietly needed help?", "Think of one safe action you can take tomorrow."),
    EveningReflectionPrompt("courage", "Where did you choose courage over comfort?", "Courage may have been a small truthful step."),
    EveningReflectionPrompt("fear", "What did fear stop you from doing?", "Separate real danger from the discomfort of growth."),
    EveningReflectionPrompt("attention", "Where did you give your full attention?", "Presence is a form of respect."),
    EveningReflectionPrompt("wasted-time", "What took your time without giving value back?", "Notice without shame, then choose one boundary."),
    EveningReflectionPrompt("body", "Did you care for the body entrusted to you?", "Consider food, water, movement, medicine and rest."),
    EveningReflectionPrompt("harmful-habit", "Did any habit harm your mind or body today?", "Honesty is the first step toward support and freedom."),
    EveningReflectionPrompt("food", "Did you respect food and avoid waste?", "Every meal carries nature, labour and grace."),
    EveningReflectionPrompt("nature", "How did you care for nature or an animal today?", "One small protective act is still dharma."),
    EveningReflectionPrompt("elders", "Did you show patience and respect toward an elder?", "Respect can be expressed through time, tone and care."),
    EveningReflectionPrompt("younger", "Did your example guide someone younger well?", "People often learn more from conduct than advice."),
    EveningReflectionPrompt("promise", "Did you keep the promises you made?", "If not, repair or renegotiate honestly."),
    EveningReflectionPrompt("responsibility", "Did you take responsibility, or look for someone to blame?", "Ownership gives you the power to correct course."),
    EveningReflectionPrompt("ego", "Where did ego make it hard to learn?", "A correction does not reduce your worth."),
    EveningReflectionPrompt("pride", "Did success make you forget anyone who helped you?", "Achievement becomes sacred when gratitude remains."),
    EveningReflectionPrompt("jealousy", "Where did comparison or jealousy enter your mind?", "Another person’s light does not remove your path."),
    EveningReflectionPrompt("celebrate", "Did you celebrate another person’s good news?", "A generous heart is not threatened by shared joy."),
    EveningReflectionPrompt("contentment", "What was already enough today?", "Contentment notices what desire keeps overlooking."),
    EveningReflectionPrompt("money", "Did you use money honestly and thoughtfully?", "Consider need, waste, fairness and generosity."),
    EveningReflectionPrompt("screen", "Did your screen use serve you, or control you?", "Notice what it displaced: sleep, work, prayer or people."),
    EveningReflectionPrompt("privacy", "Did you respect another person’s privacy and trust?", "Sacred trust includes what you choose not to share."),
    EveningReflectionPrompt("boundaries", "Did you protect a healthy boundary without cruelty?", "A calm no can protect both truth and relationship."),
    EveningReflectionPrompt("consent", "Did you respect another person’s comfort and consent?", "Care never assumes access to another person."),
    EveningReflectionPrompt("conflict", "Did you try to understand during disagreement?", "Winning an argument is not the same as solving a problem."),
    EveningReflectionPrompt("forgive", "What resentment are you still feeding?", "Forgiveness can begin without denying harm or removing safety."),
    EveningReflectionPrompt("repair", "What small repair can you make tomorrow?", "Choose something clear, possible and sincere."),
    EveningReflectionPrompt("patience", "Where did you practise patience?", "Recall the pause in which you chose not to react."),
    EveningReflectionPrompt("self-control", "When did you guide an impulse instead of obeying it?", "That quiet choice is inner strength."),
    EveningReflectionPrompt("focus", "What deserved more focused effort from you?", "Choose the first small step for tomorrow."),
    EveningReflectionPrompt("learning", "What did today teach you?", "Keep the lesson without carrying unnecessary shame."),
    EveningReflectionPrompt("mistake", "Which mistake can become wisdom?", "Name what you will do differently next time."),
    EveningReflectionPrompt("gratitude-small", "What small moment deserves gratitude?", "Acknowledge one light without denying any pain."),
    EveningReflectionPrompt("unexpected-good", "What unexpected good entered your day?", "Receive it without rushing past it."),
    EveningReflectionPrompt("service", "Did you serve without seeking praise?", "Quiet good work does not lose its value."),
    EveningReflectionPrompt("fairness", "Was anyone treated unfairly in your presence?", "Consider whether tomorrow needs your voice or support."),
    EveningReflectionPrompt("innocent", "Did you protect someone vulnerable or innocent?", "Protection can begin with noticing, reporting or standing beside them."),
    EveningReflectionPrompt("dharma-alone", "Did you stand for what was right when it felt lonely?", "Dharma is not decided only by the crowd."),
    EveningReflectionPrompt("motivation", "What was the real motive behind an important action?", "Look beneath praise, fear, duty, love and control."),
    EveningReflectionPrompt("attachment", "Which result did you try too hard to control?", "Return to sincere action and loosen your grip on the outcome."),
    EveningReflectionPrompt("burden", "Which burden was never yours to carry?", "Care for people without taking ownership of every choice they make."),
    EveningReflectionPrompt("ask-help", "Where should you ask for help?", "Receiving wise help is responsibility, not weakness."),
    EveningReflectionPrompt("self-kindness", "Did you speak to yourself with truth and kindness?", "Correction can be firm without becoming cruel."),
    EveningReflectionPrompt("tomorrow-one", "What is the one most important action for tomorrow?", "Let one clear duty be enough for a beginning."),
    EveningReflectionPrompt("release", "What thought does not need to enter your sleep?", "Write it down, then let tonight remain a place of rest."),
    EveningReflectionPrompt("prayer", "Who needs a place in your prayer tonight?", "Let concern become love, not helpless worry.")
)

val allEveningReflectionPrompts: List<EveningReflectionPrompt>
    get() = essentialEveningReflectionPrompts + additionalEveningReflectionPrompts

fun eveningReflectionDeck(seed: Int, additionalCount: Int = 6): List<EveningReflectionPrompt> =
    essentialEveningReflectionPrompts + additionalEveningReflectionPrompts.shuffled(Random(seed)).take(additionalCount)
