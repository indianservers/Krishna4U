package com.indianservers.krishna4u.feature.commitments

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

data class KrishnaCommitment(
    val id: String,
    val title: String,
    val promise: String,
    @param:DrawableRes val icon: Int,
    val practicePrompt: String? = null
)

val krishnaCommitments = listOf(
    KrishnaCommitment("dharma", "Follow Dharma", "I will follow dharma and do what is right, even when it is difficult or nobody is watching.", R.drawable.icon_dharma),
    KrishnaCommitment("nature", "Protect Nature and Life", "I will protect nature, care for animals and use food, water and other resources without waste.", R.drawable.icon_compassion),
    KrishnaCommitment("guides", "Honour Those Who Guide Me", "I will respect my parents, grandparents, teachers and elders, and remember those who help me grow.", R.drawable.icon_relationships),
    KrishnaCommitment("respect", "Respect Every Person", "I will treat women, girls and every human being with dignity, equality and respect.", R.drawable.icon_love),
    KrishnaCommitment("truth", "Speak Truth with Kindness", "I will speak the truth gently and never spread rumours, false information or another person’s private matters.", R.drawable.icon_teachings),
    KrishnaCommitment("protect", "Protect Others from Harm", "I will never bully, insult or humiliate anyone, and I will courageously protect those facing harm or injustice.", R.drawable.icon_courage),
    KrishnaCommitment("duty", "Fulfil My Duty Honestly", "I will perform my duties, studies and work with focus, discipline and sincerity—without cheating or excuses.", R.drawable.icon_karma),
    KrishnaCommitment("anger", "Master Anger and Speech", "I will control my anger, pause before reacting and use words that heal instead of hurt.", R.drawable.icon_mind),
    KrishnaCommitment("friendship", "Keep Friendship Sacred", "I will be a loyal friend who listens, supports others, keeps trust sacred and communicates honestly.", R.drawable.icon_friendship),
    KrishnaCommitment("mistakes", "Learn, Correct and Begin Again", "I will admit my mistakes, correct them, forgive myself and use every failure as a lesson.", R.drawable.icon_life_journey),
    KrishnaCommitment("forgive", "Forgive with Wisdom", "I will forgive others for my own peace while maintaining wise boundaries against repeated harm.", R.drawable.icon_inner_peace),
    KrishnaCommitment("serve", "Serve Those in Need", "I will help the hungry, lonely, elderly, younger children and anyone genuinely in need.", R.drawable.icon_compassion),
    KrishnaCommitment("health", "Protect My Mind and Body", "I will care for my body and mind, and stay away from cigarettes, tobacco, alcohol, drugs and every habit that harms my health, judgment or self-control.", R.drawable.icon_meditation),
    KrishnaCommitment("resources", "Use Wealth and Technology Wisely", "I will use phones, social media, money and technology honestly, responsibly and for helpful purposes.", R.drawable.icon_strategy),
    KrishnaCommitment("promises", "Keep My Word", "I will keep my promises and speak honestly when circumstances prevent me from fulfilling them.", R.drawable.icon_check),
    KrishnaCommitment("own-path", "Walk My Own Path", "I will not compare my journey with others; I will celebrate their success, overcome jealousy and remain grateful for my path.", R.drawable.icon_purpose),
    KrishnaCommitment("character", "Stay Humble, Brave and Generous", "I will earn honestly, share generously, remain humble in success and courageous during failure.", R.drawable.icon_leadership),
    KrishnaCommitment(
        "offering",
        "Offer My Life to Krishna",
        "I will offer my thoughts, words, talents and actions to Krishna and try to leave every person and place better than I found them.",
        R.drawable.icon_lotus,
        practicePrompt = "Choose one ordinary action—study, work, service or care. Before you begin, quietly offer it to Krishna. Do it wholeheartedly, then release the need for praise or reward."
    )
)
