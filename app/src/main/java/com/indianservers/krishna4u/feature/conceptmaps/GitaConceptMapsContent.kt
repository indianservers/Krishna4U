package com.indianservers.krishna4u.feature.conceptmaps

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

data class ConceptStage(
    val title: String,
    val explanation: String,
    val signInLife: String,
    @param:DrawableRes val icon: Int
)

data class ConceptVerse(val reference: String, val teaching: String, val route: String)

data class GitaConceptMap(
    val id: String,
    val title: String,
    val subtitle: String,
    val stages: List<ConceptStage>,
    val turningPoint: String,
    val practice: String,
    val verses: List<ConceptVerse>,
    @param:DrawableRes val icon: Int
) {
    val spokenText: String
        get() = "$title. $subtitle. ${stages.joinToString(" ") { "${it.title}. ${it.explanation} In daily life: ${it.signInLife}" }} Turning point. $turningPoint Practice today. $practice"
}

val gitaConceptMaps = listOf(
    GitaConceptMap(
        id = "attachment-confusion",
        title = "Attachment to Confusion",
        subtitle = "See how repeated mental clinging can slowly take away clear judgement.",
        stages = listOf(
            ConceptStage("Attachment", "The mind repeatedly dwells on a person, object, result or image and begins to tie peace to possessing it.", "I keep thinking, ‘I must have this, or I cannot be okay.’", R.drawable.icon_relationships),
            ConceptStage("Desire", "Attachment hardens into demand. A preference becomes something the mind believes must happen in one exact way.", "I stop asking what is right and think only about getting the result.", R.drawable.icon_purpose),
            ConceptStage("Anger", "When desire is blocked, frustration can turn into anger toward people, circumstances or oneself.", "My body becomes hot, my words become sharp and I want to punish or blame.", R.drawable.icon_courage),
            ConceptStage("Confusion", "Unchecked anger clouds memory, values and judgment. The person may act against what they already know is right.", "I say or do something that creates a second problem after the first disappointment.", R.drawable.icon_mind)
        ),
        turningPoint = "Notice the chain before desire becomes a demand. Pause, name what you are attached to and ask whether dharma still guides the next action.",
        practice = "Complete this sentence: ‘I strongly prefer this result, but I will not harm my values or another person to obtain it.’",
        verses = listOf(
            ConceptVerse("2.62", "Dwelling creates attachment and desire", "gita_verse/2/62"),
            ConceptVerse("2.63", "Anger clouds memory and judgement", "gita_verse/2/63")
        ),
        icon = R.drawable.icon_mind
    ),
    GitaConceptMap(
        id = "knowledge-equanimity",
        title = "Knowledge to Equanimity",
        subtitle = "Wisdom becomes complete when it changes action and steadies the mind.",
        stages = listOf(
            ConceptStage("Knowledge", "True knowledge is more than collected information. It helps us see the enduring Self, our responsibility and the difference between ego and wise discernment.", "I pause to understand what is true instead of reacting only to appearance or fear.", R.drawable.icon_teachings),
            ConceptStage("Right Action", "Clear understanding must enter conduct. The person chooses the action aligned with duty, care and truth rather than comfort alone.", "I ask, ‘What is mine to do here?’ and complete it sincerely.", R.drawable.icon_karma),
            ConceptStage("Equanimity", "When action is guided by wisdom rather than possession of the result, success and failure no longer throw the mind in opposite directions.", "I learn from the outcome without allowing it to decide my worth or destroy my balance.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Do not stop at understanding a teaching. Ask what behaviour must change if that understanding is real.",
        practice = "Choose one teaching you already know and express it through one visible action today.",
        verses = listOf(
            ConceptVerse("4.38", "Nothing purifies like true knowledge", "gita_verse/4/38"),
            ConceptVerse("3.19", "Perform necessary action without attachment", "gita_verse/3/19"),
            ConceptVerse("2.48", "Act with balance in success and failure", "gita_verse/2/48")
        ),
        icon = R.drawable.icon_teachings
    ),
    GitaConceptMap(
        id = "duty-freedom",
        title = "Duty to Inner Freedom",
        subtitle = "See how ordinary responsibility becomes sacred when it is offered without possessiveness.",
        stages = listOf(
            ConceptStage("Duty", "Duty begins with the honest work that belongs to our role, ability, promises and present situation. It must still be examined through dharma and safety.", "I stop avoiding the responsibility that is truly mine.", R.drawable.icon_dharma),
            ConceptStage("Offering", "The action is performed as service rather than as a stage for ego. Effort, skill and intention are offered to the Divine and the welfare of others.", "I give full attention even when nobody praises or notices the work.", R.drawable.icon_lotus),
            ConceptStage("Freedom from Results", "We remain responsible for preparation and action, but release the claim that one preferred outcome must obey us.", "I can work wholeheartedly, learn from the result and continue without panic or pride.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Separate what is in your hands—preparation, effort, honesty and repair—from what is not fully in your hands—the final result.",
        practice = "Write two columns titled ‘My action’ and ‘Not fully mine.’ Complete the first column and consciously release the second.",
        verses = listOf(
            ConceptVerse("3.8", "Perform the necessary duty", "gita_verse/3/8"),
            ConceptVerse("3.9", "Let work become an offering", "gita_verse/3/9"),
            ConceptVerse("2.47", "Your choice concerns action, not ownership of results", "gita_verse/2/47")
        ),
        icon = R.drawable.icon_dharma
    ),
    GitaConceptMap(
        id = "devotion-peace",
        title = "Devotion to Peace",
        subtitle = "Devotion matures from loving remembrance into trust, surrender and inner rest.",
        stages = listOf(
            ConceptStage("Devotion", "Devotion keeps the heart and mind turned toward Krishna through remembrance, love, prayer and values expressed in daily life.", "I remember Krishna not only during ritual, but while choosing how to speak and act.", R.drawable.icon_love),
            ConceptStage("Surrender", "Surrender is not passive helplessness. We perform the right action and release the belief that fear and ego must control every outcome.", "I ask for guidance, do what is mine and place the uncontrollable part in Krishna’s hands.", R.drawable.icon_lotus),
            ConceptStage("Peace", "When the heart trusts the Divine while continuing sincere duty, anxiety loses its claim to command every thought.", "I may still face difficulty, but I no longer feel that I must carry it entirely alone.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Let devotion enter the exact place where control is exhausting you. Surrender the outcome, not the responsibility.",
        practice = "Before one difficult duty, say: ‘Krishna, I offer You this action. Guide my effort and hold the result.’ Then begin.",
        verses = listOf(
            ConceptVerse("12.6", "Offer actions and hold Krishna as the highest aim", "gita_verse/12/6"),
            ConceptVerse("18.66", "Take refuge without fear", "gita_verse/18/66"),
            ConceptVerse("5.29", "Know Krishna as the friend of all beings and find peace", "gita_verse/5/29")
        ),
        icon = R.drawable.icon_lotus
    ),
    GitaConceptMap(
        id = "experience-steadiness",
        title = "Experience to Steadiness",
        subtitle = "Pleasure and pain become teachers when we meet their changing nature with patience.",
        stages = listOf(
            ConceptStage("Sense Contact", "The senses meet changing people, events and conditions, producing experiences that feel pleasant or painful.", "A comment, result or discomfort quickly changes my mood.", R.drawable.icon_mind),
            ConceptStage("Pleasure and Pain", "The mind naturally likes one experience and resists another, but neither state remains unchanged.", "I treat today’s feeling as if it will last forever.", R.drawable.icon_relationships),
            ConceptStage("Endurance", "Wise endurance means caring for what can be changed without surrendering judgment to temporary discomfort.", "I breathe, seek help when needed and refuse to make a harmful choice only to escape the moment.", R.drawable.icon_courage),
            ConceptStage("Steadiness", "Repeated endurance builds a mind that can act from values in both comfort and difficulty.", "I remain responsible without being ruled by one passing feeling.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Name the experience as temporary, then ask whether it needs wise action, patient endurance or both.",
        practice = "When one strong feeling appears, say: ‘This is real, and it will change. What is the right action now?’",
        verses = listOf(ConceptVerse("2.14", "Pleasure and pain come and go", "gita_verse/2/14"), ConceptVerse("2.15", "Steadiness prepares one for freedom", "gita_verse/2/15")),
        icon = R.drawable.icon_inner_peace
    ),
    GitaConceptMap(
        id = "mind-mastery",
        title = "Wandering Mind to Self-Mastery",
        subtitle = "The mind becomes steady through patient return, repeated practice and non-attachment.",
        stages = listOf(
            ConceptStage("Wandering Mind", "Attention moves toward memories, worries, screens and desires even after we choose a worthy focus.", "I notice that my body is present but my mind has left the task.", R.drawable.icon_mind),
            ConceptStage("Patient Return", "Instead of insulting the mind, we notice the wandering and gently bring attention back.", "I return to the breath, page, prayer or person in front of me.", R.drawable.icon_lotus),
            ConceptStage("Practice and Detachment", "Practice strengthens return; detachment stops every passing thought from demanding obedience.", "I repeat the routine without needing every session to feel perfect.", R.drawable.icon_meditation),
            ConceptStage("Self-Mastery", "Over time, the mind becomes a trained companion rather than an unchecked ruler.", "I can choose my response even when thoughts and urges are strong.", R.drawable.icon_chakra)
        ),
        turningPoint = "Treat every noticed distraction as the moment practice begins, not as proof that practice failed.",
        practice = "Complete one ten-minute task. Each time attention wanders, mark a dot and return without criticism.",
        verses = listOf(ConceptVerse("6.26", "Bring the wandering mind back", "gita_verse/6/26"), ConceptVerse("6.35", "Practice and detachment steady the mind", "gita_verse/6/35")),
        icon = R.drawable.icon_meditation
    ),
    GitaConceptMap(
        id = "balance-meditation",
        title = "Balance to Meditation",
        subtitle = "A sustainable spiritual life is supported by balance in food, sleep, work and rest.",
        stages = listOf(
            ConceptStage("Moderation", "Neither harsh deprivation nor constant indulgence gives the body and mind a stable foundation.", "I notice whether excess or neglect is weakening my day.", R.drawable.icon_dharma),
            ConceptStage("Ease in Daily Life", "Regular sleep, nourishment, movement, work and recreation reduce avoidable inner turbulence.", "My routine supports attention instead of repeatedly exhausting it.", R.drawable.icon_check),
            ConceptStage("Steady Meditation", "A supported body makes it easier to remain present in reflection and prayer.", "I can sit without fighting the consequences of an extreme routine.", R.drawable.icon_meditation),
            ConceptStage("Freedom from Suffering", "Balance does not remove every pain, but it reduces suffering created by unmanaged habits.", "I face difficulty with more energy, clarity and self-control.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Spiritual discipline should make life clearer and more responsible, not damage health or ignore legitimate needs.",
        practice = "Choose one stabilising change tonight: a regular bedtime, a balanced meal, movement or a screen boundary.",
        verses = listOf(ConceptVerse("6.16", "Extremes obstruct yoga", "gita_verse/6/16"), ConceptVerse("6.17", "Balance supports freedom from suffering", "gita_verse/6/17")),
        icon = R.drawable.icon_check
    ),
    GitaConceptMap(
        id = "faith-peace",
        title = "Faith to Lasting Peace",
        subtitle = "Trust becomes transformative when joined with discipline, learning and lived understanding.",
        stages = listOf(
            ConceptStage("Faith", "Faith opens the heart to the possibility that truth can be known and lived.", "I am willing to learn even before every question is answered.", R.drawable.icon_love),
            ConceptStage("Discipline", "Sincere trust becomes regular study, self-control and practice rather than wishful thinking.", "I keep a small spiritual promise when the mood is ordinary.", R.drawable.icon_chakra),
            ConceptStage("Knowledge", "Practice makes wisdom experiential: we begin to see the mind, duty and Self more clearly.", "A teaching changes how I interpret and respond to life.", R.drawable.icon_teachings),
            ConceptStage("Peace", "Knowledge grounded in disciplined faith reduces restless doubt and brings stable direction.", "I still ask questions, but they no longer make every step impossible.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Faith should invite honest learning and better conduct; it should never be used to silence sincere questions.",
        practice = "Choose one teaching you trust and practise it consistently for seven days while recording what you learn.",
        verses = listOf(ConceptVerse("4.39", "Faith and discipline lead to knowledge and peace", "gita_verse/4/39"), ConceptVerse("4.40", "Unresolved destructive doubt obstructs peace", "gita_verse/4/40")),
        icon = R.drawable.icon_love
    ),
    GitaConceptMap(
        id = "learning-wisdom",
        title = "Humility to Wisdom",
        subtitle = "Deep learning grows through respect, sincere questions, service and reflection.",
        stages = listOf(
            ConceptStage("Humility", "Humility admits that our current view may be incomplete without treating ourselves as worthless.", "I can say, ‘I do not know yet.’", R.drawable.icon_lotus),
            ConceptStage("Sincere Inquiry", "Good questions seek truth rather than using debate only to display superiority.", "I ask for reasons, context and how the teaching should shape conduct.", R.drawable.icon_ask_krishna),
            ConceptStage("Service", "Service makes learning relational and tests whether knowledge can become responsibility.", "I respect the time of a Guru or teacher and use learning for more than personal praise.", R.drawable.icon_compassion),
            ConceptStage("Wisdom", "Guidance becomes wisdom when examined carefully and expressed through ethical action.", "My choices show what I have understood.", R.drawable.icon_teachings)
        ),
        turningPoint = "A true Guru supports understanding and dharma, not blind dependence or the misuse of authority.",
        practice = "Ask one trustworthy teacher a sincere question, listen fully and apply one useful part before seeking more information.",
        verses = listOf(ConceptVerse("4.34", "Learn through humility, inquiry and service", "gita_verse/4/34"), ConceptVerse("4.38", "Knowledge is deeply purifying", "gita_verse/4/38")),
        icon = R.drawable.icon_teachings
    ),
    GitaConceptMap(
        id = "action-purification",
        title = "Selfless Action to Freedom",
        subtitle = "Work offered without ego can purify intention and make understanding clearer.",
        stages = listOf(
            ConceptStage("Selfless Action", "The person performs needed work as service rather than only for praise, possession or personal advantage.", "I do the right task even when attention is unlikely.", R.drawable.icon_karma),
            ConceptStage("Inner Purification", "Acting without constant self-interest weakens greed, resentment and the need to be seen.", "I notice less inner bargaining about what every good act will earn me.", R.drawable.icon_lotus),
            ConceptStage("Clarity", "A less possessive mind can see duty and consequences with greater honesty.", "I can separate what serves the situation from what protects my image.", R.drawable.icon_mind),
            ConceptStage("Freedom", "Action continues, but ego no longer binds identity completely to praise, blame, gain or loss.", "I learn from results without becoming owned by them.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Before acting, ask whom the work serves and whether ego is quietly demanding payment through praise or control.",
        practice = "Complete one useful task anonymously or without announcing it, then notice what the ego still requests.",
        verses = listOf(ConceptVerse("5.10", "Offer action and remain unbound", "gita_verse/5/10"), ConceptVerse("5.11", "Selfless action supports inner purification", "gita_verse/5/11")),
        icon = R.drawable.icon_karma
    ),
    GitaConceptMap(
        id = "equal-vision-compassion",
        title = "Equal Vision to Compassion",
        subtitle = "Seeing the same spiritual dignity in all beings changes how difference is treated.",
        stages = listOf(
            ConceptStage("Spiritual Vision", "Wisdom looks beyond status, appearance and social labels toward the spiritual reality present in every being.", "I remember that outward difference does not decide inner worth.", R.drawable.icon_teachings),
            ConceptStage("Equality", "Equal vision does not erase different needs or roles; it refuses to make dignity depend on power.", "I apply respect consistently, especially toward those who cannot benefit me.", R.drawable.icon_dharma),
            ConceptStage("Compassion", "When another being is not treated as lesser, their pain becomes morally significant.", "I listen, protect and help without turning service into superiority.", R.drawable.icon_compassion),
            ConceptStage("Non-Hatred", "Compassion weakens hatred while still allowing firm boundaries and resistance to wrongdoing.", "I oppose harmful action without denying the humanity of the person involved.", R.drawable.icon_love)
        ),
        turningPoint = "Ask whether you would judge the same action differently if status, wealth, caste, gender or familiarity changed.",
        practice = "Offer equal courtesy and one useful act to someone whose work or presence is often overlooked.",
        verses = listOf(ConceptVerse("5.18", "The wise see with equal vision", "gita_verse/5/18"), ConceptVerse("12.13", "Live without hatred and with compassion", "gita_verse/12/13")),
        icon = R.drawable.icon_compassion
    ),
    GitaConceptMap(
        id = "ego-bondage",
        title = "Ego to Bondage",
        subtitle = "The false claim ‘I alone am the doer’ turns action into pride, fear and attachment.",
        stages = listOf(
            ConceptStage("Ego", "The limited self-image seeks to be the sole centre of credit, control and importance.", "I need the outcome to prove that I am superior or safe.", R.drawable.icon_profile),
            ConceptStage("Doership", "The person ignores body, nature, circumstances, teachers and helpers and claims complete authorship.", "I say ‘I did everything’ and forget the conditions that supported me.", R.drawable.icon_karma),
            ConceptStage("Attachment", "Because identity has merged with action, its result becomes personally possessive.", "Praise inflates me and criticism feels like the destruction of who I am.", R.drawable.icon_relationships),
            ConceptStage("Bondage", "The mind becomes trapped in repeating pride, fear, defensiveness and craving around outcomes.", "I cannot rest, learn or share credit because the result owns me.", R.drawable.icon_lock)
        ),
        turningPoint = "Recognise your real effort while also remembering nature, circumstances, teachers, colleagues and grace.",
        practice = "After one achievement, name your effort, three forms of help and one factor you could not control.",
        verses = listOf(ConceptVerse("3.27", "Nature’s qualities participate in action", "gita_verse/3/27"), ConceptVerse("18.16", "The isolated ego mistakes itself as sole doer", "gita_verse/18/16")),
        icon = R.drawable.icon_profile
    ),
    GitaConceptMap(
        id = "three-gates",
        title = "Impulse to Self-Protection",
        subtitle = "Recognise desire, anger and greed before they become gates to self-destruction.",
        stages = listOf(
            ConceptStage("Unchecked Impulse", "A strong urge appears and demands immediate obedience without considering consequence.", "I feel that I must act now and think later.", R.drawable.icon_mind),
            ConceptStage("Desire, Anger or Greed", "The urge becomes possessive craving, punishing rage or the wish to take beyond what is right.", "Enough no longer feels enough, or another person becomes an obstacle.", R.drawable.icon_courage),
            ConceptStage("Harm", "Judgment narrows and the person may damage health, relationships, trust or future freedom.", "One short act creates a long consequence.", R.drawable.icon_strategy),
            ConceptStage("Self-Restraint", "A pause, boundary and safer action interrupt the chain before the urge becomes conduct.", "I leave, ask for help or delay action until clear judgment returns.", R.drawable.icon_chakra)
        ),
        turningPoint = "Strong desire is not an instruction. Create distance from the trigger and protect the next ten minutes.",
        practice = "Write one personal warning sign and one exit plan for desire, anger or greed before it becomes harmful action.",
        verses = listOf(ConceptVerse("16.21", "Desire, anger and greed are destructive gates", "gita_verse/16/21"), ConceptVerse("16.22", "Freedom from them supports the highest good", "gita_verse/16/22")),
        icon = R.drawable.icon_chakra
    ),
    GitaConceptMap(
        id = "character-choice",
        title = "Inner Faith to Character",
        subtitle = "What the heart repeatedly values influences choices and gradually forms character.",
        stages = listOf(
            ConceptStage("Inner Faith", "Faith here includes the deep assumptions about what is worthy, real and worth pursuing.", "My repeated attention reveals what I truly value.", R.drawable.icon_love),
            ConceptStage("Values", "Inner belief creates priorities such as truth, status, service, pleasure or control.", "I choose what receives my time, loyalty and sacrifice.", R.drawable.icon_dharma),
            ConceptStage("Choices", "Values become visible through small repeated decisions, especially when nobody watches.", "My habits reveal more than my stated ideals.", R.drawable.icon_check),
            ConceptStage("Character", "Repeated choices shape the kind of person who acts more naturally in a certain direction.", "Tomorrow’s response is being trained by today’s practice.", R.drawable.icon_purpose)
        ),
        turningPoint = "Do not judge faith only by words; examine what your calendar, spending and habits repeatedly worship.",
        practice = "Choose one value you claim and make it visible through a small repeated action for seven days.",
        verses = listOf(ConceptVerse("17.3", "A person becomes shaped by deep faith", "gita_verse/17/3"), ConceptVerse("17.15", "Disciplined speech expresses character", "gita_verse/17/15")),
        icon = R.drawable.icon_purpose
    ),
    GitaConceptMap(
        id = "leadership-welfare",
        title = "Example to Collective Welfare",
        subtitle = "Leadership multiplies conduct because people learn what is acceptable by watching power.",
        stages = listOf(
            ConceptStage("Leader’s Conduct", "People with influence reveal priorities through decisions, habits and what they tolerate.", "My private shortcuts can become another person’s public permission.", R.drawable.icon_leadership),
            ConceptStage("Example", "Others often copy visible behaviour more readily than they remember advice.", "The team follows what I reward, not only what I announce.", R.drawable.icon_teachings),
            ConceptStage("Shared Standard", "Repeated examples become the unwritten culture of a family, class, workplace or nation.", "People learn whether truth, service and accountability are truly safe here.", R.drawable.icon_relationships),
            ConceptStage("Collective Welfare", "Wise leaders work diligently so that shared systems protect and uplift people, not merely the leader’s image.", "My authority makes others safer, fairer and more capable.", R.drawable.icon_compassion)
        ),
        turningPoint = "Before demanding a standard, ask whether your own repeated conduct makes that standard believable.",
        practice = "Model one behaviour you want others to practise and give public credit to someone who serves the group well.",
        verses = listOf(ConceptVerse("3.21", "People follow the example of the influential", "gita_verse/3/21"), ConceptVerse("3.25", "The wise act for the welfare of the world", "gita_verse/3/25")),
        icon = R.drawable.icon_leadership
    ),
    GitaConceptMap(
        id = "fear-courage",
        title = "Fear to Courageous Action",
        subtitle = "Courage begins when fear is understood but no longer allowed to abandon rightful duty.",
        stages = listOf(
            ConceptStage("Fear", "The mind anticipates pain, rejection, failure or loss and prepares to protect itself.", "My body tightens and I imagine only the worst outcome.", R.drawable.letters_icon_star),
            ConceptStage("Avoidance", "Fear invites delay, excuses or retreat even when a safe and rightful action remains possible.", "Temporary relief replaces the responsibility I know is mine.", R.drawable.icon_previous),
            ConceptStage("Remembered Duty", "Clarity returns when we remember whom the action protects and why it matters.", "I reconnect the difficult step with truth, care and responsibility.", R.drawable.icon_dharma),
            ConceptStage("Courageous Action", "Courage acts with preparation and support while fear may still be present.", "My voice may shake, but I take the next safe and honest step.", R.drawable.icon_courage)
        ),
        turningPoint = "Ask whether fear is warning of real danger that needs protection or discomfort that accompanies meaningful growth.",
        practice = "Name one avoided duty, reduce it to a safe ten-minute step and tell a supportive person before beginning.",
        verses = listOf(ConceptVerse("2.3", "Do not surrender to weakening fear", "gita_verse/2/3"), ConceptVerse("2.38", "Meet gain and loss with balance, then act", "gita_verse/2/38")),
        icon = R.drawable.icon_courage
    ),
    GitaConceptMap(
        id = "own-path",
        title = "Your Own Duty to Growth",
        subtitle = "An imperfect authentic path develops strength more deeply than polished imitation.",
        stages = listOf(
            ConceptStage("Your Own Duty", "Dharma begins with the real responsibilities, abilities and context entrusted to you.", "I ask what is truly mine rather than copying another life.", R.drawable.icon_dharma),
            ConceptStage("Imperfect Practice", "Early work may be awkward or incomplete, yet honest practice provides the feedback required for growth.", "I allow myself to be a beginner without hiding behind perfectionism.", R.drawable.icon_karma),
            ConceptStage("Growth", "Repeated effort strengthens skill, judgment and the capacity to serve.", "I compare today’s practice with my earlier practice, not another person’s highlight.", R.drawable.icon_purpose),
            ConceptStage("Fulfilment", "Meaning grows when one’s nature and responsibility are expressed through useful, ethical work.", "My path feels connected to contribution rather than imitation.", R.drawable.icon_lotus)
        ),
        turningPoint = "Do not use ‘my own path’ to excuse harm or avoid learning; authentic duty must remain accountable to dharma.",
        practice = "Identify one responsibility that is genuinely yours and practise it for twenty focused minutes without comparison.",
        verses = listOf(ConceptVerse("18.47", "One’s own duty is better though imperfect", "gita_verse/18/47"), ConceptVerse("18.48", "Natural work may contain imperfections", "gita_verse/18/48")),
        icon = R.drawable.icon_dharma
    ),
    GitaConceptMap(
        id = "senses-peace",
        title = "Guided Senses to Happiness",
        subtitle = "Wise boundaries around attention allow clarity, peace and deeper well-being to grow.",
        stages = listOf(
            ConceptStage("Guided Senses", "The senses continue to experience the world, but attention is not dragged helplessly by every attraction and dislike.", "I choose what I watch, consume and continue thinking about.", R.drawable.icon_chakra),
            ConceptStage("Clear Mind", "Reduced reactivity makes perception less distorted by immediate craving or aversion.", "I can see facts and consequences before acting.", R.drawable.icon_mind),
            ConceptStage("Peace", "Clarity reduces the inner conflict created by chasing and resisting every experience.", "My mind has space between stimulus and response.", R.drawable.icon_inner_peace),
            ConceptStage("Stable Happiness", "A peaceful mind can recognise meaningful well-being beyond quick stimulation.", "I enjoy life without needing constant novelty or excess.", R.drawable.icon_lotus)
        ),
        turningPoint = "Sense control means wise direction, not hatred of the body or fear of ordinary enjoyment.",
        practice = "Choose one attention boundary for today and replace the removed stimulation with rest, learning or connection.",
        verses = listOf(ConceptVerse("2.64", "Guided senses support inner grace", "gita_verse/2/64"), ConceptVerse("2.65", "Peace supports stable understanding and happiness", "gita_verse/2/65")),
        icon = R.drawable.icon_chakra
    ),
    GitaConceptMap(
        id = "craving-contentment",
        title = "Craving to Contentment",
        subtitle = "Peace grows when desires can enter awareness without overflowing and commanding the mind.",
        stages = listOf(
            ConceptStage("Craving", "The mind repeatedly insists that one more possession, approval or experience is necessary for completeness.", "Satisfaction disappears quickly and the next want immediately arrives.", R.drawable.icon_purpose),
            ConceptStage("Agitation", "Competing desires keep attention restless and make ordinary sufficiency feel empty.", "I cannot enjoy what is present because I am rehearsing what is missing.", R.drawable.icon_mind),
            ConceptStage("Contentment", "Contentment recognises enough while still allowing responsible goals and growth.", "I can value what I have without giving up sincere effort.", R.drawable.icon_lotus),
            ConceptStage("Peace", "Like a full ocean receiving rivers, the steady person experiences desires without being swept away by each one.", "A desire can visit without becoming my master.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Change ‘I must have this to be complete’ into ‘I may pursue this without surrendering my peace or values.’",
        practice = "Delay one nonessential desire for twenty-four hours and write three forms of sufficiency already present.",
        verses = listOf(ConceptVerse("2.70", "Peace belongs to the one steady amid desires", "gita_verse/2/70"), ConceptVerse("2.71", "Releasing possessive craving supports peace", "gita_verse/2/71")),
        icon = R.drawable.icon_lotus
    ),
    GitaConceptMap(
        id = "pure-action",
        title = "Right Intention to Pure Action",
        subtitle = "Action becomes clearer when duty, non-attachment and steadiness work together.",
        stages = listOf(
            ConceptStage("Right Intention", "The action begins from duty and welfare rather than revenge, vanity or selfish gain.", "I examine why I want to do this before asking how.", R.drawable.icon_dharma),
            ConceptStage("Detached Action", "The person works diligently without making reward the condition for doing what is right.", "I keep my standard even when praise is uncertain.", R.drawable.icon_karma),
            ConceptStage("Steadiness", "Consistency continues through ordinary difficulty, delay and mixed results.", "I do not abandon the task only because excitement fades.", R.drawable.icon_chakra),
            ConceptStage("Purity", "Action is less contaminated by ego, possessiveness and avoidable harm.", "The work leaves more truth and less regret behind it.", R.drawable.icon_lotus)
        ),
        turningPoint = "A good-looking action can still carry a harmful motive; examine intention, method and consequence together.",
        practice = "Before one important action, write: ‘My duty, my motive, who may be affected and the cleanest method.’",
        verses = listOf(ConceptVerse("18.23", "Pure action follows duty without attachment", "gita_verse/18/23"), ConceptVerse("18.26", "The balanced doer is free from ego and agitation", "gita_verse/18/26")),
        icon = R.drawable.icon_karma
    ),
    GitaConceptMap(
        id = "giving-purification",
        title = "Sacrifice to Purification",
        subtitle = "Offering, generosity and disciplined living refine the person when performed wisely.",
        stages = listOf(
            ConceptStage("Sacrifice", "Sacrifice gives time, effort or comfort toward worship, shared welfare and meaningful responsibility.", "I make room for something larger than immediate preference.", R.drawable.icon_lotus),
            ConceptStage("Charity", "Ethical giving responds to real need with respect and without humiliating the receiver.", "I share suitable help at the right time and place.", R.drawable.icon_compassion),
            ConceptStage("Discipline", "Constructive restraint trains body, speech and mind without abuse or display.", "My practice makes me kinder and more dependable, not harsher and proud.", R.drawable.icon_chakra),
            ConceptStage("Purification", "These practices reduce selfishness and strengthen clarity when their motive is sincere.", "Giving changes how I relate to possession, comfort and ego.", R.drawable.icon_inner_peace)
        ),
        turningPoint = "Sacrifice, charity or discipline lose their purpose when used for status, control, harm or spiritual superiority.",
        practice = "Give one useful thing—time, food, knowledge, attention or money—quietly and with respect for the receiver’s dignity.",
        verses = listOf(ConceptVerse("18.5", "Sacrifice, charity and discipline should not be abandoned", "gita_verse/18/5"), ConceptVerse("17.20", "Pure charity is given appropriately without return", "gita_verse/17/20")),
        icon = R.drawable.icon_compassion
    ),
    GitaConceptMap(
        id = "remembrance-grace",
        title = "Remembrance to Grace",
        subtitle = "Remembering Krishna can guide action, deepen refuge and reduce the loneliness of control.",
        stages = listOf(
            ConceptStage("Remembrance", "The mind returns to Krishna as a living reference for truth, love and dharma.", "I pause before action and remember the values I want to embody.", R.drawable.icon_peacock_feather),
            ConceptStage("Dedicated Action", "Remembrance enters duty so that work is performed sincerely and offered beyond ego.", "I act carefully instead of using prayer to avoid responsibility.", R.drawable.icon_karma),
            ConceptStage("Refuge", "The heart relies on the Divine while accepting wise human help and the limits of personal control.", "I do what is mine and stop pretending that everything depends on me alone.", R.drawable.icon_lotus),
            ConceptStage("Grace", "Grace is received as strength, guidance and support that cannot be reduced to ego’s achievement.", "I notice help, remain grateful and continue the next right action.", R.drawable.letters_icon_star)
        ),
        turningPoint = "Remembrance is not escape. Let it make the next action more honest, compassionate and courageous.",
        practice = "Before one duty, remember Krishna, name your intention, complete the work and close with thanks.",
        verses = listOf(ConceptVerse("18.57", "Dedicate action and keep the mind centred", "gita_verse/18/57"), ConceptVerse("18.58", "Grace supports one through difficulty", "gita_verse/18/58")),
        icon = R.drawable.icon_peacock_feather
    ),
    GitaConceptMap(
        id = "doubt-action",
        title = "Doubt to Decisive Action",
        subtitle = "Honest doubt can mature into discernment when it seeks knowledge instead of endless delay.",
        stages = listOf(
            ConceptStage("Doubt", "The mind sees conflicting possibilities and hesitates because truth or consequence is unclear.", "I keep reopening the same question without learning anything new.", R.drawable.icon_ask_krishna),
            ConceptStage("Honest Inquiry", "The person gathers facts, asks trustworthy guides and examines assumptions.", "My questions seek clarity rather than permission for the answer I already want.", R.drawable.icon_teachings),
            ConceptStage("Discernment", "Knowledge is tested against dharma, evidence, duty and likely harm.", "I can explain why one option is wiser even without complete certainty.", R.drawable.icon_strategy),
            ConceptStage("Decisive Action", "Once reasonably clear, the person acts and remains willing to correct course when new truth appears.", "I stop using uncertainty as a hiding place from responsibility.", R.drawable.icon_courage)
        ),
        turningPoint = "Distinguish a question that still needs information from anxiety that keeps demanding impossible certainty.",
        practice = "Set a decision time, gather the two most important missing facts, consult one unbiased guide and then choose.",
        verses = listOf(ConceptVerse("4.41", "Knowledge and released attachment cut binding doubt", "gita_verse/4/41"), ConceptVerse("4.42", "Use wisdom to cut doubt and rise to action", "gita_verse/4/42")),
        icon = R.drawable.icon_strategy
    ),
    GitaConceptMap(
        id = "mind-ally",
        title = "Self-Observation to Inner Strength",
        subtitle = "The mind becomes an ally when it is understood, guided and trained through conscious action.",
        stages = listOf(
            ConceptStage("Self-Observation", "Awareness notices thoughts, habits and emotional patterns without immediately becoming them.", "I can say ‘anger is present’ instead of ‘anger is all I am.’", R.drawable.icon_mind),
            ConceptStage("Self-Uplift", "We use available choices, support and discipline to raise rather than degrade ourselves.", "I choose the next action that protects my future self.", R.drawable.icon_courage),
            ConceptStage("Mind as Ally", "A repeatedly guided mind begins to support focus, restraint and recovery.", "My inner voice helps me return instead of pushing me deeper into shame.", R.drawable.icon_friendship),
            ConceptStage("Inner Strength", "Self-mastery creates dependable courage that does not require the absence of difficulty.", "I trust myself to pause, seek help and choose according to values.", R.drawable.icon_chakra)
        ),
        turningPoint = "Self-uplift is not isolated self-reliance; wise teachers, family, friends and professional help can support the work.",
        practice = "Notice one harmful inner sentence, replace it with one truthful instruction and complete the action it recommends.",
        verses = listOf(ConceptVerse("6.5", "Lift yourself through the mind", "gita_verse/6/5"), ConceptVerse("6.6", "The mastered mind becomes a friend", "gita_verse/6/6")),
        icon = R.drawable.icon_friendship
    )
)

fun gitaConceptMap(id: String?): GitaConceptMap =
    gitaConceptMaps.firstOrNull { it.id == id } ?: gitaConceptMaps.first()
