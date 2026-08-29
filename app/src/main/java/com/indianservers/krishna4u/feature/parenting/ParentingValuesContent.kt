package com.indianservers.krishna4u.feature.parenting

data class ParentingValuesSession(
    val id: String,
    val value: String,
    val title: String,
    val story: String,
    val lesson: String,
    val parentNote: String,
    val conversationPrompts: List<String>,
    val activityTitle: String,
    val activitySteps: List<String>,
    val kidsTip: String,
    val teensTip: String
) {
    fun facilitationTip(readingMode: String): String =
        if (readingMode == "kids") kidsTip else teensTip
}

val parentingValuesSessions = listOf(
    ParentingValuesSession(
        "honesty-syamantaka", "Honesty", "Krishna Answers a Rumour with Evidence",
        "When Krishna was blamed in the mystery surrounding the Syamantaka jewel, anger alone could not restore the truth. He followed what had happened, searched carefully and brought the evidence into the open. The story does not say that rumours are harmless; it shows that a clear name is protected through patient truth rather than louder accusation.",
        "Honesty includes checking facts, correcting false impressions and refusing to spread what we have not verified.",
        "Children tell the truth more readily when adults seek understanding before punishment. Begin with calm questions and praise honest correction, even when a consequence is still needed.",
        listOf("Why are rumours easy to believe?", "What should Krishna have avoided doing while angry?", "How can we check whether a message is true?", "When have you corrected something untrue about another person?"),
        "Fact, Guess or Rumour?",
        listOf("Write six everyday statements on separate slips—two observed facts, two guesses and two rumours.", "Take turns sorting them and explain what evidence would be needed.", "Create a family rule: pause, verify and protect dignity before sharing."),
        "Use simple examples such as a missing toy. Ask, “What did you see with your own eyes?”",
        "Include social-media examples and discuss screenshots, edited clips, anonymous posts and reputational harm."
    ),
    ParentingValuesSession(
        "honesty-ashwatthama", "Honesty", "When Words Are Technically True but Misleading",
        "During the Mahabharata war, words about Ashwatthama were shaped so Drona would believe his son had died, though an elephant carried the same name. The strategy stopped a devastating warrior, but the epic surrounds the choice with moral discomfort. It asks whether words can be technically accurate while the speaker intends a false belief.",
        "Honesty is not only choosing correct words; it also means caring what another person will reasonably understand.",
        "Do not present this as a trick children may copy. Explain that the episode belongs to an extreme wartime dilemma and that ordinary family, school and digital situations offer truthful alternatives.",
        listOf("Can a sentence be accurate and still deceive?", "Why does intention matter in communication?", "What other choices are available in ordinary life?", "How can we correct a misleading impression?"),
        "The Missing Half",
        listOf("Read three incomplete statements such as, “I finished my work,” when only one part is finished.", "Ask what a listener would believe and what information is missing.", "Rewrite each statement so it is complete, clear and kind."),
        "Keep examples playful and concrete. Avoid frightening details of war.",
        "Invite disagreement about the epic choice and distinguish moral analysis from disrespect toward tradition."
    ),
    ParentingValuesSession(
        "honesty-broken-item", "Honesty", "The Broken Lamp at Home",
        "A child playing indoors knocks over a lamp. Fear immediately invents three stories: blame the pet, say the wind did it, or hide the pieces. Then the child remembers that truth allows repair. They tell the family what happened, help clean safely and accept a fair consequence.",
        "A mistake may create a problem; dishonesty adds a second problem and weakens trust.",
        "Make confession emotionally safe without removing accountability. Thank the child for telling the truth, regulate your own reaction, solve safety first and agree on repair.",
        listOf("Why might someone hide a mistake?", "What response from an adult makes honesty easier?", "What is a fair repair?", "How can trust be rebuilt after a lie?"),
        "Our Family Repair Formula",
        listOf("Practise saying: “This happened. This was my part. I am sorry. I will repair it by…”", "Let each family member use a harmless imaginary example.", "Display the four-sentence formula where everyone can use it—including adults."),
        "Praise telling the truth before discussing the consequence.",
        "Let teenagers help design proportionate consequences and prevention plans rather than imposing humiliation."
    ),

    ParentingValuesSession(
        "responsibility-cows", "Responsibility", "Krishna Cares for the Cows",
        "Krishna’s life in Vrindavan is closely connected with caring for cows and calves. Affection was expressed through regular attention—guiding, watching, feeding and bringing them home safely. Care was not performed only when it was interesting or when praise was available.",
        "Responsibility is love made dependable through repeated action.",
        "Choose duties that match the child’s age and teach the complete task before expecting independence. Reminders and visual routines build skill; ridicule does not.",
        listOf("What could happen if daily care is forgotten?", "How is liking an animal different from caring for it?", "Which jobs in our home protect others?", "What helps us remember a repeated duty?"),
        "Care Calendar",
        listOf("Choose one real care task for each family member.", "Define exactly when and how it is complete.", "Track consistency for seven days, then celebrate reliability rather than perfection."),
        "Use pictures for each step and keep the task short enough to complete successfully.",
        "Let teens choose among meaningful duties and experience natural, safe consequences for forgotten work."
    ),
    ParentingValuesSession(
        "responsibility-govardhan", "Responsibility", "Everyone Beneath Govardhan",
        "When the storm threatened Vrindavan, Krishna lifted Govardhan to create shelter for the community and animals. The people did not treat safety as one person’s problem; they gathered, protected one another and endured the long rain together.",
        "Responsibility grows from asking, “What is mine to contribute to our shared safety and wellbeing?”",
        "Avoid turning responsibility into endless obedience. Explain the reason behind family duties and invite children to notice burdens adults may not see.",
        listOf("Who needed shelter in the story?", "Why do communities share difficult work?", "What burden at home is carried by one person?", "How can strength make others safer?"),
        "Lift the Family Hill",
        listOf("Write recurring household burdens on paper stones.", "Place each stone under the name of the person currently carrying it.", "Redistribute one or two tasks fairly and review the plan after a week."),
        "Use only three simple tasks and let the child choose one.",
        "Discuss invisible labour, fairness and how school pressure affects the duties each person can reasonably carry."
    ),
    ParentingValuesSession(
        "responsibility-arjuna", "Responsibility", "Arjuna Faces the Duty He Wants to Avoid",
        "At Kurukshetra, Arjuna sees the pain connected with his duty and lowers his bow. Krishna does not shame the hesitation. He helps Arjuna examine fear, compassion, consequence and responsibility before deciding. The lesson is not that every command must be obeyed; difficult duty requires discernment.",
        "Responsibility means facing what is yours to do while still questioning whether the action is right.",
        "Help children distinguish duty from adult convenience. A duty should be age-appropriate, ethical, clearly explained and open to questions—especially when safety is involved.",
        listOf("Why did Arjuna hesitate?", "When is hesitation wise?", "How can we tell a duty from unfair pressure?", "Who can help when two responsibilities conflict?"),
        "Duty or Pressure?",
        listOf("Create cards describing chores, helping a friend cheat, reporting danger and keeping an unsafe secret.", "Sort them into responsibility, unfair pressure or ask a trusted adult.", "Discuss why each card belongs where it does."),
        "Use familiar duties like packing a school bag and make clear that unsafe secrets must be told.",
        "Explore conflicts between study, family expectations, friendship and personal boundaries without assuming one automatic answer."
    ),

    ParentingValuesSession(
        "compassion-fruit-vendor", "Compassion", "The Fruit Vendor’s Generous Heart",
        "A fruit vendor responded warmly to little Krishna’s small handful of grains. The exchange is remembered because generosity was not calculated like a business transaction. A modest gift, received with affection, became abundant in meaning.",
        "Compassion notices the heart behind what another person can offer.",
        "Do not force children to give away cherished possessions. Model generosity, offer meaningful choices and teach that helping should respect the recipient’s real need.",
        listOf("Why was the small offering meaningful?", "Can a gift be expensive but thoughtless?", "How can we give without making someone feel small?", "What can children share besides money?"),
        "The Generosity Menu",
        listOf("List ways to give time, attention, skill, food and belongings.", "Each person chooses one action they can offer freely this week.", "Afterward, discuss the relationship created—not praise received."),
        "Offer two concrete choices: share a snack or help with a task.",
        "Discuss consent, sustainable giving and why helping does not mean ignoring personal boundaries."
    ),
    ParentingValuesSession(
        "compassion-kubja", "Compassion", "Krishna Sees Kubja’s Dignity",
        "Kubja was accustomed to being noticed through appearance and limitation. Krishna met her with attention rather than ridicule, and the encounter is remembered as one of recognition and transformation. Before any outward change, someone overlooked was treated as worthy of presence.",
        "Compassion sees a whole person before a body, label, status or difference.",
        "Avoid teaching pity. Disability, appearance and social difference do not make a person an object of charity. Emphasise access, inclusion, friendship and asking before helping.",
        listOf("How is pity different from respect?", "What labels prevent us from seeing a whole person?", "Why should we ask before helping?", "How can a group become more inclusive?"),
        "Change the Environment",
        listOf("Choose a familiar place such as home, school or playground.", "Identify one barrier that makes participation harder for someone.", "Design a respectful change that improves access without singling anyone out."),
        "Use examples about including everyone in a game and asking, “Would you like help?”",
        "Discuss ableist jokes, appearance-based content online and the difference between visibility and tokenism."
    ),
    ParentingValuesSession(
        "compassion-draupadi", "Compassion", "Do Not Become a Silent Spectator",
        "During Draupadi’s humiliation, many powerful people in the assembly failed to stop what was happening. Her story asks not only why wrongdoers acted, but why witnesses allowed dignity to be attacked. Compassion sometimes needs a voice, a report and protective action.",
        "Empathy feels another person’s pain; moral courage helps make them safer.",
        "Keep the description age-appropriate and focus on humiliation, bystander responsibility and seeking safe help. Never tell a child to confront danger alone.",
        listOf("Why do witnesses sometimes stay silent?", "What can a bystander do safely?", "When should an adult be involved immediately?", "How can we support someone after humiliation?"),
        "Bystander Action Ladder",
        listOf("Write four safe actions: distract, include, document appropriately and report.", "Practise matching an action to imaginary school or online situations.", "Add the trusted adults and reporting routes available to your family."),
        "Practise one short phrase: “Stop. That is not kind,” followed by finding an adult.",
        "Include cyberbullying, privacy and the difference between documenting harm for help and reposting it for attention."
    ),

    ParentingValuesSession(
        "courage-kaliya", "Courage", "Krishna Protects the Yamuna",
        "Kaliya’s poison made the river dangerous for people and animals. Krishna entered the danger, stopped the harm and restored safety. The story’s courage is not thrill-seeking; it is strength directed toward protecting life and the shared environment.",
        "Courage is not the absence of fear. It is wise action for what matters despite fear.",
        "Teach the difference between brave and reckless. Children should not enter physical danger; their courageous action is often to move away, warn others and call a capable adult.",
        listOf("Who was affected by the poisoned river?", "What makes courage protective rather than reckless?", "When should we act and when should we call help?", "What shared place needs our care?"),
        "Brave, Risky or Wise?",
        listOf("Name situations such as reporting smoke, entering deep water or telling an adult about bullying.", "Sort each as brave-and-wise, risky or requiring expert help.", "Create one family safety sentence for emergencies."),
        "Use simple safety examples and repeat: move away, tell an adult, stay available.",
        "Discuss how dares and viral challenges can disguise recklessness as courage."
    ),
    ParentingValuesSession(
        "courage-rukmini", "Courage", "Rukmini Makes Her Voice Heard",
        "When decisions about Rukmini’s future were being shaped by powerful others, she sent a clear message expressing her own choice and asking for help. Krishna listened and treated her voice as meaningful rather than deciding that silence meant agreement.",
        "Courage includes expressing a clear choice and respecting another person’s agency.",
        "Use this story to reinforce consent and safe disclosure. Children should know they may say no to unwanted touch, pressure or secrecy and tell another trusted adult if the first person does not listen.",
        listOf("Why was sending the message courageous?", "How can we know whether someone agrees?", "What should we do when a clear no is ignored?", "How can we help without taking control?"),
        "The Trusted Circle",
        listOf("Draw the child in the centre of a page.", "Add at least three safe adults they can contact at home, school or community.", "Practise one sentence for asking help and what to do if the first adult cannot help."),
        "Keep the lesson concrete: your body belongs to you, and unsafe secrets must be told.",
        "Discuss relationship pressure, digital consent, image sharing and how support can preserve the other person’s choice."
    ),
    ParentingValuesSession(
        "courage-gita", "Courage", "Arjuna Chooses After Questioning",
        "Arjuna does not receive a short command and immediately obey. He questions, listens, examines and finally chooses. Krishna’s guidance strengthens discernment rather than replacing it. Courage emerges after the conflict has been honestly explored.",
        "Moral courage combines a steady voice, thoughtful questions and willingness to act on a considered value.",
        "Welcome respectful disagreement. If adults punish every question, children may learn obedience but not discernment—and may remain silent when an instruction is unsafe or unethical.",
        listOf("Why did Krishna allow Arjuna to ask so many questions?", "How is questioning different from disrespect?", "What values help us choose under pressure?", "When have you acted even though your voice shook?"),
        "Family Council",
        listOf("Choose one small real family decision with two reasonable options.", "Let each person name facts, feelings, values and consequences without interruption.", "Choose together where possible, explain the final decision and review what happened later."),
        "Use a simple choice such as the weekend activity and let the child speak first.",
        "Use a genuine decision involving time, responsibility or shared resources so participation is meaningful rather than pretend."
    )
)

fun parentingValuesSession(id: String?): ParentingValuesSession =
    parentingValuesSessions.firstOrNull { it.id == id } ?: parentingValuesSessions.first()
