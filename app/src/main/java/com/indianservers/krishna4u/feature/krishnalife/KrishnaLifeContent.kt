package com.indianservers.krishna4u.feature.krishnalife

import com.indianservers.krishna4u.R

data class KrishnaLifeEventUi(
    val id: String,
    val title: String,
    val era: String,
    val subtitle: String,
    val icon: Int,
    val illustration: Int,
    val background: Int,
    val qualities: String,
    val story: String,
    val context: String,
    val lessons: List<String>,
    val reflection: String,
    val openingQuestion: String = reflection,
    val choicePrompt: String = "What would you do in this situation?"
) {
    /** A youth-friendly narrative: curiosity, episode, deeper meaning, then a modern-life connection. */
    val storyText: String
        get() = expandedLifeStory(id, openingQuestion, story, context)

    /** Every lesson closes with a practical set of five takeaways. */
    val takeaways: List<String>
        get() = lessons + listOf(
            "You do not need to be perfect. You only need to choose your next right step.",
            "Pause and ask which choice is true, kind, and fair.",
            "Try this lesson in one small action today."
        )

    val familyStoryText: String
        get() = simpleLifeStories[id] ?: listOf(openingQuestion, story, context).joinToString("\n\n")

    /** Youth mode keeps the story direct and pauses at the real-life moral choice. */
    val youthStoryText: String
        get() = youthLifeStory(id, openingQuestion, story, choicePrompt)

    val familyDiscussionQuestions: List<String>
        get() = listOf(
            choicePrompt,
            "Which action in this story showed ${qualities.substringBefore(" · ").lowercase()}?",
            "What is one way our family can practise this lesson together this week?"
        )

    fun storyFor(modeId: String): String = when (modeId) {
        "kids" -> familyStoryText
        "teens" -> youthStoryText
        else -> storyText
    }

    fun takeawaysFor(modeId: String): List<String> = when (modeId) {
        "kids" -> takeaways.take(3)
        "teens" -> takeaways.take(5)
        else -> takeaways
    }

    fun familyQuestionsFor(modeId: String): List<String> = when (modeId) {
        "kids" -> listOf(
            "What did Krishna do in this story?",
            "Who did Krishna help, and how did they feel?",
            "What kind or brave action can our family try today?"
        )
        "adults" -> listOf(
            choicePrompt,
            "Which duty, value or consequence deserves the most attention in this story?",
            "How can we practise this lesson while respecting each family member’s point of view?"
        )
        else -> familyDiscussionQuestions
    }
}

private fun lifeStory(question: String, scene: String, forYou: String): String =
    listOf(question, scene, forYou).joinToString("\n\n")

/**
 * Keeps the opening and story scene together, adds the event's unique moral context,
 * and closes with the direct application written for the reader.
 */
private fun expandedLifeStory(id: String, question: String, story: String, context: String): String {
    val compactStory = simpleLifeStories[id] ?: return listOf(question, story, context).joinToString("\n\n")
    val parts = compactStory.split("\n\n")
    return if (parts.size == 3) {
        listOf(parts[0], parts[1], context, parts[2]).joinToString("\n\n")
    } else {
        listOf(compactStory, context).joinToString("\n\n")
    }
}

private fun youthLifeStory(id: String, question: String, story: String, choicePrompt: String): String {
    val compactStory = simpleLifeStories[id] ?: return listOf(question, story, choicePrompt).joinToString("\n\n")
    val parts = compactStory.split("\n\n")
    return if (parts.size == 3) {
        listOf(parts[0], parts[1], "Now imagine this in your own life. $choicePrompt", parts[2]).joinToString("\n\n")
    } else {
        listOf(compactStory, choicePrompt).joinToString("\n\n")
    }
}

private val simpleLifeStories = mapOf(
    "birth" to lifeStory(
        "Have you ever felt that hope came at the worst possible time?",
        "Mathura was ruled by fear. Krishna was born inside a prison on a dark night. His father, Vasudeva, carried the tiny child across a wild river to Gokul. He could not see the whole future. He only knew that this child needed his courage now.",
        "Your new start may also feel small and unsafe. Do not laugh at it. Protect it with one brave step. You deserve hope, even in a hard time."
    ),
    "gokul" to lifeStory(
        "When did you last feel free enough to laugh and play?",
        "In Gokul, Krishna grew among family, friends, cows, music and games. His joy pulled people close. Even simple days became stories that people loved to tell. He showed that a good life is not made only of big wins. It is also made of warm, full moments.",
        "You do not need to earn every moment of joy. Put the phone down for a while. Play, sing, talk or sit with someone you love. Joy can make your heart strong again."
    ),
    "kaliya" to lifeStory(
        "What would you do if one harmful thing was hurting everyone?",
        "The serpent Kaliya had poisoned the Yamuna. People and animals were in danger. Krishna did not look away. He faced Kaliya, stopped the harm and made the river safe again. He used strength to heal the place, not to show how powerful he was.",
        "You may also need to face a bad habit, cruel act or unsafe choice. Be firm, but do not become cruel. Real courage stops harm and brings peace back."
    ),
    "govardhan" to lifeStory(
        "If everyone was afraid, could you become the calm person they need?",
        "A great storm fell on Vrindavan. Krishna lifted Govardhan Hill and called everyone under it. No one was left outside—not the rich, the poor, the old, the young or the animals. For seven days, the village stayed together under his care.",
        "You may not lift a mountain, but you can lift fear from one heart. Stay calm. Share what you have. Protect the person near you. That is how faith becomes real."
    ),
    "friendship" to lifeStory(
        "Would you still welcome an old friend who had little to offer you?",
        "Sudama came to Krishna’s palace in worn clothes with a small gift. He felt shy. Krishna ran to meet him, held him close and treated him with great love. He saw his friend, not his money or clothes.",
        "Maybe you fear that you are not successful enough to matter. True love does not count your price. You deserve friends who see your heart. Be that kind of friend to someone today."
    ),
    "mathura" to lifeStory(
        "What if doing the right thing means leaving a place you love?",
        "Krishna loved Vrindavan, yet people in Mathura still lived under Kamsa’s fear. He left comfort, returned to Mathura and ended the cruel rule. He did not stop loving Vrindavan. He simply let his love grow into duty.",
        "You can miss comfort and still move toward your purpose. Fear does not mean you are on the wrong road. Ask what needs your courage, then take the next honest step."
    ),
    "dwarka" to lifeStory(
        "Is a loud fight always braver than a wise plan?",
        "Krishna saw that endless attacks were putting families in danger. He helped his people move and build Dwarka, a safer home. Some may have called it running away. Krishna cared more about people than about looking brave.",
        "You do not need to win every fight. A new plan can be an act of courage. Protect the people who trust you, and build something better."
    ),
    "peace" to lifeStory(
        "Would you ask for peace even when you think the other side may say no?",
        "Before the great war, Krishna went to the Kaurava court as a peace messenger. He asked for a fair answer and tried to stop the loss of many lives. He was ready to stand for dharma, but he first gave peace a true chance.",
        "A calm talk is not weakness. Say what is wrong without hate. Listen, set fair limits and ask for help. You deserve peace, but not peace built on silent harm."
    ),
    "kurukshetra" to lifeStory(
        "Have you ever known your duty but felt too upset to move?",
        "On the field of Kurukshetra, Arjuna’s hands shook. He put down his bow and said he could not fight. Krishna did not call him weak. He listened, answered his questions and helped him see duty, the soul and right action more clearly.",
        "Your feelings matter, but they do not have to rule every choice. Pause. Ask for wisdom. Then do the next right thing without trying to control every result."
    ),
    "butter" to lifeStory(
        "Can a funny act still need a sorry?",
        "Little Krishna loved butter and his games made Gokul laugh. But other people had worked for that butter. When Yashoda asked questions, love did not remove the need to answer. Joy and care had to stay together.",
        "You are allowed to have fun. If your fun hurts or upsets someone, be brave enough to say sorry and make it right. A good heart can laugh and learn."
    ),
    "fruit-seller" to lifeStory(
        "Can a tiny gift still be full of love?",
        "A fruit seller came through Gokul. Krishna ran to her with grain in his little hands, but much of it fell on the way. She saw his honest wish to give and filled his hands with fruit. The story says her basket was later filled with treasure.",
        "Your gift does not need to be big to matter. Give time, thanks or help with a full heart. You already have something good to offer."
    ),
    "universe" to lifeStory(
        "Have you ever judged too fast and later found that you were wrong?",
        "Krishna’s friends told Yashoda that he had eaten mud. She asked him to open his mouth. The story says she saw the whole universe there. One small moment broke open her old idea of what was possible.",
        "Do not punish before you know the truth. Ask, listen and check. People are often more than the first story you hear about them."
    ),
    "twin-trees" to lifeStory(
        "Can a mistake become the door to a better life?",
        "Krishna pulled a wooden mortar between two trees. The trees fell, and two proud beings were set free from an old curse. Their fall was not the end. It became the moment when pride broke and a new life began.",
        "A result can wake you up without making you worthless. Admit the mistake. Learn. Change. You deserve a life that is bigger than your old habit."
    ),
    "bakasura" to lifeStory(
        "What would you do if someone strong scared a smaller child?",
        "Bakasura came like a huge crane and attacked Krishna’s friends. Krishna stood between the danger and the children. He did not fight to look great. He acted because someone needed protection.",
        "You do not have to face danger alone. Stand beside the person being hurt, call a trusted adult and refuse to join the bully. Even a shaking voice can be brave."
    ),
    "aghasura" to lifeStory(
        "If all your friends take a risk, does that make it safe?",
        "Aghasura looked like a giant cave. The boys rushed inside before they knew the danger. Krishna went after them, stopped the threat and brought them out. Their group excitement had hidden the warning signs.",
        "You are not weak when you pause or say no. A real friend does not push others toward danger. Think, warn your friends and get safe help."
    ),
    "brahma-calves" to lifeStory(
        "Who in your group is treated as if they do not matter?",
        "Brahma hid the calves and the cowherd boys to test Krishna. Krishna cared for each home until Brahma saw that no life was small or easy to replace. The people he had treated as ordinary were full of divine worth.",
        "Marks, money and fame do not decide a person’s value. Make room for the quiet child. Speak with respect. The same sacred light lives in you and in them."
    ),
    "forest-fire" to lifeStory(
        "When everyone starts to panic, can you help one person stay calm?",
        "A forest fire closed around Krishna’s friends and the cows. Fear could have made everyone run in different ways. Krishna gathered them, held their trust and led them through the danger together.",
        "Being scared is human. Slow your breath, alert an adult and follow the safety plan. Calm is not doing nothing. Calm helps you choose what saves lives."
    ),
    "cows-home" to lifeStory(
        "If almost everyone is safe but one is missing, is the work done?",
        "Krishna’s cowherd days were full of music, but they also held daily duty. He watched the herd and knew when one cow had wandered away. He did not say, ‘Most are here.’ He cared enough to bring every one home.",
        "Small duties show who you are. Finish what others trust you to do. Notice the one left out. Your steady care matters even when no one claps."
    ),
    "kubja" to lifeStory(
        "Can one kind meeting change how a hurt person feels about themselves?",
        "In Mathura, people judged Kubja by her body. Krishna did not copy their cruel eyes. He spoke with warmth and received her gift with respect. For that moment, she was not a joke or a label. She was a person who mattered.",
        "You know how it hurts to feel judged. Do not pass that pain to another. Look at the person, not their shape, clothes or status. Respect can help a heart stand tall again."
    ),
    "sandipani" to lifeStory(
        "If you are talented, do you still need to listen and practise?",
        "Krishna and Balarama studied with Sage Sandipani. Krishna did not say that he was too gifted for lessons. He listened, worked and respected his teacher. Great ability did not make him proud.",
        "You do not need to know everything today. Asking a question is not shameful. Talent grows when you give it time, practice and a humble heart."
    ),
    "guru-dakshina" to lifeStory(
        "How can you truly thank the person who helped you grow?",
        "When his studies ended, Krishna asked his teacher what he could do in return. He did not treat learning like something to take and forget. He chose a hard act of care for his teacher’s family.",
        "A thank-you becomes strong when you live it. Use what you learned well. Help another person. Let your good work show your teacher that their time mattered."
    ),
    "kuvalayapida" to lifeStory(
        "Can a loud show of power make a wrong thing right?",
        "Kamsa placed a fierce elephant at Mathura’s gate to frighten Krishna. The danger was real, but Krishna did not let fear choose his path. He stayed focused on why he had come and faced the next step.",
        "Someone may try to make you feel small. You can feel fear and still act with care. Prepare, get support and keep your purpose stronger than their noise."
    ),
    "kamsa-justice" to lifeStory(
        "After you stop a bully, should you become the new bully?",
        "When Kamsa fell, Krishna did not grab the throne for himself. He freed Ugrasena and helped bring fair rule back to Mathura. Winning did not become an excuse for pride or revenge.",
        "The best victory makes life safer and fairer. Do not shame a defeated person just because you can. Use your strength to repair what was broken."
    ),
    "strategic-retreat" to lifeStory(
        "Is walking away always fear, or can it sometimes be wisdom?",
        "Mathura faced attack again and again. Krishna saw that a never-ending fight would cost innocent lives. He changed the plan and led the people toward safety in Dwarka. He chose their future over his image.",
        "You do not have to stay in every fight to prove courage. Change the plan when it protects what matters. Wise people serve the goal, not their pride."
    ),
    "rukmini" to lifeStory(
        "What if everyone plans someone’s future without hearing their voice?",
        "Rukmini sent Krishna a message about the choice she wanted and the pressure around her. Krishna listened. He did not treat her as a prize. He took her words seriously and helped her voice matter.",
        "When someone trusts you, listen before you take over. Respect their choice and get safe help when needed. Love protects a voice; it does not own it."
    ),
    "syamantaka" to lifeStory(
        "What would you do if people believed a false story about you?",
        "Krishna was blamed when the Syamantaka jewel went missing. He did not answer one rumour with another. He followed the facts, found what had happened and brought the truth into the open.",
        "A false story hurts, but rage can make the harm grow. Keep records, ask questions and speak the truth calmly. Your good name is best guarded by clear action."
    ),
    "narakasura" to lifeStory(
        "Is a rescue complete if the person is still blamed afterward?",
        "Krishna freed people held by Narakasura. But freedom was not only opening a door. Those who had suffered also needed respect, safety and a place in the world. Their pain did not make them less worthy.",
        "Never blame a person for harm done to them. Believe them, protect their privacy and help them feel welcome. Every survivor deserves dignity."
    ),
    "draupadi" to lifeStory(
        "If a whole room stays silent during harm, does that silence become right?",
        "Draupadi was shamed in a royal hall while many powerful people watched. Her bond with Krishna is remembered as care that did not leave her alone. The story also asks why so many others chose silence.",
        "If someone is being shamed, do not watch or record it for fun. Interrupt safely, stand near them and call a responsible adult. A true friend protects dignity."
    ),
    "rajasuya-service" to lifeStory(
        "If people call you important, are simple tasks now below you?",
        "At the Rajasuya gathering, kings honoured Krishna. Yet he still chose humble work and cared for guests. He did not need to sit above others to know his worth. His service showed his greatness.",
        "You do not lose value when you help. Notice the cup to fill, the chair to move or the person alone. Leadership often begins before anyone gives you a title."
    ),
    "shishupala" to lifeStory(
        "Does forgiving someone mean letting them hurt you again and again?",
        "Shishupala kept insulting Krishna. Krishna was patient and did not react to every word. But patience had a limit when the harm would not stop. A clear line had to protect the gathering.",
        "You can forgive and still say, ‘This must stop.’ Speak clearly, step away and seek help. A boundary is not hate. It is care for safety and self-respect."
    ),
    "army-choice" to lifeStory(
        "Would you choose a huge advantage or one guide you can trust?",
        "Krishna offered two choices: his great army, or Krishna alone and unarmed. Duryodhana chose the army. Arjuna chose Krishna’s wisdom. One looked stronger from outside; the other helped him stay close to dharma.",
        "Big numbers, fame and quick wins can pull your eyes. Ask what will keep your heart honest. The right guide can be worth more than a loud crowd."
    ),
    "karna-dialogue" to lifeStory(
        "Can you speak a hard truth without treating someone like an enemy?",
        "Before the war, Krishna met Karna in private. He shared painful truths and asked Karna to look again at his side. Karna did not change his choice, yet Krishna still spoke to him as a person with worth and duty.",
        "You can challenge a choice without crushing the person. Speak in private when you can. Listen. Be clear. Respect does not require you to agree."
    ),
    "gita-freedom" to lifeStory(
        "Should a good teacher make every choice for you?",
        "Krishna gave Arjuna deep guidance. Then he asked Arjuna to think about it and choose. He did not use love or wisdom to control him. Arjuna had to own his action.",
        "You deserve guidance that helps you grow, not fear that makes you obey. Listen to wise people, think with care and take charge of the choice you make."
    ),
    "gandhari" to lifeStory(
        "What should you do when a person’s deep pain comes out as anger?",
        "After the war, Gandhari carried great grief. Her anger turned toward Krishna. He did not laugh at her or rush to defend himself. He heard the pain under her words and accepted the heavy cost that victory had brought.",
        "Being right does not mean another person stops hurting. Listen before you explain. Make room for tears. A strong heart can hold truth and care at the same time."
    )
)

val krishnaLifeEvents = listOf(
    KrishnaLifeEventUi("birth", "Birth in Mathura", "The beginning", "Hope enters a time of fear", R.drawable.icon_lotus, R.drawable.illustration_09_peacock_feather, R.drawable.bg_04_sacred_cosmic_temple, "Hope · Divine purpose", "Krishna was born to Devaki and Vasudeva while Mathura lived under Kamsa’s oppression. Vasudeva carried the newborn across the Yamuna to safety in Gokul.", "A sacred beginning does not always arrive in comfort. It may appear quietly, asking courage and trust from those who protect it.", listOf("Protect what is innocent", "Hope can arise in the darkest hour", "Courage often begins with one faithful step"), "What new beginning in your life needs patient protection?"),
    KrishnaLifeEventUi("gokul", "Childhood in Gokul", "Early childhood", "Joy, wonder and belonging", R.drawable.icon_playfulness, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_02_vrindavan_dawn, "Joy · Presence", "Raised by Yashoda and Nanda, Krishna’s childhood in Gokul is remembered through affection, music, play and a deep bond with the community.", "Playfulness is not carelessness. It can be a form of presence that restores closeness, creativity and trust.", listOf("Make room for wonder", "Let love be expressed openly", "Presence turns ordinary moments sacred"), "Where has life become too serious for joy to enter?"),
    KrishnaLifeEventUi("kaliya", "The Kaliya Encounter", "Youth in Vrindavan", "Courage without cruelty", R.drawable.icon_courage, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_05_moonlit_sacred_river, "Courage · Restoration", "When the serpent Kaliya poisoned the Yamuna, Krishna confronted the danger, subdued it and restored safety to the river and community.", "Strength is sacred when it stops harm and restores balance—not when it seeks humiliation or revenge.", listOf("Face danger without hatred", "Use strength to restore balance", "Protect shared sources of life"), "How can you confront a harmful pattern without becoming harsh?"),
    KrishnaLifeEventUi("govardhan", "Govardhan", "Vrindavan", "The mountain of faith", R.drawable.icon_dharma, R.drawable.illustration_04_govardhan, R.drawable.bg_02_vrindavan_dawn, "Faith · Protection", "When a fierce storm threatened Vrindavan, Krishna lifted Govardhan and offered shelter to the entire community.", "Krishna redirected ritual born from fear toward gratitude for the land and toward direct responsibility for one another.", listOf("Stand against pride and fear", "Protection is a form of leadership", "Faith becomes real through service"), "Who depends on your steadiness when circumstances become difficult?"),
    KrishnaLifeEventUi("friendship", "Friendship with Sudama", "Dwarka", "Love beyond wealth and status", R.drawable.icon_friendship, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_05_moonlit_sacred_river, "Friendship · Humility", "When his childhood friend Sudama arrived in Dwarka with a humble gift, Krishna welcomed him with warmth, honour and no trace of judgment.", "True friendship remembers the person beneath circumstance. Dignity is not measured by wealth, influence or outward success.", listOf("Welcome without judgment", "Remember those who shared your beginnings", "Receive simple love with gratitude"), "Which friendship deserves your attention without waiting for a reason?"),
    KrishnaLifeEventUi("mathura", "Return to Mathura", "Young adulthood", "Purpose beyond comfort", R.drawable.icon_purpose, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_06_dharma_crossroads, "Purpose · Justice", "Krishna left the beloved familiarity of Vrindavan, returned to Mathura and brought Kamsa’s oppressive rule to an end.", "Dharma sometimes asks us to leave comfort behind. Love for a peaceful life does not excuse silence when others remain under harm.", listOf("Let purpose grow beyond comfort", "Challenge injustice without becoming unjust", "Accept the cost of necessary change"), "What responsibility have you postponed because comfort feels safer?"),
    KrishnaLifeEventUi("dwarka", "Building Dwarka", "Leadership years", "Vision, strategy and service", R.drawable.icon_leadership, R.drawable.illustration_08_wisdom_tree, R.drawable.bg_07_gita_wisdom, "Leadership · Foresight", "Krishna guided his people toward Dwarka, establishing a secure home through foresight, diplomacy and practical leadership.", "Wisdom is not passive. A leader anticipates danger, creates conditions for peace and chooses the wellbeing of people over personal pride.", listOf("Plan beyond the present crisis", "Put collective safety before ego", "Combine compassion with strategy"), "What can you build today that will protect others tomorrow?"),
    KrishnaLifeEventUi("peace", "The Peace Mission", "Before Kurukshetra", "Peace before conflict", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_06_dharma_crossroads, "Diplomacy · Integrity", "Before the war, Krishna went as a peace messenger and sought a just settlement, even when reconciliation appeared unlikely.", "Working for peace is not weakness. It is the discipline of exhausting honourable alternatives before accepting unavoidable conflict.", listOf("Seek dialogue before confrontation", "Do not trade justice for superficial calm", "Let strength make peace possible"), "Have you clearly and calmly attempted the conversation you are avoiding?"),
    KrishnaLifeEventUi("kurukshetra", "Kurukshetra", "The Bhagavad Gita", "Wisdom in the hour of doubt", R.drawable.icon_gita, R.drawable.illustration_03_krishna_arjuna_chariot, R.drawable.bg_03_kurukshetra_cosmos, "Dharma · Wisdom", "When Arjuna was overwhelmed by grief and moral confusion, Krishna unfolded a teaching on the Self, action, wisdom, devotion and freedom.", "Clarity grows when emotion is honoured but not allowed to decide alone. Right action joins discernment, courage and freedom from selfish attachment.", listOf("Pause before acting from despair", "Examine duty, motive and consequence", "Act sincerely without possessing the result"), "What would you choose if fear of the outcome no longer controlled you?"),
    KrishnaLifeEventUi("butter", "The Butter Mischief", "Childhood in Gokul", "Joy must grow into responsibility", R.drawable.icon_playfulness, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_02_vrindavan_dawn, "Joy · Accountability", "Young Krishna’s love of butter filled Gokul with laughter, complaints and affection. When Yashoda questioned him, the playful moment also became a lesson: charm does not remove our responsibility for what we do.", "Krishna’s childhood invites joy without selfishness. Fun is dharmic when nobody is harmed, sharing replaces greed, and we are honest enough to accept correction.", listOf("Enjoy without hurting others", "Share what you love", "Accept correction without resentment"), "Where can you add joy while still respecting another person’s effort?", openingQuestion = "Can something be funny and still require an apology?", choicePrompt = "If your fun upset someone at home, would you hide, blame a friend, or honestly make it right?"),
    KrishnaLifeEventUi("fruit-seller", "Krishna and the Fruit Seller", "Childhood in Gokul", "A small gift can carry a great heart", R.drawable.icon_compassion, R.drawable.illustration_09_peacock_feather, R.drawable.bg_02_vrindavan_dawn, "Generosity · Gratitude", "A fruit seller came calling through Gokul. Little Krishna ran to her with grain in his tiny hands, but most of it slipped away; moved by his sincerity, she gave him fruit, and tradition remembers her basket becoming filled with treasure.", "The story values intention over size. Krishna responded to a humble worker with trust and generosity, showing that respect and heartfelt giving matter more than a perfect transaction.", listOf("Honour every kind of work", "Give sincerely, even when the gift is small", "Respond to generosity with gratitude"), "Whose ordinary work can you appreciate today?", openingQuestion = "If you had very little, could your gift still be valuable?", choicePrompt = "Would you keep your last handful for yourself or offer it with a sincere heart?"),
    KrishnaLifeEventUi("universe", "The Universe in Krishna’s Mouth", "Childhood in Gokul", "Wonder beyond quick judgment", R.drawable.icon_om, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_04_sacred_cosmic_temple, "Wonder · Humility", "When Krishna’s friends said he had eaten mud, Yashoda asked him to open his mouth. The tradition says she saw the whole universe there and realised that the child before her could not be contained by her assumptions.", "Yashoda investigated before punishing, and wonder replaced certainty. Dharma asks us to seek truth carefully and remain humble when reality is larger than our first opinion.", listOf("Listen before judging", "Check facts with care", "Stay humble before what you do not understand"), "Which opinion of yours may need a second look?", openingQuestion = "Have you ever been completely sure—and then discovered you were wrong?", choicePrompt = "If two friends gave different stories, would you react immediately or calmly seek the truth?"),
    KrishnaLifeEventUi("twin-trees", "The Two Arjuna Trees", "Childhood in Gokul", "Freedom begins when pride falls", R.drawable.icon_inner_peace, R.drawable.illustration_08_wisdom_tree, R.drawable.bg_02_vrindavan_dawn, "Humility · Transformation", "In a traditional childhood story, Krishna pulled the wooden mortar between two trees, bringing them down and freeing Nalakuvara and Manigriva from a condition born of arrogance. Their freedom began with recognition and change.", "Krishna did not merely expose pride; he opened a path beyond it. Dharma corrects us so that we can grow, not so that we remain trapped in shame.", listOf("Let consequences teach you", "Replace pride with humility", "Believe people can genuinely change"), "Which habit would you like to be free from?", openingQuestion = "Can a mistake become the beginning of a better life?", choicePrompt = "If your pride caused harm, would you defend it or use the consequence to change?"),
    KrishnaLifeEventUi("bakasura", "Facing Bakasura", "Youth in Vrindavan", "Courage protects the vulnerable", R.drawable.icon_courage, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_06_dharma_crossroads, "Courage · Protection", "Bakasura appeared as a dangerous crane and threatened Krishna’s companions. Krishna faced the danger directly and stopped it before more harm could reach the children and community.", "Krishna’s courage was protective, not performative. Dharma does not ask us to seek fights; it asks us not to abandon someone who is being harmed.", listOf("Stand beside those who feel powerless", "Get trusted help when danger is real", "Use strength to stop harm, not display ego"), "Who might need you to stand beside them?", openingQuestion = "What would you do if a stronger person frightened someone smaller?", choicePrompt = "Would you watch silently, join the bully, or seek help and stand with the person being targeted?"),
    KrishnaLifeEventUi("aghasura", "The Aghasura Trap", "Youth in Vrindavan", "Do not follow danger blindly", R.drawable.icon_mind, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_05_moonlit_sacred_river, "Awareness · Peer courage", "Aghasura disguised himself as a vast cave-like serpent. The cowherd boys entered before understanding the danger, and Krishna went after them, overcame the threat and brought them safely out.", "Curiosity needs discernment. Krishna protected his friends, while the story reminds young people that a group’s excitement does not make a risky choice wise.", listOf("Pause before following a crowd", "Notice warning signs", "Real friendship protects friends from reckless choices"), "When have you felt pressure to follow the group?", openingQuestion = "If all your friends enter a dangerous place, does following them make it safe?", choicePrompt = "Would you follow to avoid looking afraid, or pause, warn them and call a trusted adult?"),
    KrishnaLifeEventUi("brahma-calves", "Brahma and the Missing Calves", "Youth in Vrindavan", "No one is ordinary or replaceable", R.drawable.icon_relationships, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_02_vrindavan_dawn, "Respect · Equality", "Brahma tested Krishna by hiding the calves and cowherd boys. Krishna responded by caring for every family in their forms until Brahma understood that the divine dignity he sought was already present in each seemingly ordinary life.", "The lesson turns arrogance into reverence. Dharma asks us not to rank human worth by popularity, marks, money or status.", listOf("See dignity in every person", "Do not use intelligence to humiliate", "Let wonder replace superiority"), "Who is often overlooked in your class or group?", openingQuestion = "Have you ever treated someone as unimportant because others did?", choicePrompt = "Would you ignore the quiet student or make space for their voice and friendship?"),
    KrishnaLifeEventUi("forest-fire", "The Forest Fire", "Youth in Vrindavan", "Calm leadership in a crisis", R.drawable.icon_leadership, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_05_moonlit_sacred_river, "Calm · Trust", "When a forest fire surrounded the cowherd community, fear spread quickly. The stories remember Krishna gathering everyone’s trust and bringing them through the danger rather than allowing panic to scatter them.", "In a crisis, calm is service. Dharma means thinking clearly, keeping people together and following the safest responsible action.", listOf("Do not spread panic", "Help younger people follow safety instructions", "Calm thinking protects a group"), "How can you become steadier during an emergency?", openingQuestion = "When everyone panics, who helps the group think clearly?", choicePrompt = "Would you run without looking back, or stay calm, alert adults and help others follow the safety plan?"),
    KrishnaLifeEventUi("cows-home", "Bringing Every Cow Home", "Youth in Vrindavan", "Duty includes the one who is missing", R.drawable.icon_dharma, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_02_vrindavan_dawn, "Duty · Care", "Krishna’s days as a cowherd were not only music and play. He watched the herd, knew when one wandered and treated bringing every animal home safely as a responsibility.", "Dharma often appears in repeated, unnoticed duties. Krishna’s care teaches that a goal is incomplete when the vulnerable are forgotten.", listOf("Finish the responsibility you accepted", "Notice who has been left behind", "Care consistently, not only when praised"), "What daily duty can others trust you to complete?", openingQuestion = "If ninety-nine are safe but one is missing, is your work finished?", choicePrompt = "Would you say ‘most are safe’ or patiently search until the one depending on you is found?"),
    KrishnaLifeEventUi("kubja", "Krishna Meets Kubja", "Mathura", "Respect restores dignity", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_05_moonlit_sacred_river, "Kindness · Dignity", "In Mathura, Krishna met Kubja, a woman often judged by her appearance. He spoke to her with warmth and accepted her offering, allowing dignity—not ridicule—to define the encounter.", "Krishna noticed the person whom society might mock. Dharma is visible in how we treat someone who cannot increase our popularity or power.", listOf("Never make appearance a reason for cruelty", "Receive sincere offerings respectfully", "Let kindness restore belonging"), "How do you respond when others mock someone’s appearance?", openingQuestion = "Can one respectful moment change how a person sees themselves?", choicePrompt = "Would you laugh with the crowd or greet the person with the same respect you want for yourself?"),
    KrishnaLifeEventUi("sandipani", "Learning with Sage Sandipani", "Student years", "Great ability still needs discipline", R.drawable.icon_teachings, R.drawable.illustration_07_open_gita, R.drawable.bg_07_gita_wisdom, "Learning · Discipline", "Krishna and Balarama studied in Sage Sandipani’s gurukula. Though Krishna possessed extraordinary ability, he learned with attention, discipline and respect rather than acting as if talent placed him above a teacher.", "Knowledge grows when curiosity joins humility. Dharma as a student means practising, asking sincerely and honouring those who help us learn.", listOf("Talent does not replace practice", "Ask questions without arrogance", "Respect teachers through sincere effort"), "What would disciplined learning look like for you this week?", openingQuestion = "If you are talented, do you still need to practise and listen?", choicePrompt = "Would you depend only on talent, or build it through patience, routine and respect?"),
    KrishnaLifeEventUi("guru-dakshina", "Krishna’s Gratitude to His Teacher", "Student years", "Learning creates responsibility", R.drawable.icon_teachings, R.drawable.illustration_07_open_gita, R.drawable.bg_04_sacred_cosmic_temple, "Gratitude · Commitment", "After completing his education, Krishna asked Sage Sandipani how he could express gratitude. The traditional account remembers Krishna undertaking a difficult task for his teacher’s family instead of treating education as something simply consumed.", "Gratitude becomes action. Dharma asks us to use what we learn responsibly and remember the people whose patience made our growth possible.", listOf("Thank people through action", "Use knowledge to serve", "Do not forget those who helped you grow"), "How can you thank a teacher, parent or mentor through your conduct?", openingQuestion = "What is the best way to thank someone who taught you?", choicePrompt = "Would you offer only words, or also use the lesson in a way that honours their effort?"),
    KrishnaLifeEventUi("kuvalayapida", "The Elephant at Mathura’s Gate", "Return to Mathura", "Do not let intimidation decide your path", R.drawable.icon_courage, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_06_dharma_crossroads, "Courage · Focus", "Kamsa placed the fierce elephant Kuvalayapida in Krishna’s path to frighten and stop him. Krishna did not allow the display of power to make him abandon the purpose that had brought him to Mathura.", "Dharma is not recklessness, but it also does not surrender merely because wrongdoing looks powerful. Prepare wisely, seek support and keep sight of the right goal.", listOf("Do not confuse intimidation with authority", "Prepare before facing a hard challenge", "Keep purpose stronger than fear"), "What important goal has fear made look impossible?", openingQuestion = "Can someone’s loud display of power make a wrong thing right?", choicePrompt = "Would you abandon a just goal because someone tries to frighten you, or prepare and continue wisely?"),
    KrishnaLifeEventUi("kamsa-justice", "Justice After Kamsa", "Mathura", "Victory should restore, not imitate, oppression", R.drawable.icon_dharma, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_06_dharma_crossroads, "Justice · Restraint", "After Kamsa’s fall, Krishna did not seize Mathura simply for personal power. He restored the imprisoned Ugrasena and helped return lawful responsibility to the kingdom.", "Krishna’s response separated justice from revenge. Dharma removes oppression and rebuilds a fair order; it does not use victory as permission to become another tyrant.", listOf("Restore fairness after stopping harm", "Do not let victory feed arrogance", "Use power for responsibility, not possession"), "How would you behave after winning an important conflict?", openingQuestion = "After defeating a bully, should you become the new bully?", choicePrompt = "Would you use victory to dominate others or help rebuild fairness for everyone?"),
    KrishnaLifeEventUi("strategic-retreat", "The Strategic Move from Mathura", "Leadership years", "A wise retreat can protect the mission", R.drawable.icon_strategy, R.drawable.illustration_08_wisdom_tree, R.drawable.bg_07_gita_wisdom, "Strategy · Humility", "Repeated attacks placed Mathura’s people at risk. Krishna chose relocation and strategy over proving bravery through endless confrontation, helping create safety in Dwarka.", "Dharma is not controlled by what critics call cowardice. A courageous leader protects people and the long-term mission, even when ego wants a dramatic fight.", listOf("Do not risk others to protect your image", "Changing strategy is not giving up", "Measure courage by responsibility"), "Where might a wiser plan be better than a louder fight?", openingQuestion = "Is walking away always cowardice—or can it sometimes be leadership?", choicePrompt = "Would you fight only to protect your reputation, or change the plan to protect everyone depending on you?"),
    KrishnaLifeEventUi("rukmini", "Listening to Rukmini’s Choice", "Royal years", "Courage respects another person’s voice", R.drawable.icon_relationships, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_06_dharma_crossroads, "Respect · Courage", "Rukmini sent Krishna a message explaining her own choice and the pressure surrounding her marriage. Krishna listened, took her words seriously and acted courageously in response.", "The lesson is not about possession; it is about respecting a person’s clear voice when power tries to silence it. Dharma protects dignity, consent and responsible choice.", listOf("Listen when someone asks for help", "Respect another person’s agency", "Use courage to protect dignity"), "How can you support a friend without taking control of their decision?", openingQuestion = "What if everyone decides someone’s future without listening to them?", choicePrompt = "Would you follow the powerful crowd, or respectfully help the person’s own voice be heard?"),
    KrishnaLifeEventUi("syamantaka", "The Syamantaka Jewel", "Dwarka", "Answer rumours with truth and patience", R.drawable.icon_teachings, R.drawable.illustration_09_peacock_feather, R.drawable.bg_07_gita_wisdom, "Truth · Reputation", "When Krishna was falsely suspected in the disappearance connected with the Syamantaka jewel, he did not answer the rumour with more rumour. He investigated, followed the evidence and brought the truth into the open.", "Dharma protects reputation through transparent action, not rage or manipulation. Truth may require patience, courage and a willingness to examine facts.", listOf("Do not forward an unverified accusation", "Use evidence instead of anger", "Clear misunderstanding without humiliating others"), "What should you do before sharing a damaging message online?", openingQuestion = "How would you react if many people believed a false story about you?", choicePrompt = "Would you attack everyone, create another rumour, or calmly gather facts and tell the truth?"),
    KrishnaLifeEventUi("narakasura", "Freeing the Captives", "Royal years", "Protect dignity after rescue", R.drawable.icon_courage, R.drawable.illustration_01_krishna_full_body, R.drawable.bg_06_dharma_crossroads, "Protection · Inclusion", "Traditions remember Krishna defeating Narakasura and freeing people held against their will. The deeper moral challenge came afterward: those who suffered were not to be blamed, shamed or abandoned.", "Dharma does not stop at removing danger. It restores dignity and belonging to survivors, refusing the cruel idea that another person’s wrongdoing reduces their worth.", listOf("Never blame someone for harm done to them", "Make safety include dignity", "Welcome people without stigma"), "How can a community help someone feel safe and respected again?", openingQuestion = "Is rescue complete if a survivor is still rejected afterward?", choicePrompt = "Would you repeat cruel labels or help the person return with dignity and belonging?"),
    KrishnaLifeEventUi("draupadi", "Krishna Stands with Draupadi", "Mahabharata years", "Friendship does not disappear in humiliation", R.drawable.icon_friendship, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_03_kurukshetra_cosmos, "Friendship · Dignity", "Draupadi was humiliated in a royal assembly while many powerful people remained silent. Her bond with Krishna is remembered as protection and unwavering solidarity when dignity was under attack.", "The story asks us to notice the silence around injustice. Dharma and friendship require us to refuse humiliation, seek help and stand beside the person being targeted.", listOf("Do not become a silent audience to humiliation", "Believe a friend who asks for help", "Protect dignity without blaming the victim"), "When have you wished someone had spoken up for you?", openingQuestion = "If everyone important stays silent, does silence become right?", choicePrompt = "Would you watch public humiliation, record it for entertainment, or interrupt safely and get responsible help?"),
    KrishnaLifeEventUi("rajasuya-service", "Krishna Serves the Guests", "Rajasuya gathering", "No task is beneath a true leader", R.drawable.icon_leadership, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_04_sacred_cosmic_temple, "Service · Humility", "At the Rajasuya gathering, tradition remembers Krishna accepting humble service, including attending to guests, even though kings honoured him. Status did not make ordinary care unworthy of him.", "Krishna showed that leadership is not a throne; it is readiness to serve. Dharma gives dignity to necessary work and asks leaders to participate, not merely command.", listOf("Respect every honest task", "Serve without demanding attention", "Let leadership begin with contribution"), "Which unnoticed task can you do without being asked?", openingQuestion = "If everyone calls you important, are simple chores beneath you?", choicePrompt = "Would you wait to be served or notice what needs doing and quietly help?"),
    KrishnaLifeEventUi("shishupala", "Patience and the Boundary", "Rajasuya gathering", "Forgiveness needs wise limits", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_06_dharma_crossroads, "Patience · Boundaries", "Shishupala repeatedly insulted and provoked Krishna. Krishna showed extraordinary patience, yet the story also reaches a boundary when continued aggression threatened the order and dignity of the gathering.", "Forgiveness is not permission for endless harm. Dharma can combine patience, clear warnings, proportionate boundaries and action when a pattern refuses to change.", listOf("Do not react to every provocation", "State boundaries clearly", "Seek responsible intervention when harm continues"), "Which boundary can you communicate calmly and clearly?", openingQuestion = "Does forgiving someone mean allowing the same harm forever?", choicePrompt = "Would you explode at the first insult, tolerate endless abuse, or use patience and clear boundaries?"),
    KrishnaLifeEventUi("army-choice", "Krishna or Krishna’s Army", "Before Kurukshetra", "Choose values over impressive power", R.drawable.icon_dharma, R.drawable.illustration_03_krishna_arjuna_chariot, R.drawable.bg_03_kurukshetra_cosmos, "Values · Discernment", "Krishna offered a choice: his powerful army on one side, or Krishna himself, unarmed, on the other. Duryodhana chose the visible force; Arjuna chose Krishna’s guidance.", "The choice reveals character. Dharma asks us to look beyond size, fame and advantage and ask which companion, principle or path will keep us aligned with what is right.", listOf("Do not measure everything by power", "Choose wise guidance", "Let values direct ambition"), "What guides your biggest goals: popularity, power or principle?", openingQuestion = "Would you choose a huge advantage or one truthful guide?", choicePrompt = "If success without values and slower success with integrity stood before you, which would you choose?"),
    KrishnaLifeEventUi("karna-dialogue", "Krishna Speaks Privately with Karna", "Before Kurukshetra", "Respect can survive disagreement", R.drawable.icon_relationships, R.drawable.illustration_03_krishna_arjuna_chariot, R.drawable.bg_06_dharma_crossroads, "Truth · Respect", "Before the war, Krishna spoke privately with Karna, revealed difficult truths and invited him to reconsider his allegiance. Karna did not change sides, yet the conversation treated him as a responsible person capable of moral choice.", "Dharma speaks honestly without reducing a person to an enemy label. We can challenge a choice, recognise complexity and preserve respect even when agreement is impossible.", listOf("Speak hard truths privately when possible", "Do not erase another person’s humanity", "Let loyalty be examined by dharma"), "Can you disagree strongly without becoming cruel?", openingQuestion = "How do you speak to someone whose loyalty is leading them toward harm?", choicePrompt = "Would you insult them publicly or speak honestly, listen carefully and leave them responsible for their choice?"),
    KrishnaLifeEventUi("gita-freedom", "Krishna Lets Arjuna Choose", "The Bhagavad Gita", "A mentor guides but does not control", R.drawable.icon_gita, R.drawable.illustration_03_krishna_arjuna_chariot, R.drawable.bg_03_kurukshetra_cosmos, "Freedom · Responsibility", "After giving profound guidance, Krishna did not force Arjuna to obey blindly. He asked Arjuna to reflect fully and then act according to his considered understanding.", "Dharma needs awakened judgment, not dependence. A good teacher explains, questions and supports; the learner must finally own the decision and its responsibility.", listOf("Think before obeying pressure", "Seek guidance without surrendering judgment", "Own the choice you make"), "Which decision needs both wise advice and your own reflection?", openingQuestion = "If a teacher gives advice, should they also control your final choice?", choicePrompt = "Would you obey without thinking, reject all advice, or understand deeply and then choose responsibly?"),
    KrishnaLifeEventUi("gandhari", "Krishna Receives Gandhari’s Grief", "After Kurukshetra", "Do not defend yourself against another’s pain", R.drawable.icon_compassion, R.drawable.illustration_02_krishna_portrait, R.drawable.bg_05_moonlit_sacred_river, "Empathy · Accountability", "After the war, Gandhari’s grief was immense and her anger turned toward Krishna. He did not mock her sorrow or answer pain with pride; he received the weight of her words and the consequences surrounding victory.", "Even a justified struggle leaves wounds. Dharma asks victors to remain humble, hear grief and accept that being right does not remove the need for compassion.", listOf("Listen before defending yourself", "Do not celebrate another person’s suffering", "Let success remain accountable to its cost"), "How can you make room for someone’s pain without making the moment about yourself?", openingQuestion = "What should you do when someone’s grief becomes anger toward you?", choicePrompt = "Would you argue immediately, or listen with humility and recognise the pain beneath the words?")
)

fun lifeEvent(eventId: String?): KrishnaLifeEventUi = krishnaLifeEvents.firstOrNull { it.id == eventId } ?: krishnaLifeEvents.first { it.id == "govardhan" }
