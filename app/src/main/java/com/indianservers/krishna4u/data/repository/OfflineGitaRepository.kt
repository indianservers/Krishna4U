package com.indianservers.krishna4u.data.repository

import android.content.Context
import org.json.JSONObject

data class OfflineGitaVerse(val chapter: Int, val verse: Int, val sanskrit: String, val transliteration: String, val englishSummary: String, val translator: String)
data class OfflineGitaChapter(val number: Int, val sanskritTitle: String, val title: String, val theme: String, val summary: String)

val gitaChapters = listOf(
    OfflineGitaChapter(1, "अर्जुनविषादयोग", "Arjuna Vishada Yoga", "The Yoga of Arjuna’s Grief", """The armies of the Pandavas and Kauravas assemble at Kurukshetra, prepared for a war caused by injustice and a long failure of reconciliation. Arjuna asks Krishna, his charioteer, to place their chariot between the armies. Seeing teachers, elders, cousins and friends on both sides, he is overwhelmed by sorrow. His body trembles, his bow slips, and the promised victory suddenly appears empty. Arjuna fears the destruction of families, traditions and social order, and questions whether any kingdom can justify such suffering. His arguments combine compassion, attachment, duty and confusion, making his crisis deeply human rather than cowardly. Finally, he refuses to fight and sits down in despair. This breakdown becomes the doorway to wisdom: by admitting that he cannot determine the right path alone, Arjuna becomes ready to listen. The chapter shows that sincere spiritual inquiry often begins when familiar answers no longer resolve a moral conflict."""),
    OfflineGitaChapter(2, "सांख्ययोग", "Sankhya Yoga", "The Yoga of Knowledge", """Arjuna formally becomes Krishna’s student and asks for clear guidance. Krishna first distinguishes the eternal Self from the changing body: birth and death belong to embodied existence, while the Self is neither created nor destroyed. Because change, pleasure and pain are temporary, one should meet them with patience and steadiness. Krishna then reconnects this knowledge to Arjuna’s responsibility as a warrior confronting injustice. The central discipline introduced here is karma yoga—acting wholeheartedly while relinquishing possessive attachment to success or failure. Such equanimity protects intelligence from desire, fear and agitation. Krishna describes the sthitaprajna, a person of steady wisdom, who withdraws unhealthy cravings, governs the senses and remains inwardly balanced amid gain, loss, praise and difficulty. The chapter does not recommend indifference; it teaches freedom from compulsive dependence on outcomes. Right understanding, disciplined action and an anchored mind together form the foundation for the paths developed throughout the Gita."""),
    OfflineGitaChapter(3, "कर्मयोग", "Karma Yoga", "The Yoga of Action", """Arjuna wonders why he should engage in battle if knowledge appears higher than action. Krishna explains that no embodied person can remain completely inactive, because nature continually moves the body and mind. Merely restraining outward action while mentally dwelling on desire is not true renunciation. The wiser path is to perform necessary work as yajna—an offering that supports the larger order—without claiming the results as personal possession. Krishna describes a cycle of mutual nourishment linking responsible action, society, nature and the sacred. Even one who is inwardly fulfilled should act for loka-sangraha, the welfare and stability of the world, because others learn from visible conduct. Arjuna is urged to perform his own duty without ego, dedicating action to the Divine. Krishna identifies desire and anger, born from restless passion, as forces that obscure judgment. They must be addressed through disciplined senses, a clear intellect and knowledge of the Self. Freedom is therefore found through purified action, not escape from responsibility."""),
    OfflineGitaChapter(4, "ज्ञानकर्मसंन्यासयोग", "Jnana Karma Sannyasa Yoga", "Wisdom in Action", """Krishna reveals that this yoga is ancient, transmitted through generations but obscured over time. When Arjuna questions how Krishna could have taught it in the distant past, Krishna explains divine manifestation: though unborn and imperishable, the Divine appears whenever dharma declines, protecting the good, confronting destructive forces and restoring balance. Those who understand the divine nature of such action are freed from binding attachment. Krishna then explores the subtle relationship among action, inaction and wrong action. A wise person can act intensely while remaining inwardly free, because selfish craving and ownership have been offered into knowledge. The chapter describes many forms of yajna, including material service, disciplined senses, breath practices, study and the sacrifice of ignorance through insight. All can purify when performed sincerely, yet knowledge is especially transformative because it reveals the unity underlying action. Arjuna is encouraged to approach a realised teacher with humility, thoughtful questions and service. Knowledge cuts doubt, burns past bondage and restores confident, responsible action."""),
    OfflineGitaChapter(5, "कर्मसंन्यासयोग", "Karma Sannyasa Yoga", "Renunciation Through Action", """Arjuna again asks whether renouncing action or performing disciplined action is better. Krishna answers that both can lead toward freedom, but karma yoga is generally more practical and secure. Genuine renunciation is not abandoning work; it is releasing attraction, aversion and the egoistic belief that “I alone am the doer.” A wise person understands that the senses engage with their objects according to nature, while the inner Self remains unattached. Actions offered to the Divine do not cling, just as water does not cling to a lotus leaf. By giving up possessiveness over results, the practitioner gains peace, whereas craving for results creates bondage. Krishna teaches equal vision: the truly learned recognise the same spiritual reality in a scholar, a cow, an elephant, a dog and an outcast. Their happiness depends increasingly on the inner Self rather than unstable external contact. Governing desire and anger, acting for the welfare of all beings, and knowing the Divine as the friend of all lead toward lasting peace."""),
    OfflineGitaChapter(6, "आत्मसंयमयोग", "Dhyana Yoga", "The Yoga of Meditation", """Krishna explains that the true renunciant and yogi is one who performs necessary action without dependence on its rewards. The mind can become either a friend or an enemy: disciplined, it supports freedom; uncontrolled, it produces conflict. A meditator is advised to live moderately, avoiding extremes in food, sleep, work and recreation. In a clean, quiet place, the practitioner sits steadily, aligns body and attention, regulates the senses and repeatedly brings the wandering mind back to a single focus. Meditation gradually produces an inward joy and equanimity that recognises the Self in all beings and all beings in the Self. Arjuna objects that the mind seems as difficult to control as the wind. Krishna agrees that it is restless, yet says it can be trained through consistent practice and non-attachment. When Arjuna fears that an unsuccessful seeker may lose both worldly and spiritual attainments, Krishna offers reassurance: no sincere effort toward goodness is ever wasted. Practice continues to bear fruit and eventually leads onward."""),
    OfflineGitaChapter(7, "ज्ञानविज्ञानयोग", "Jnana Vijnana Yoga", "Knowledge and Realisation", """Krishna begins a deeper account of how the Divine can be known both conceptually and through direct realisation. Material nature is described through earth, water, fire, air, space, mind, intellect and ego, while a higher nature appears as the living consciousness sustaining the world. Everything arises from and rests in the Divine, like jewels strung on an unseen thread. Krishna identifies the sacred essence within ordinary experience—the taste in water, light in the sun and moon, intelligence in the intelligent, strength free from selfish desire. The three gunas veil this underlying source and make divine maya difficult to cross, but wholehearted refuge opens a way beyond it. Four kinds of virtuous seekers turn toward Krishna: those in distress, those seeking understanding, those seeking benefit and those established in wisdom. All are welcomed, though the person who recognises the Divine everywhere possesses especially mature insight. Desire may lead people toward limited forms of worship and limited results; enduring knowledge recognises the one source behind every form."""),
    OfflineGitaChapter(8, "अक्षरब्रह्मयोग", "Akshara Brahma Yoga", "The Imperishable Absolute", """Arjuna asks Krishna to define Brahman, the individual self, action, the material field, the divine principle and the power present in sacrifice. Krishna answers these questions and then focuses on remembrance at death. The final direction of consciousness reflects the tendencies cultivated throughout life; therefore, remembrance of the Divine should not be treated as a last-minute technique. Arjuna is told to remember Krishna while continuing to perform his duty, uniting contemplation with responsible action. Through steady practice, disciplined life energy and unwavering attention, the seeker approaches the imperishable reality beyond change. Krishna describes vast cosmic cycles in which worlds appear and dissolve, contrasting temporary realms with the supreme state from which there is no compulsory return. The chapter also mentions bright and dark paths associated with different post-death journeys. Its practical emphasis, however, is clear: whatever one repeatedly loves, thinks about and serves shapes consciousness. A life of sustained remembrance prepares the mind to remain centred even at its final transition."""),
    OfflineGitaChapter(9, "राजविद्याराजगुह्ययोग", "Raja Vidya Raja Guhya Yoga", "Royal Knowledge and Mystery", """Krishna calls this teaching the sovereign knowledge and deepest mystery because it is purifying, directly realisable and grounded in dharma. The Divine pervades and sustains the entire universe while remaining beyond limitation by it. All beings rest in Krishna, yet Krishna is not confined within any being or event. Creation unfolds cyclically through divine nature without binding the witnessing source. Those who see only Krishna’s human appearance miss this greater reality, while the wise worship with understanding and recognise one presence through many expressions. Krishna accepts every sincere offering—a leaf, flower, fruit or water—when it is given with devotion. Therefore ordinary acts such as eating, working, giving and practising discipline can all be offered, freeing the heart from selfish consequences. The chapter strongly emphasises spiritual accessibility: social position, past conduct or worldly disadvantage cannot prevent a wholehearted person from approaching the Divine. Even someone with a troubled history can become righteous through undivided direction. Devotion transforms daily life into relationship, trust and freedom."""),
    OfflineGitaChapter(10, "विभूतियोग", "Vibhuti Yoga", "Divine Manifestations", """Krishna further explains divine glory so that devotion may be supported by understanding and wonder. He is presented as the source of gods, sages, mental qualities and the entire unfolding cosmos. Qualities such as intelligence, forgiveness, truthfulness, self-control, courage and contentment arise within the one sustaining reality. Those who recognise this remain lovingly absorbed in Krishna and help one another deepen their understanding. Krishna grants them the inner discernment by which they can approach him and dispels ignorance with the lamp of knowledge. Arjuna accepts Krishna as the supreme reality and asks how the Divine may be remembered throughout ordinary experience. Krishna responds with a celebrated series of vibhutis: among lights the sun, among mountains Meru, among bodies of water the ocean, among purifiers the wind, and among warriors Rama. These are not exhaustive identifications but contemplative examples. Wherever exceptional splendour, power, beauty or wisdom appears, it can be understood as a spark of divine magnificence. The entire universe exists through only a fraction of that limitless presence."""),
    OfflineGitaChapter(11, "विश्वरूपदर्शनयोग", "Vishvarupa Darshana Yoga", "The Vision of the Universal Form", """After hearing of Krishna’s manifestations, Arjuna asks to behold the complete divine form directly. Krishna grants him divine sight, because ordinary vision cannot contain such a revelation. Arjuna sees innumerable faces, eyes, weapons, ornaments and celestial wonders gathered within one boundless body. The sun, moon, gods, sages and all worlds appear there simultaneously. Wonder soon becomes terror as Arjuna sees warriors from both armies rushing into blazing mouths and being destroyed. Krishna identifies himself as Time, the mighty force that brings worlds to their end, and tells Arjuna that the opposing warriors are already overtaken by destiny. Arjuna is not asked to become cruel, but to serve as an instrument through the duty before him, without egoistic ownership. Overwhelmed, he praises Krishna, apologises for having treated him casually as a friend, and asks to see the familiar gentle form again. Krishna complies and explains that this vision is approached through undivided devotion, dedicated action, freedom from attachment and goodwill toward all beings."""),
    OfflineGitaChapter(12, "भक्तियोग", "Bhakti Yoga", "The Yoga of Devotion", """Arjuna asks whether worship of Krishna in a personal form or contemplation of the unmanifest absolute is the better path. Krishna affirms that both can lead to the highest reality, but explains that the unmanifest path is especially difficult for embodied minds. Loving devotion offers a more accessible relationship: place the mind and understanding in Krishna and live in steady remembrance. If such concentration is not yet possible, practise returning repeatedly. If sustained practice is difficult, perform actions for the Divine; if that too feels beyond reach, relinquish attachment to the fruits of action. Krishna presents a compassionate ladder rather than a single rigid standard. The chapter then describes the character of a beloved devotee: free from hatred, friendly and compassionate, without possessiveness or ego, balanced in pleasure and pain, forgiving, content, self-controlled and firm in commitment. Such a person neither agitates the world nor is easily agitated by it. Devotion is shown not merely as emotion or ritual, but as a transformed way of relating to every being."""),
    OfflineGitaChapter(13, "क्षेत्रक्षेत्रज्ञविभागयोग", "Kshetra Kshetrajna Vibhaga Yoga", "The Field and Its Knower", """Krishna distinguishes kshetra, the field of experience, from kshetrajna, the knower of that field. The field includes the body, senses, mind, ego, desires, aversions, pleasure, pain and the material elements—everything that can be observed and that changes. The knower is consciousness, while Krishna is described as the ultimate knower present in every field. True knowledge is then expressed through qualities rather than information alone: humility, sincerity, nonviolence, patience, self-control, detachment, awareness of life’s vulnerability, devotion and persistent inquiry into truth. The object of knowledge is the beginningless supreme reality, beyond ordinary categories yet present within and around all beings. Krishna also explains purusha and prakriti: consciousness experiences, while material nature produces bodies, senses and activity through the gunas. Bondage arises through identification with these changing qualities. One who perceives the same undivided reality dwelling within diverse beings, and sees that nature performs actions while the Self remains witnessing, gains liberating vision and does not spiritually injure the self through mistaken identification."""),
    OfflineGitaChapter(14, "गुणत्रयविभागयोग", "Gunatraya Vibhaga Yoga", "The Three Qualities of Nature", """Krishna explains the three gunas, fundamental qualities of material nature that shape thought, behaviour and experience. Sattva is luminous and harmonious, yet it binds through attachment to happiness and knowledge. Rajas is active and passionate, binding through craving, ambition and restless action. Tamas is obscuring and inert, binding through negligence, confusion and sleep. All three operate within every person in changing proportions; their dominance can be recognised through characteristic attitudes and actions. Sattva brings clarity, rajas produces dissatisfaction and grasping, and tamas leads toward heedlessness and delusion. Their influence also shapes the direction of future experience. Liberation requires more than cultivating a pleasant quality: the seeker eventually learns to witness all three without hatred when they arise or longing when they subside. The gunatita remains steady amid pleasure and pain, praise and blame, honour and dishonour, understanding that the gunas are interacting with the gunas. Unwavering devotion to Krishna carries one beyond their binding power toward the foundation of immortality and lasting bliss."""),
    OfflineGitaChapter(15, "पुरुषोत्तमयोग", "Purushottama Yoga", "The Supreme Person", """The chapter opens with the image of an imperishable ashvattha tree whose roots are above and branches extend below. Its leaves are associated with sacred knowledge, while its branches, nourished by the gunas, spread through the world of action and sensory attachment. Because its full form cannot be clearly perceived from within entanglement, the seeker must cut attachment with the strong weapon of non-attachment and search for the supreme source. Krishna describes the individual living being as an eternal portion of the Divine that carries mind and senses from one embodiment to another, as the wind carries fragrance. The undiscerning cannot perceive this process, but those with cultivated vision can. Divine presence is also recognised in sunlight, moonlight, fire, the earth’s sustaining power, digestion, memory, knowledge and the removal of doubt. Krishna then distinguishes the perishable realm, the imperishable self and Purushottama, the Supreme Person beyond both who sustains the three worlds. Knowing this highest reality inspires wholehearted devotion and fulfils the purpose of wisdom."""),
    OfflineGitaChapter(16, "दैवासुरसम्पद्विभागयोग", "Daivasura Sampad Vibhaga Yoga", "Divine and Destructive Qualities", """Krishna contrasts qualities that support freedom with those that deepen bondage. Divine qualities include fearlessness, purity of heart, disciplined pursuit of knowledge, generosity, self-control, sacrifice, study, honesty, nonviolence, truthfulness, absence of destructive anger, renunciation, peace, compassion, gentleness, modesty, steadiness and forgiveness. Destructive tendencies include hypocrisy, arrogance, self-importance, anger, harshness and ignorance. People dominated by these tendencies deny moral order, treat desire as life’s highest purpose and pursue power or pleasure without concern for harm. Their endless anxiety, pride and hostility entangle them further. Krishna identifies desire, anger and greed as three gates leading toward self-destruction; abandoning them allows a person to act for genuine well-being. The distinction is not meant as a permanent label placed on different groups, but as a practical mirror for character and choice. Arjuna is reassured that his disposition is oriented toward the divine. The chapter concludes by recommending scriptural wisdom as a guide when deciding what should and should not be done, joining inner character with disciplined ethical discernment."""),
    OfflineGitaChapter(17, "श्रद्धात्रयविभागयोग", "Shraddhatraya Vibhaga Yoga", "Three Forms of Faith", """Arjuna asks about people who worship sincerely but do not follow established scriptural guidance. Krishna explains that faith reflects a person’s inner nature and takes the colour of sattva, rajas or tamas. The object and manner of worship therefore differ according to the dominant guna. This threefold analysis extends to food, sacrifice, austerity and charity. Sattvic food supports vitality and clarity; rajasic food overstimulates; tamasic food is stale or degrading. Sattvic sacrifice is performed as duty without craving reward, while rajasic sacrifice seeks recognition and tamasic sacrifice ignores wisdom and care. Austerity is described through body, speech and mind: reverence and purity, truthful beneficial words, serenity and self-discipline. When practised with faith and no desire for display, it is sattvic; when motivated by honour or performed through delusion and self-harm, it is not spiritually wholesome. Giving at the right time and place, to a worthy recipient and without expectation, is sattvic. “Om Tat Sat” symbolises the sacred orientation that purifies disciplined action and reminds the seeker that faith requires wise intention."""),
    OfflineGitaChapter(18, "मोक्षसंन्यासयोग", "Moksha Sannyasa Yoga", "Liberation Through Renunciation", """The final chapter gathers and integrates the Gita’s major teachings. Krishna distinguishes sannyasa, renouncing desire-driven actions, from tyaga, relinquishing attachment to the results of action. Necessary duties, sacrifice, charity and discipline should not be abandoned, but performed without possessiveness. Knowledge, action, the doer, intellect, determination and happiness are each analysed according to the three gunas, helping Arjuna recognise how inner quality shapes outward conduct. Krishna discusses varied social duties in terms of disposition and emphasises that one’s own imperfectly performed responsibility is safer than imitating another’s path. Through self-mastery, non-attachment, meditation and devotion, action can become a means to realisation. Arjuna is asked to dedicate every action to Krishna, take refuge in the Divine and listen thoughtfully rather than obey mechanically. The intimate final counsel is wholehearted surrender, with assurance of freedom from binding consequences. Krishna then leaves the decision to Arjuna. His confusion dispelled and understanding restored, Arjuna freely chooses to act. Sanjaya closes by affirming that where Krishna’s wisdom and Arjuna’s committed action unite, there are prosperity, victory and justice.""")
)

val gitaChapterTakeaways: Map<Int, List<String>> = mapOf(
    1 to listOf(
        "Moral confusion should be examined honestly, not hidden behind confidence.",
        "Compassion needs wisdom; emotion alone may not reveal the right action.",
        "Our choices affect families, communities and future generations.",
        "Attachment can make a sincere argument appear clearer than it truly is.",
        "Admitting ‘I do not know’ can be the beginning of genuine learning.",
        "A crisis becomes meaningful when it opens us to guidance and self-inquiry."
    ),
    2 to listOf(
        "The Self is enduring even though the body and circumstances continually change.",
        "Pleasure, pain, gain and loss are temporary; meet them with patient steadiness.",
        "Perform the right action without making inner peace depend on its result.",
        "Equanimity is not indifference—it is clear, balanced participation in life.",
        "Uncontrolled attention grows into attachment, desire, anger and poor judgment.",
        "Steady wisdom develops when the senses, mind and understanding work together."
    ),
    3 to listOf(
        "No one can avoid action completely; even inaction has consequences.",
        "Necessary work becomes freeing when performed as an offering rather than for ego.",
        "Choose your own responsibility instead of escaping into another person’s role.",
        "Those who influence others must model the conduct they hope to inspire.",
        "Work for collective welfare, not only personal success.",
        "Recognise desire and anger early, before they overpower judgment."
    ),
    4 to listOf(
        "Dharma may need renewal when injustice becomes dominant.",
        "Wise action can be intense outwardly while remaining unattached inwardly.",
        "Study, service, discipline and self-observation can all become sacred offerings.",
        "Knowledge burns confusion by revealing what truly acts and what merely witnesses.",
        "Approach a trustworthy teacher with humility, service and sincere questions.",
        "Use understanding to cut doubt and return to responsible action."
    ),
    5 to listOf(
        "True renunciation means releasing selfish attachment, not abandoning useful work.",
        "Offer actions to the Divine and let results arrive without possessiveness.",
        "The ego’s claim ‘I alone am the doer’ creates avoidable burden.",
        "Equal vision recognises the same spiritual dignity in every being.",
        "Inner happiness is more dependable than pleasure borrowed from circumstances.",
        "Peace grows through self-mastery and action for the welfare of all."
    ),
    6 to listOf(
        "A disciplined mind becomes an ally; an untrained mind behaves like an enemy.",
        "Meditation works best with balance in food, sleep, work and recreation.",
        "When attention wanders, return it gently and repeatedly without self-condemnation.",
        "Practice and non-attachment gradually steady even a restless mind.",
        "Deep meditation supports equal vision and compassion for every being.",
        "No sincere effort toward goodness or self-mastery is ever wasted."
    ),
    7 to listOf(
        "Know the Divine through both thoughtful understanding and lived realisation.",
        "Matter, mind and intellect belong to nature; consciousness is the higher sustaining power.",
        "Look for the sacred essence within ordinary experiences, abilities and beauty.",
        "The gunas and personal desires can hide the source underlying all existence.",
        "Distress, curiosity, need and wisdom can each begin a sincere spiritual journey.",
        "Mature knowledge recognises one Divine source behind many forms."
    ),
    8 to listOf(
        "The direction of the final mind is shaped by habits cultivated throughout life.",
        "Remember the Divine while fulfilling responsibility, not only after withdrawing from life.",
        "Repeated attention gradually determines what the heart can remember under pressure.",
        "The changing cosmos points toward an imperishable reality beyond it.",
        "Steady practice prepares consciousness for both daily transitions and death.",
        "Live now in a way that supports the state of mind you hope to carry at the end."
    ),
    9 to listOf(
        "The Divine pervades the universe yet is never limited by it.",
        "Ordinary actions become spiritual when consciously offered without selfish ownership.",
        "A sincere leaf, flower, fruit or water matters more than an impressive empty offering.",
        "No birth, status or troubled past can block wholehearted devotion.",
        "Direction can transform character: a person is not permanently defined by past mistakes.",
        "See devotion as a daily relationship expressed through work, gratitude and remembrance."
    ),
    10 to listOf(
        "Intelligence, courage, forgiveness and truthfulness can be recognised as Divine gifts.",
        "Contemplating excellence can turn admiration into remembrance rather than envy.",
        "The Divine may be remembered through nature, beauty, strength, wisdom and moral greatness.",
        "Spiritual friends deepen understanding by sharing and reflecting together.",
        "Inner discernment grows when knowledge is joined with loving devotion.",
        "Every exceptional glory is only a small expression of limitless Divine splendour."
    ),
    11 to listOf(
        "Reality is far larger than the limited form visible to ordinary perception.",
        "Creation and destruction coexist within the universal movement of time.",
        "Do your part without claiming complete control over history or its outcomes.",
        "Become an instrument of dharma rather than an instrument of personal ambition.",
        "Awe should mature into humility, reverence and responsibility.",
        "The Divine can be approached through dedicated action, devotion and goodwill toward all."
    ),
    12 to listOf(
        "A personal relationship with the Divine offers an accessible path for the embodied mind.",
        "If concentration is difficult, begin with repeated practice and keep returning.",
        "If practice feels difficult, dedicate useful actions and release their rewards.",
        "Devotion appears in character—not only in emotion, ritual or words.",
        "Cultivate friendliness, compassion, forgiveness, contentment and freedom from hatred.",
        "Do not agitate the world unnecessarily, and do not let every disturbance control you."
    ),
    13 to listOf(
        "The body, senses, thoughts and emotions are the changing field of experience.",
        "Consciousness is the knower of the field and should not be reduced to its changing contents.",
        "Humility, patience, sincerity and nonviolence are forms of knowledge in action.",
        "Nature performs through its qualities while the Self remains the witness.",
        "The same spiritual presence dwells within diverse bodies and circumstances.",
        "Clear discrimination between the field and its knower weakens false identification."
    ),
    14 to listOf(
        "Sattva brings clarity, rajas brings restless craving, and tamas brings inertia and confusion.",
        "All three gunas operate within everyone and change from moment to moment.",
        "Observe which quality is shaping a decision before acting on it.",
        "Even attachment to comfort, goodness or knowledge can subtly bind.",
        "Freedom grows when qualities are witnessed without hatred or craving.",
        "Steady devotion helps consciousness stand beyond the gunas’ changing influence."
    ),
    15 to listOf(
        "Worldly entanglement resembles a vast tree whose branches are fed by attachment.",
        "Use non-attachment to cut compulsive dependence, not compassionate involvement.",
        "The individual self carries mental tendencies as wind carries fragrance.",
        "Recognise Divine support in light, nourishment, memory, knowledge and life itself.",
        "The changing world and enduring self both depend upon the Supreme Person.",
        "Remembering the highest source gives direction to knowledge and devotion."
    ),
    16 to listOf(
        "Fearlessness, honesty, compassion, restraint and forgiveness support inner freedom.",
        "Arrogance, hypocrisy, harshness and destructive anger deepen bondage.",
        "Desire, anger and greed are three gates toward self-destruction.",
        "Character is shaped through repeated choices; these qualities are warnings, not permanent labels.",
        "Ethical freedom requires both a pure intention and disciplined conduct.",
        "When uncertain, use trustworthy wisdom to examine what should and should not be done."
    ),
    17 to listOf(
        "Faith takes the colour of a person’s character and understanding.",
        "Food, worship, discipline and giving can promote clarity, restlessness or dullness.",
        "Good austerity strengthens body, speech and mind without self-harm or display.",
        "Speak words that are truthful, beneficial, gentle and timely.",
        "Give at the right time and place without seeking repayment or recognition.",
        "A sacred intention gives disciplined action coherence and meaning."
    ),
    18 to listOf(
        "Do not abandon necessary duty; abandon selfish attachment to its rewards.",
        "Knowledge, action, determination and happiness reflect the gunas shaping them.",
        "Your own imperfect responsibility is safer than imitating another person’s path.",
        "Offer every action to the Divine while cultivating self-mastery and clear understanding.",
        "Surrender is not passivity—it releases ego and supports courageous, ethical action.",
        "Wisdom should restore freedom of choice: Krishna teaches, and Arjuna chooses to act."
    )
)

class OfflineGitaRepository(context: Context) {
    val verses: List<OfflineGitaVerse> = context.assets.open("data/bhagavad_gita_700.json").bufferedReader().use { reader ->
        val array = JSONObject(reader.readText()).getJSONArray("verses")
        List(array.length()) { index -> array.getJSONObject(index).run { OfflineGitaVerse(getInt("chapter"), getInt("verse"), getString("sanskrit"), getString("transliteration"), getString("englishSummary"), getString("translator")) } }
    }
    fun chapter(number: Int): OfflineGitaChapter = gitaChapters.first { it.number == number }
    fun versesInChapter(number: Int): List<OfflineGitaVerse> = verses.filter { it.chapter == number }
    fun verse(chapter: Int, verse: Int): OfflineGitaVerse? = verses.firstOrNull { it.chapter == chapter && it.verse == verse }
}
