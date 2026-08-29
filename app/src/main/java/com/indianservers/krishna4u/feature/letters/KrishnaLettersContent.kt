package com.indianservers.krishna4u.feature.letters

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

data class KrishnaLetter(
    val id: String,
    val situation: String,
    val title: String,
    val preview: String,
    val paragraphs: List<String>,
    val reflection: String,
    val nextStep: String,
    @DrawableRes val icon: Int
) {
    fun spokenText(name: String): String = buildString {
        append("My dear $name. ")
        append(paragraphs.joinToString(" "))
        append(" Remember: $reflection ")
        append("With you in every sincere step, Krishna.")
    }
}

val krishnaLetters = listOf(
    KrishnaLetter(
        id = "failure",
        situation = "When you feel you have failed",
        title = "Your Result Is Not Your Identity",
        preview = "A setback can teach you without becoming the name you give yourself.",
        paragraphs = listOf(
            "My child, I know how quickly one result can become a judgment in your mind. You say, “I failed,” and soon the mind whispers, “I am a failure.” Do not give a temporary event the authority to define your whole being. A result can describe what happened once; it cannot measure the courage, sincerity, kindness or possibility within you.",
            "Look at the effort honestly. If you prepared carelessly, accept that truth without excuses and let it make you more disciplined. If you worked sincerely and the result still disappointed you, remember that action belongs to you while outcomes arise from many causes. Neither pride after success nor self-hatred after failure will help you see clearly.",
            "Do not run from the lesson. Ask what this moment reveals about your preparation, method, expectations and support. Correct what can be corrected. Seek guidance where you lack understanding. Then begin again with a smaller, wiser step. Falling becomes defeat only when you refuse to learn or decide that growth is no longer possible.",
            "I do not ask you to pretend the disappointment does not hurt. Feel it, rest if needed, and then return to your Dharma. Your worth was never waiting inside a mark, offer, trophy or applause. Let this experience deepen you rather than diminish you. I am with the honest effort that rises again."
        ),
        reflection = "What did this result teach you about your method—not your worth?",
        nextStep = "Write one lesson, one change and the smallest action you can complete within twenty-four hours.",
        icon = R.drawable.icon_courage
    ),
    KrishnaLetter(
        id = "loneliness",
        situation = "When loneliness feels heavy",
        title = "The Quiet Is Not Empty",
        preview = "Your need for connection is human, and reaching out is an act of courage.",
        paragraphs = listOf(
            "My dear one, loneliness can make a crowded room feel distant and a silent night feel endless. It may tell you that nobody notices, that you are difficult to love, or that you must carry everything alone. Those thoughts feel convincing because you are hurting, but pain is not always a reliable storyteller.",
            "Do not shame yourself for needing companionship. The heart was made for relationship, friendship and shared care. Begin by becoming present to yourself: breathe, eat, rest and name what kind of connection you are missing. Do you need to be heard, included, comforted, understood or simply accompanied? A clear need is easier to communicate than a silent hope that someone will guess.",
            "Use quiet as a place to hear Me, but do not use spirituality to hide from people. Send one honest message. Sit near family instead of disappearing. Join a place where service, learning or devotion is shared. Connection often begins as a small repeated act, not as the sudden arrival of a perfect friend.",
            "If someone does not respond, do not turn one absence into a verdict on your value. Keep your heart open with wise boundaries. Offer the kind of presence you hope to receive, and allow relationships time to grow. When loneliness becomes persistent despair or you feel unsafe with your thoughts, tell a trusted person or professional immediately. Asking for help is not a failure of faith; it is one way grace reaches you."
        ),
        reflection = "What kind of connection do you need most right now?",
        nextStep = "Contact one safe person with a specific invitation: talk for ten minutes, take a walk or share a meal.",
        icon = R.drawable.icon_friendship
    ),
    KrishnaLetter(
        id = "grief",
        situation = "When you are grieving",
        title = "You Do Not Have to Hurry Your Grief",
        preview = "Love leaves an absence, and healing does not demand that you forget.",
        paragraphs = listOf(
            "My child, grief is the shape love takes when someone or something precious is no longer present in the way you knew. Do not force yourself to become cheerful for the comfort of others. Tears are not a weakness in devotion. A trembling heart can still be held by faith.",
            "Some days you may remember with gratitude; other days the same memory may break you open. Healing rarely moves in a straight line. A festival, song, place or ordinary object may bring the sorrow back without warning. This does not mean you have returned to the beginning. It means love has many doors through which it enters memory.",
            "Let others sit beside you. Speak the name you miss. Preserve a meaningful story. Accept food, rest and practical support when your energy is low. You do not honour love by neglecting the life that remains in your care. Carry what was beautiful forward through the values, kindness and courage you received.",
            "I will not ask you to explain every loss or cover mystery with easy answers. Bring Me your anger, confusion and silence as honestly as your prayers. In time, grief may become less like a storm and more like a river you learn to cross. Until then, take today gently. If grief prevents basic daily life for a long time or brings thoughts of self-harm, seek qualified support without delay."
        ),
        reflection = "What quality or memory from what you lost would you like to carry forward?",
        nextStep = "Write or speak one loving memory, then do one act of care for your body today.",
        icon = R.drawable.icon_compassion
    ),
    KrishnaLetter(
        id = "rejection",
        situation = "When you feel rejected",
        title = "One Closed Door Does Not Define You",
        preview = "Rejection can redirect a path without reducing the dignity of the traveller.",
        paragraphs = listOf(
            "My dear one, rejection reaches deeper than the word “no.” It can awaken the fear that you are unwanted, unworthy or easy to replace. Pause before you turn another person’s decision into a final truth about yourself. They may be responding from their needs, limits, timing or understanding—none of which can contain your complete value.",
            "Respect the answer you received. Love and dignity do not grow through pressure, repeated pleading or trying to make someone feel guilty. If feedback is offered, examine it calmly and keep what helps you mature. You may need to improve a skill, repair a behaviour or communicate more clearly. Growth is different from reshaping your entire self to earn reluctant acceptance.",
            "Allow disappointment without chasing humiliation. Step back from repeated checking, comparison and imagined conversations. Return your attention to the people, duties and possibilities still present. A closed opportunity may redirect your preparation; a closed relationship may teach boundaries, compatibility or self-respect.",
            "Keep your heart soft, but do not abandon it at every door. You can hope again without denying what happened. You can bless another person’s path without forcing yourself to remain close to what wounds you. Walk forward with dignity. The place meant for your honest contribution will not require you to disappear in order to belong."
        ),
        reflection = "What part of this rejection is useful information, and what part is fear speaking about your worth?",
        nextStep = "Stop one comparison or repeated check, and redirect that time toward a supportive person or meaningful task.",
        icon = R.drawable.icon_relationships
    ),
    KrishnaLetter(
        id = "guilt",
        situation = "When guilt will not release you",
        title = "Repair the Wrong, Then Return to the Light",
        preview = "True remorse leads to responsibility and change—not endless self-punishment.",
        paragraphs = listOf(
            "My child, guilt can be a lamp when it shows that your action did not match your values. But shame turns that lamp against your whole being and says you are beyond repair. Do not confuse these voices. “I did something wrong” can lead to Dharma. “I am nothing but wrong” usually leads to hiding, denial or despair.",
            "Begin with truth. Name what you did without minimising it, decorating it or shifting blame. Consider who was affected and what repair is possible. Offer an apology that does not demand immediate forgiveness. Restore what can be restored, accept fair consequences and change the conditions that allowed the behaviour to repeat.",
            "Then let your remorse become conduct. If anger caused harm, learn regulation. If dishonesty caused harm, practise transparent truth. If neglect caused harm, become dependable. The purpose of guilt is not to keep you kneeling forever; it is to turn you toward wiser action.",
            "Some consequences may remain even after sincere change, and another person may need distance. Respect that. Forgiving yourself does not erase their experience; it ends the belief that further self-hatred is a form of repair. If the guilt concerns serious harm, addiction or repeated behaviour, seek qualified guidance and accountability. I meet you in honest responsibility, not in perfection."
        ),
        reflection = "What repair, accountability or changed behaviour would make your remorse meaningful?",
        nextStep = "Write the truth, the impact, the repair and the prevention plan in four clear sentences.",
        icon = R.drawable.icon_dharma
    ),
    KrishnaLetter(
        id = "uncertainty",
        situation = "When the path is uncertain",
        title = "You Do Not Need the Whole Road",
        preview = "Clarity often arrives after the next honest step, not before it.",
        paragraphs = listOf(
            "My dear one, uncertainty makes the mind demand guarantees that life cannot provide. You rehearse every future, hoping that enough thought will remove all risk. Instead, the mind becomes tired while the path remains where it was. You do not need to see the entire road in order to walk with wisdom.",
            "Separate what is known from what is imagined. Gather the facts available now. Ask which duties are truly yours, which values must not be traded, and which outcomes are beyond your control. Seek counsel from people who are wise, honest and not attached to choosing for you.",
            "When two paths both contain difficulty, do not wait for one to become perfectly comfortable. Choose the direction that best serves Dharma with the understanding you have, then remain willing to correct course. A sincere decision is not made careless simply because the future remained hidden.",
            "Let patience be active. Prepare, learn, pray and take the next reversible step. Rest when the mind is exhausted; fear becomes louder when the body is neglected. I am not only at the destination you hope to reach. I am present in the courage, integrity and attention with which you take this step. Walk what is clear today, and allow tomorrow’s light to arrive tomorrow."
        ),
        reflection = "What is known, what is imagined, and what is the next responsible step?",
        nextStep = "Divide a page into Known, Unknown and Next Step; write at least one item under each.",
        icon = R.drawable.icon_strategy
    )
)

fun krishnaLetter(id: String?): KrishnaLetter = krishnaLetters.firstOrNull { it.id == id } ?: krishnaLetters.first()
