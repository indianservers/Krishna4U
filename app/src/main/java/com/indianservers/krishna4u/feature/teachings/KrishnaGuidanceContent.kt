package com.indianservers.krishna4u.feature.teachings

import com.indianservers.krishna4u.R

private data class GuidanceSeed(
    val id: String,
    val title: String,
    val message: String,
    val action: String
)

private fun guidance(id: String, title: String, message: String, action: String) =
    GuidanceSeed(id, title, message, action)

private val guidanceSeeds = listOf(
    guidance("care-for-parents-elders-animals", "Care for those entrusted to you", "Take care of your elderly parents, elders, and animals—they are my gifts placed in your care.", "Offer patient, practical care to an elder, parent or animal today."),
    guidance("respect-every-soul", "Respect every soul", "Respect every soul you meet—for I reside in each of them.", "Give your full attention and respect to someone you might usually overlook."),
    guidance("stand-for-dharma", "Stand for Dharma", "Stand for Dharma, even if you must stand alone.", "Choose what is right in one situation, even when it is not the popular choice."),
    guidance("protect-the-innocent", "Protect the innocent", "Protect the innocent. Even your smallest act of courage can change someone’s fate.", "Safely speak up, seek help or stand beside someone vulnerable."),
    guidance("serve-with-humility", "Serve with humility", "Serve with humility, not ego. True greatness hides in selfless actions.", "Complete one useful task quietly, without seeking credit."),
    guidance("fulfil-your-duties", "Fulfil your duties", "Fulfill your duties—not for reward, but because it's the right thing to do.", "Finish one responsibility because it is right, not because someone is watching."),
    guidance("listen-as-a-friend", "Listen as a friend", "Be a friend who listens, not just speaks. Your presence can be someone’s healing.", "Ask a friend how they are and listen without interrupting or fixing."),
    guidance("forgive-for-peace", "Forgive for your peace", "Forgive, not because they deserve it, but because your heart deserves peace.", "Release one repeated angry thought while keeping any necessary boundary."),
    guidance("feed-and-guide", "Let your hands carry blessings", "Feed the hungry, help the lost—my blessings flow through such hands.", "Share food or offer clear, safe help to someone who needs direction."),
    guidance("honour-women", "Honour women", "Treat women with utmost honor—they are strength, creation, and divinity.", "Challenge one disrespectful word or habit and practise equal respect."),
    guidance("actions-over-recognition", "Let actions speak", "Do not chase recognition. Let your actions speak your name.", "Do excellent work today without announcing it."),
    guidance("care-for-nature", "Care for nature", "Care for nature. Every tree, river, and breeze carries my breath.", "Protect one small part of nature by reducing waste or caring for a plant."),
    guidance("learn-to-serve", "Learn so you can serve", "Educate yourself and others—not just to succeed, but to serve better.", "Learn one useful idea and explain it kindly to someone else."),
    guidance("meditate-in-silence", "Meet Me in silence", "Meditate. Even a few moments with Me in silence can calm storms inside you.", "Sit quietly with your breath for three undisturbed minutes."),
    guidance("practice-gratitude", "Invite grace through gratitude", "Be grateful. Gratitude invites more grace into your life.", "Write down three specific gifts from today."),
    guidance("guide-the-younger", "Guide through example", "Guide the younger ones with patience—teach not just by words, but by how you live.", "Model one value patiently instead of merely instructing it."),
    guidance("control-anger", "Do not let anger burn wisdom", "Control anger—it is the fire that burns your wisdom.", "Delay your response until your breathing and voice are steady."),
    guidance("honest-wealth", "Earn honestly and share", "Earn your wealth honestly and share it generously.", "Review one financial choice for honesty, fairness and generosity."),
    guidance("guard-your-thoughts", "Be mindful of thought", "Be mindful of your thoughts—they create the world you live in.", "Notice one harmful thought and replace it with a truthful, constructive one."),
    guidance("accept-change", "Accept change", "Accept change. Nothing in this world is permanent except Me.", "Name one change you cannot control and choose your next helpful response."),
    guidance("do-not-judge-quickly", "Look beyond first judgment", "Don't judge too quickly. Every soul is on a journey you don’t fully see.", "Ask one curious question before forming an opinion about someone."),
    guidance("be-loyal", "Keep trust sacred", "Be loyal in your relationships—trust is sacred.", "Keep a confidence, show up reliably or repair a broken promise."),
    guidance("pause-before-reacting", "Find Me in the pause", "Pause before you react. In that pause, you’ll hear Me.", "Take three slow breaths before answering the next difficult moment."),
    guidance("truth-with-gentleness", "Speak truth gently", "Speak the truth, but speak it gently. Let your words uplift, not wound.", "Say one necessary truth with respect and without humiliation."),
    guidance("protect-from-pride", "Protect the mind from pride", "Protect your mind from pride—it builds walls between you and Me.", "Acknowledge someone else’s contribution and one thing you still need to learn."),
    guidance("offer-actions", "Make action sacred", "Offer whatever you do, eat, or give… to Me. Then it becomes sacred.", "Begin one ordinary action with a quiet intention to offer it."),
    guidance("trust-divine-timing", "Be patient with timing", "Be patient. My timing is perfect, even when it feels delayed.", "Use a waiting period to prepare, learn or serve instead of only worrying."),
    guidance("voice-against-injustice", "Let devotion find its voice", "Stand up when injustice happens, even if your voice shakes. That is true devotion.", "Safely object, document the problem or seek responsible help."),
    guidance("work-wholeheartedly", "Let sincerity become worship", "Do your work with full heart, not half-mind. Sincerity is worship.", "Give one task twenty focused minutes without switching or scrolling."),
    guidance("do-not-compare-paths", "Walk your unique path", "Don’t compare your path with others. I’ve written a unique purpose for you.", "Replace one comparison with a concrete step toward your own purpose."),
    guidance("celebrate-others", "Celebrate another’s success", "Celebrate others’ success—there’s no scarcity in My blessings.", "Congratulate someone sincerely without mentioning your own achievement."),
    guidance("keep-promises", "Keep your word", "Keep your promises. Your word is a reflection of your soul.", "Complete one promise today or honestly renegotiate it before it is broken."),
    guidance("invest-in-virtues", "Invest in what lasts", "Don’t cling to what’s temporary—invest in virtues that last.", "Practise patience, courage or generosity in one visible action."),
    guidance("respect-time", "Respect time", "Respect time. It is the most silent teacher I have sent.", "Choose the most important task and begin it at the time you planned."),
    guidance("release-corrected-guilt", "Release guilt after repair", "Let go of guilt once you've corrected your mistake. I do not hold it against you—why should you?", "Make a sincere repair, learn the lesson and stop rehearsing self-punishment."),
    guidance("pray-for-the-world", "Let prayer overflow", "Offer prayers not only for yourself, but for the world. Love is meant to overflow.", "Include another person, community and the natural world in today’s prayer."),
    guidance("silence-before-harm", "Choose healing silence", "When tempted to harm with words, pause. Silence can heal more than anger ever could.", "Postpone one hurtful reply until you can speak constructively."),
    guidance("discipline-with-purpose", "Discipline guides power", "Be disciplined—it’s not restriction, it’s power guided with purpose.", "Keep one small promise to your routine today."),
    guidance("be-honest-with-yourself", "Begin with self-honesty", "Do not lie to yourself. Self-honesty is the beginning of transformation.", "Write one truth you have avoided and one safe next step."),
    guidance("care-for-health", "Care for the body", "Take care of your health—your body is a temple gifted by Me.", "Choose one nourishing meal, healthy movement or needed period of rest."),
    guidance("peace-with-the-past", "Make peace with the past", "Make peace with your past. It helped shape the wisdom you carry now.", "Name one lesson from the past without using it to condemn your present self."),
    guidance("listen-to-elders", "Listen across generations", "Listen to elders, not because they are always right, but because they’ve walked paths you haven’t.", "Ask an elder about one experience and listen with discernment and respect."),
    guidance("stillness-in-confusion", "Sit still when confused", "In moments of confusion, sit still. I whisper clearest to the calm mind.", "Pause decisions for five quiet minutes and separate facts from fear."),
    guidance("do-not-waste-food", "Honour every grain", "Do not waste food. Every grain holds the labor of many and My grace.", "Take only what you can eat and store or share safe leftovers."),
    guidance("offer-your-talents", "Use talent to elevate", "Offer your talents to serve the world—not just to earn, but to elevate.", "Use one skill to help a person or cause without charging or seeking praise."),
    guidance("release-jealousy", "Let your own light breathe", "Let go of jealousy—it is a shadow that blocks your own light.", "Turn envy into one sincere compliment and one step in your own growth."),
    guidance("walk-with-me", "Create ripples of light", "Walk with Me, and even your smallest steps will create ripples of light in the world.", "Choose one small good action and do it consistently."),
    guidance("love-without-expectation", "Love without keeping score", "Above all, love with all your heart, expecting nothing. That love is closest to divine.", "Offer care freely while maintaining healthy, respectful boundaries.")
)

internal val krishnaGuidanceTeachings = guidanceSeeds.map { seed ->
    TeachingUi(
        id = "guidance-${seed.id}",
        title = seed.title,
        summary = seed.message,
        category = "Krishna’s Guidance",
        source = "Krishna Speaks to You",
        question = "Where can you live this teaching today?",
        teaching = seed.message,
        takeaways = listOf(
            seed.action,
            "Begin with one sincere step rather than waiting for a perfect moment.",
            "Let the action come from love and Dharma, not recognition or fear.",
            "Reflect tonight on how this choice affected you and others."
        ),
        practice = seed.action,
        icon = R.drawable.icon_lotus
    )
}
