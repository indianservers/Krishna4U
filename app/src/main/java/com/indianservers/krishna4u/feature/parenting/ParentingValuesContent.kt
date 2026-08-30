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
    val teensTip: String,
    val teenFocused: Boolean = false,
    val rolePlay: List<String> = emptyList(),
    val weeklyAction: String = ""
) {
    fun facilitationTip(readingMode: String): String =
        if (readingMode == "kids") kidsTip else teensTip

    fun rolePlaySteps(): List<String> = rolePlay.ifEmpty {
        listOf(
            "The parent plays the young person and the child or teen plays the parent.",
            "Act out the difficult moment using calm words. Then switch roles and try again.",
            "Each person names what felt difficult and one response that made the conversation safer."
        )
    }

    fun actionForThisWeek(): String = weeklyAction.ifBlank {
        "Choose one lesson from this session and practise it together once each day for seven days. Parents participate too."
    }
}

private fun parentTeenSession(
    id: String,
    value: String,
    title: String,
    story: String,
    lesson: String,
    parentNote: String,
    prompts: List<String>,
    activityTitle: String,
    activitySteps: List<String>,
    rolePlay: List<String>,
    weeklyAction: String
) = ParentingValuesSession(
    id = id,
    value = value,
    title = title,
    story = story,
    lesson = lesson,
    parentNote = parentNote,
    conversationPrompts = prompts,
    activityTitle = activityTitle,
    activitySteps = activitySteps,
    kidsTip = "This session is designed for teenagers. With younger children, use only the simple safety or respect message and avoid details they do not need.",
    teensTip = "Let the teen speak first. Ask questions before giving advice, avoid sarcasm and agree on limits that protect both safety and growing independence.",
    teenFocused = true,
    rolePlay = rolePlay,
    weeklyAction = weeklyAction
)

private fun expandedValueSession(
    id: String,
    value: String,
    title: String,
    story: String,
    lesson: String,
    parentNote: String,
    prompts: List<String>,
    activityTitle: String,
    activitySteps: List<String>,
    rolePlay: List<String>,
    weeklyAction: String
) = ParentingValuesSession(
    id = id,
    value = value,
    title = title,
    story = story,
    lesson = lesson,
    parentNote = parentNote,
    conversationPrompts = prompts,
    activityTitle = activityTitle,
    activitySteps = activitySteps,
    kidsTip = "Keep the story concrete, let the child answer first and practise the value through one small action rather than a long lecture.",
    teensTip = "Connect the value to a real choice involving school, friends, home or technology. Welcome respectful disagreement and discuss consequences without shame.",
    rolePlay = rolePlay,
    weeklyAction = weeklyAction
)

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
    ),

    parentTeenSession(
        "teen-privacy-secrecy", "Trust", "Privacy Is Healthy; Unsafe Secrecy Is Not",
        "A teenager closes a journal when a parent enters. The parent feels shut out and wants to read it. Later, the teen receives a threatening message and is afraid to tell anyone. These are not the same situation. A private journal protects inner space; a secret involving danger, abuse, blackmail or self-harm needs safe adult help.",
        "Trust grows when parents respect ordinary privacy and teenagers speak when safety is at risk.",
        "Do not demand complete access as proof of love. Explain the limited situations in which safety requires adult involvement, and promise to respond calmly when a teen asks for help.",
        listOf("What parts of life deserve privacy?", "Which secrets can place someone in danger?", "When may a parent step in for safety?", "Which trusted adults can the teen contact?"),
        "Privacy and Safety Agreement",
        listOf("List private spaces the family will respect, such as journals and ordinary conversations.", "List safety situations that must be shared with a trusted adult.", "Agree how a parent will explain any necessary safety check."),
        listOf("The teen asks for privacy after receiving an ordinary message; practise a respectful parent response.", "Then role-play a threatening message and practise asking a trusted adult for help.", "Switch roles and identify what made trust easier in each scene."),
        "For seven days, parents knock before entering and teenagers share one concern that genuinely needs support."
    ),
    parentTeenSession(
        "teen-friendships-relationships", "Relationships", "Guide Relationships Without Taking Control",
        "A teenager becomes close to someone the family does not know well. The parent reacts with accusations, and the teen stops sharing. Krishna’s way of guiding Arjuna offers another model: listen, ask, explain consequences and leave room for responsible choice instead of using fear as the only teacher.",
        "A safe relationship allows respect, consent, honest boundaries and continued connection with trusted people.",
        "Avoid insulting the friend or romantic interest. Criticise specific unsafe behaviour, not a person’s identity, and keep the door open so the teenager can seek help without expecting humiliation.",
        listOf("What makes a friendship feel safe?", "Which behaviours show control rather than care?", "How can a teen disagree with a friend?", "What would make it easier to ask a parent for help?"),
        "Green, Yellow and Red Signs",
        listOf("List green signs such as respect and honest apology.", "List yellow signs such as pressure or constant checking.", "List red signs such as threats, isolation, violence or sharing private images."),
        listOf("One person plays a friend pressuring the other to cancel family plans.", "Practise a clear boundary and a respectful response.", "Switch roles, then practise contacting a trusted adult when the boundary is ignored."),
        "Have one fifteen-minute conversation about relationships in which the parent asks questions and gives no lecture."
    ),
    parentTeenSession(
        "teen-social-media", "Digital Dharma", "Use Social Media Without Losing Yourself",
        "A student posts a joke about a classmate and receives quick attention. By evening the post has travelled far beyond the original group, and the classmate is deeply hurt. Digital actions may feel distant, but dharma still asks who is protected, who is harmed and whether we would say the same words face to face.",
        "Online freedom carries responsibility for truth, privacy, attention and another person’s dignity.",
        "Rules work better when adults follow them too. Discuss algorithms, edited images, permanent sharing and sleep honestly rather than describing every platform as worthless.",
        listOf("Would you post this if the person were beside you?", "What information should remain private?", "How can attention change judgment?", "What should happen after an online mistake?"),
        "The Dharma Pause",
        listOf("Before posting, ask: Is it true, necessary, kind and safe?", "Review privacy and reporting controls together.", "Choose one phone-free family time that adults also follow."),
        listOf("Role-play receiving a humiliating group message about a classmate.", "Practise refusing to forward it, supporting the person and reporting serious harm.", "Switch roles and compare silence, public argument and safe action."),
        "Use the four-question Dharma Pause before every non-routine post for one week."
    ),
    parentTeenSession(
        "teen-career-pressure", "Purpose", "A Career Is a Path, Not a Measure of Worth",
        "A teenager loves design, while the family strongly prefers medicine. Every discussion becomes a battle between fear and identity. Krishna teaches that one’s own path, followed sincerely, is better than copying another path perfectly. This does not remove practical planning; it asks that ability, values and reality be examined together.",
        "Career guidance should join self-knowledge, honest preparation and practical responsibility—not comparison or family prestige.",
        "Share financial and practical concerns without using love, sacrifice or shame as pressure. Help the teen research several paths and speak with qualified people in those fields.",
        listOf("Which work gives the teen energy?", "What skills need evidence, not only interest?", "Which family fears are practical?", "What experiments could test a career idea safely?"),
        "Three Possible Paths",
        listOf("Choose three realistic options rather than one dream versus one demand.", "For each, list training, cost, daily work, opportunities and backup routes.", "Agree on one small experiment such as a course, project or professional conversation."),
        listOf("The parent expresses a practical career concern without comparison or threat.", "The teen responds with evidence, questions and a proposed experiment.", "Switch roles and identify which words opened thought instead of defence."),
        "Complete one real career experiment this week and discuss what it taught without demanding a final decision."
    ),
    parentTeenSession(
        "teen-marks-comparison", "Confidence", "Marks Give Information, Not Identity",
        "Two students receive different results. One is praised as brilliant; the other hears comparisons all evening. The lower mark may reveal a study gap, but repeated comparison turns useful information into shame. Krishna’s teaching directs attention toward sincere action rather than allowing one result to own the mind.",
        "Review preparation, method and support while protecting the teenager’s basic worth.",
        "Begin with regulation and listening. Ask what the teen thinks happened before prescribing a solution. Praise honest effort and improved method, not only rank or natural ability.",
        listOf("What does this mark actually tell us?", "Which part of preparation worked?", "What support is missing?", "How does comparison affect learning?"),
        "Result-to-Plan Sheet",
        listOf("Write facts about the result without labels such as lazy or brilliant.", "Choose one method to keep and one to change.", "Set a small review date and identify help the parent will provide."),
        listOf("The parent receives a disappointing result and first responds with curiosity.", "The teen explains effort, difficulty and one needed support.", "Switch roles and compare this with a response based on shouting or comparison."),
        "For one week, nobody in the family will compare one child’s marks, body or future with another person’s."
    ),
    parentTeenSession(
        "teen-independence", "Independence", "Freedom Grows With Responsibility",
        "A teenager asks to travel with friends. The parent sees every danger; the teen sees only mistrust. Independence cannot arrive in one argument. Like Arjuna learning to choose after questioning, freedom grows through information, responsibility, demonstrated judgment and plans for what may go wrong.",
        "Healthy independence expands through trust, clear limits and accountable choices.",
        "Do not make age the only test. Name the exact safety concerns, invite a plan and offer a smaller step when full permission is not yet reasonable.",
        listOf("What responsibility should come with this freedom?", "Which risks are real and which are imagined?", "How will plans change if something goes wrong?", "What behaviour builds trust?"),
        "Freedom Ladder",
        listOf("Choose one freedom the teen wants.", "Define the skills, information and communication it requires.", "Agree on a smaller first step and a date to review how it went."),
        listOf("The teen requests a new freedom with a complete safety plan.", "The parent names concerns and negotiates conditions without ridicule.", "Switch roles and practise responding if the agreed plan changes unexpectedly."),
        "Complete one agreed independence step and review it using facts rather than blame."
    ),
    parentTeenSession(
        "teen-late-phone", "Balance", "Let the Mind Rest from the Phone",
        "A teenager says one more video will help them relax, but sleep moves later each night and mornings become painful. The phone is not an enemy; unplanned use is taking control of rest. Krishna’s teaching on balance in food, sleep, work and recreation offers a practical measure.",
        "Technology should serve life without stealing sleep, attention, duty or real connection.",
        "Parents should examine their own late-night use before setting rules. Focus on sleep and functioning rather than moral panic, and distinguish homework or emergency contact from endless scrolling.",
        listOf("How does late use affect the next morning?", "What makes stopping difficult?", "Where can phones rest safely?", "What evening activity helps the mind settle?"),
        "Family Charging Place",
        listOf("Choose a shared place outside bedrooms for overnight charging.", "Agree on a realistic stopping time for adults and teenagers.", "Create exceptions for genuine needs and review sleep after seven days."),
        listOf("The parent asks for the phone at the agreed time without shouting.", "The teen explains an unfinished need and proposes a clear end time.", "Switch roles and practise keeping the agreement the following night."),
        "For seven nights, all participating family members place phones in the agreed charging place."
    ),
    parentTeenSession(
        "teen-consent-safety", "Safety", "A Clear No Must Be Respected",
        "A young person feels pressured to share an image, accept touch or keep an uncomfortable interaction secret. Like Rukmini making her choice known, every person’s voice matters. Silence, fear, freezing or past agreement does not give someone permanent permission.",
        "Consent must be informed, freely given, specific, reversible and respected without punishment.",
        "Use clear words without creating shame. Promise that asking for help will not lead to blame. If abuse, exploitation or immediate danger is disclosed, prioritise safety and qualified support rather than conducting your own interrogation.",
        listOf("What does free agreement sound like?", "Can someone change their mind?", "What can a person do if they freeze?", "Which adults and services can help safely?"),
        "Safety Words and Trusted People",
        listOf("Practise three phrases: No; I am leaving; I need help.", "Name at least three trusted adults or services.", "Agree on a family code word for requesting help without explaining in public."),
        listOf("One person applies pressure and the other practises a clear refusal and exit.", "Practise helping a friend without taking away their choices.", "Switch roles, then rehearse contacting a trusted adult when a no is ignored."),
        "Review the trusted-person list and family safety code once this week without waiting for a crisis."
    ),
    parentTeenSession(
        "teen-substance-pressure", "Self-control", "Courage Can Say No and Leave",
        "At a gathering, a friend offers alcohol, tobacco, vaping or drugs and says refusal proves weakness. Real courage is not surrendering judgment to the crowd. Protecting the mind and body may require a simple no, leaving the place and calling someone safe.",
        "A prepared exit plan is stronger than hoping good judgment will appear under pressure.",
        "Avoid only using threats and horror. Explain real health, legal and safety risks, make pickup available without immediate public punishment, and seek qualified help if use has already become repeated or dependent.",
        listOf("Which words create pressure?", "What can the teen say without debating?", "Who can provide a safe ride?", "How should a parent respond when help is requested?"),
        "No-Questions-First Safety Plan",
        listOf("Choose a code message the teen can send when they need pickup.", "List two safe adults and transport options.", "Agree that safety comes first; the full conversation happens later when everyone is calm."),
        listOf("Role-play a friend repeatedly offering a harmful substance.", "Practise saying no, changing location and sending the safety code.", "Switch roles and practise the parent’s calm pickup response."),
        "Memorise and test the safety code once this week, including who will respond and how."
    ),
    parentTeenSession(
        "teen-respectful-disagreement", "Communication", "Disagreement Does Not Cancel Respect",
        "A parent and teenager disagree about a family rule. Both begin speaking louder because each believes respect means being obeyed. Krishna allowed Arjuna to question deeply before choosing. Respectful guidance makes room for facts, feelings and reasons while keeping safety and responsibility clear.",
        "Respect is shown through honest listening and clean speech, not forced agreement or silent resentment.",
        "Do not label every disagreement as disrespect. Set limits on insults, threats and shouting, but allow the teen to question a rule and propose an alternative.",
        listOf("What is the concern beneath each position?", "Which part of the rule protects safety?", "What alternative could meet the same need?", "How can either person request a pause?"),
        "The Twenty-Minute Council",
        listOf("Give each person two uninterrupted minutes to explain facts, feelings and needs.", "Each repeats the other person’s point before responding.", "Agree, negotiate or explain the final boundary, then choose a review date."),
        listOf("Choose a small real disagreement and practise speaking without labels or old complaints.", "Use the sentence: I understand your concern; my concern is; my proposal is.", "Switch positions and argue the other person’s view fairly before returning to your own."),
        "Use the family council once this week for a genuine disagreement and record one point learned from the other person."
    ),

    expandedValueSession(
        "respect-sandipani", "Respect", "Krishna Honours His Teacher",
        "Krishna and Balarama studied with Sage Sandipani. Though Krishna possessed extraordinary wisdom, he entered the teacher’s home as a student, listened, learned and later expressed gratitude through service. His strength did not make courtesy unnecessary; true greatness remained willing to learn.",
        "Respect means recognising dignity, listening sincerely and behaving with care—even when we are talented, confident or in disagreement.",
        "Teach mutual respect, not one-way obedience. Adults should model listening, apology and clean speech while maintaining age-appropriate boundaries.",
        listOf("How did Krishna show respect while learning?", "Can we disagree respectfully?", "What makes a person feel heard?", "How should adults show respect to children?"),
        "The Respect Check",
        listOf("Each family member names one behaviour that helps them feel respected.", "Choose two shared rules for listening and speech.", "Practise disagreeing with one idea without insulting the person."),
        listOf("The child questions a family rule using a calm voice.", "The parent listens, explains the reason and considers one fair change.", "Switch roles and identify what respect sounded like from both sides."),
        "For seven days, nobody interrupts the first minute when another family member is explaining a concern."
    ),
    expandedValueSession(
        "gratitude-sudama", "Gratitude", "Krishna Receives Sudama’s Simple Gift",
        "Sudama visited Krishna carrying a very simple offering. Krishna did not measure the gift by price or social status. He welcomed his friend warmly and received what was given with love. The meeting shows gratitude for intention, relationship and effort rather than display.",
        "Gratitude notices the people, labour and care behind what we receive and turns thanks into responsible action.",
        "Do not force grateful words immediately after disappointment. Model specific thanks and let children see adults care for gifts, food, time and relationships.",
        listOf("Why was Sudama’s small gift meaningful?", "What work is hidden behind an ordinary meal?", "How is gratitude different from owing someone?", "How can thanks become action?"),
        "The Hidden Hands Map",
        listOf("Choose one everyday item such as food, clothing or a book.", "List the people and natural resources that helped it reach the family.", "Thank one person and choose one way to avoid wasting the item."),
        listOf("One person offers a small handmade gift.", "The receiver practises noticing effort rather than price.", "Switch roles and compare a distracted response with a grateful one."),
        "Each family member will give one specific thank-you and prevent one form of waste every day this week."
    ),
    expandedValueSession(
        "self-control-pause", "Self-control", "The Pause Before the Reply",
        "A child receives an insulting message and begins typing a cruel reply. Before sending it, they remember that anger can cloud judgment. They place the phone down, breathe, show the message to a trusted adult and later answer firmly without creating a second wound.",
        "Self-control does not erase emotion; it creates enough space for values to choose the response.",
        "Children borrow regulation from adults. Lower your voice, name the feeling and delay serious decisions until bodies are calm.",
        listOf("What happens inside the body before an angry reaction?", "Which pause helps you most?", "When should a message not receive an answer?", "How can a firm reply avoid cruelty?"),
        "Build a Pause Plan",
        listOf("Choose three calming actions such as water, breathing or walking.", "Write a sentence for requesting a pause.", "Decide which situations must be shown to a trusted adult."),
        listOf("One person delivers an irritating comment.", "The other notices the body signal, asks for a pause and returns with clean words.", "Switch roles and practise accepting another person’s request for time."),
        "Every family member will use the agreed pause sentence at least once before reacting this week."
    ),
    expandedValueSession(
        "patience-peace-mission", "Patience", "Krishna Tries the Path of Peace",
        "Before the Mahabharata war, Krishna went as a peace messenger and tried to prevent destruction through dialogue. Patience did not mean pretending injustice was acceptable. It meant giving a peaceful path a sincere chance before accepting that stronger protective action might be necessary.",
        "Patience is steady effort guided by wisdom; it is neither passive waiting nor accepting repeated harm.",
        "Help children distinguish waiting for growth from tolerating danger. Use visual time, predictable follow-up and small steps when a skill takes practice.",
        listOf("Why did Krishna try dialogue first?", "When does patience help a problem?", "When does waiting become unsafe?", "What can we do while a result takes time?"),
        "Wait with a Plan",
        listOf("Choose one family goal that needs time.", "Name the useful action possible today and the date for review.", "Identify the condition that would require asking for more help."),
        listOf("One person wants an immediate answer to a difficult request.", "The other explains what needs time and gives a clear review date.", "Switch roles and practise waiting without repeated pressure."),
        "Choose one slow family goal and complete one small step toward it on three days this week."
    ),
    expandedValueSession(
        "discipline-arjuna", "Discipline", "Arjuna Trains His Attention",
        "Arjuna’s skill did not appear only during a great battle. It grew through repeated practice, correction and focused attention before the moment of need. Discipline made ability dependable. The lesson is not endless work, but returning faithfully to a meaningful practice.",
        "Discipline is a supportive structure that helps intention become reliable action.",
        "Use routines, visible steps and realistic expectations. Avoid calling punishment discipline or demanding performance without sleep, play and recovery.",
        listOf("Why is practice needed before a difficult moment?", "What makes a routine realistic?", "How should we respond after missing one day?", "What is the difference between discipline and punishment?"),
        "The Small Practice",
        listOf("Choose one skill or duty that matters.", "Set a small daily time, place and clear finish point.", "Prepare the materials beforehand and review after seven days."),
        listOf("The child misses the agreed practice.", "The parent responds by checking the plan instead of attacking character.", "Switch roles and redesign the routine so returning is easier."),
        "Complete one chosen practice for ten focused minutes on five days without using shame after a missed day."
    ),
    expandedValueSession(
        "friendship-sudama", "Friendship", "Krishna Welcomes Sudama",
        "When Sudama came to see Krishna after many years, difference in wealth did not erase their friendship. Krishna welcomed him with warmth and dignity. The story remembers a bond that was not based on popularity, usefulness or matching social position.",
        "True friendship respects dignity, keeps trust and remains caring when status or circumstances change.",
        "Avoid choosing friends for children only by marks, family status or convenience. Help them recognise loyalty, consent, honesty and mutual effort.",
        listOf("What made Krishna’s welcome meaningful?", "How do friends protect each other’s dignity?", "What should remain private between friends?", "When does loyalty need a boundary?"),
        "Friendship Qualities",
        listOf("List five qualities of a safe friend.", "Circle the qualities each person also needs to practise.", "Choose one friendship that could be strengthened through a sincere action."),
        listOf("One friend needs listening after a bad day.", "Practise listening before advice and keeping the focus on their need.", "Switch roles and practise setting a boundary when a friend asks for something wrong."),
        "Contact one friend this week to listen, help or express appreciation without asking for anything in return."
    ),
    expandedValueSession(
        "forgiveness-repair", "Forgiveness", "After Hurt, Choose Repair Without Losing Wisdom",
        "Two cousins exchange cruel words during a game. One apology is offered quickly, but the hurt remains. The family helps them name what happened, repair the damage and agree on a boundary. Forgiveness is allowed to grow; it is not forced as a performance.",
        "Forgiveness releases revenge, while accountability and boundaries protect everyone from repeated harm.",
        "Do not force physical affection or immediate forgiveness. Require repair from the person who caused harm and allow trust to rebuild through behaviour.",
        listOf("What is a complete apology?", "Can we forgive and still keep a boundary?", "How is forgiveness different from pretending?", "What behaviour helps trust return?"),
        "The Repair Path",
        listOf("Name the action and its effect without excuses.", "Offer a specific repair and ask what the harmed person needs.", "Agree on one boundary that reduces repeated harm."),
        listOf("One person gives an incomplete apology that includes an excuse.", "Try again with truth, empathy and repair.", "Switch roles and practise accepting an apology without promising instant trust."),
        "This week, repair one unresolved small hurt through a sincere apology, action and respected boundary."
    ),
    expandedValueSession(
        "humility-charioteer", "Humility", "Krishna Chooses to Be Arjuna’s Charioteer",
        "Krishna stood beside Arjuna not as the warrior seeking attention, but as charioteer and guide. The role involved service, listening and responsibility. His greatness did not depend on occupying the most celebrated position; purpose mattered more than display.",
        "Humility is clear strength without the need to place others below us or demand attention for every contribution.",
        "Do not teach humility as silence, low confidence or accepting disrespect. Let children celebrate growth while thanking help and remaining teachable.",
        listOf("Why might service require strength?", "Can we feel proud and remain humble?", "How do we give credit fairly?", "When does attention become more important than the work?"),
        "Share the Credit",
        listOf("Choose one recent family success.", "Name every person and hidden task that contributed.", "Thank someone whose work was easy to overlook."),
        listOf("One person receives praise for a group achievement.", "Practise accepting the praise and naming other contributions.", "Switch roles and practise celebrating someone without comparing yourself."),
        "Each person will complete one useful task this week without announcing it or seeking praise."
    ),
    expandedValueSession(
        "digital-responsibility", "Digital responsibility", "Dharma Still Applies Behind a Screen",
        "A child forwards a dramatic clip without checking whether it is true. Another person’s reputation is harmed before the mistake is discovered. A screen can make consequences feel distant, but truth, privacy and dignity still matter whenever a message is created or shared.",
        "Digital responsibility means pausing before posting, checking facts, protecting privacy and refusing to use attention as permission to harm.",
        "Create rules with children and follow them as adults. Teach reporting, privacy and repair instead of relying only on surveillance.",
        listOf("How can we verify a digital claim?", "Whose permission is needed before sharing an image?", "What makes a joke harmful?", "How should an online mistake be repaired?"),
        "The Four Digital Questions",
        listOf("Ask whether a post is true, necessary, kind and safe.", "Review one privacy setting together.", "Delete or correct one item that should not remain shared."),
        listOf("One person asks to post a family photo.", "Practise asking consent and accepting no without pressure.", "Switch roles and practise correcting a false message already shared."),
        "Use the four digital questions before every post or forward for seven days; adults participate too."
    ),
    expandedValueSession(
        "money-contentment", "Money and contentment", "Enough Is Different from More",
        "A family sees a new item advertised and each person begins to feel that the current one is worthless. They pause, compare need with desire and remember the work, time and resources behind money. They choose carefully instead of buying only to escape comparison.",
        "Contentment appreciates what is enough while money is used honestly for needs, responsibilities, generosity and thoughtful enjoyment.",
        "Do not romanticise financial hardship or shame reasonable wishes. Share age-appropriate facts and involve children in choices without placing adult financial anxiety on them.",
        listOf("What is the difference between need and desire?", "What does a purchase cost besides money?", "Can we enjoy something without owning it?", "How can money serve another person?"),
        "Spend, Save, Share",
        listOf("Use an imaginary or small real amount of money.", "Divide it among spending, saving and sharing.", "Explain each choice and what value it protects."),
        listOf("A child asks for an expensive item because friends have it.", "The parent explains the decision without shame or comparison.", "Switch roles and propose a saving or alternative-use plan."),
        "Delay one nonessential family purchase for seven days and discuss whether it is still worth the cost."
    ),
    expandedValueSession(
        "care-elders", "Care for elders", "Honour Elders Through Presence and Practical Care",
        "After Kamsa’s fall, Krishna and Balarama returned care and respect to their parents and restored elder Ugrasena’s place. The wider tradition remembers strength being used to repair family dignity rather than to claim every position for oneself.",
        "Care for elders includes listening, practical help and dignity, while safety and healthy boundaries still matter.",
        "Do not make children responsible for medical or emotional burdens beyond their age. Care should be shared fairly and should never require tolerating abuse.",
        listOf("What kinds of help preserve dignity?", "Why should we ask before helping?", "Which duties belong to adults?", "How can we learn from an elder’s experience?"),
        "An Elder’s Story",
        listOf("Invite an elder to share one memory or skill.", "Listen without correcting small details or rushing.", "Offer one specific, appropriate form of help they actually want."),
        listOf("An elder needs help with a simple task but values independence.", "Practise asking what help is wanted instead of taking over.", "Switch roles and notice how tone affects dignity."),
        "Spend twenty undistracted minutes with an elder this week and complete one requested act of care."
    ),
    expandedValueSession(
        "care-animals-nature", "Care for animals and nature", "Protection Is Part of Belonging",
        "Krishna’s Vrindavan life is filled with cows, forests, rivers and Govardhan. These were not merely decorations around human life; the community depended on them and carried responsibility toward them. Love for nature became care, restraint and shared protection.",
        "Animals and nature are living responsibilities, not objects for amusement, waste or careless use.",
        "Teach safe, informed care. Do not encourage children to approach unknown animals, handle wildlife or enter dangerous places without trained adult guidance.",
        listOf("What does an animal need besides affection?", "Where does our water and food come from?", "How does waste affect another life?", "Which environmental action is safe for our family?"),
        "Care for One Place",
        listOf("Choose a safe nearby place, plant or animal-care need.", "Learn what responsible help actually requires.", "Complete one action without disturbing wildlife or creating more waste."),
        listOf("A child wants to feed an unknown animal unsuitable food.", "Practise pausing and asking a knowledgeable adult first.", "Switch roles and plan a safe act of care."),
        "For seven days, reduce one form of waste and complete one safe action for an animal, plant or shared place."
    ),
    expandedValueSession(
        "handling-failure", "Handling failure", "A Result Is a Teacher, Not a Name",
        "A child practises for a competition and is not selected. The result hurts, and the mind quickly says, ‘I am not good at anything.’ The family allows disappointment, then studies the preparation, asks for useful feedback and chooses whether to practise again or follow another path.",
        "Failure describes an outcome; it does not define worth. Honest review can turn pain into skill, resilience and wiser direction.",
        "Do not rush to false praise or immediate solutions. Let the child grieve, then examine method and support without comparison.",
        listOf("What part of this result hurts most?", "What did the effort teach?", "Which feedback is useful?", "Should the next step be retry, adjust or release?"),
        "Result, Lesson, Next Step",
        listOf("Write only the facts of what happened.", "Name one method that helped and one that needs change.", "Choose a small next action or a thoughtful decision to stop."),
        listOf("The child shares a disappointing result.", "The parent listens before advising and asks what support is wanted.", "Switch roles and compare curiosity with comparison or blame."),
        "After one setback this week, every family member will name a lesson and one next step without using identity labels."
    ),
    expandedValueSession(
        "healthy-boundaries", "Healthy boundaries", "A Loving No Can Protect What Matters",
        "A relative repeatedly enters a child’s room, reads messages and dismisses requests for space as disrespect. The family learns that love does not remove every boundary. Privacy, rest, body autonomy and clean communication help relationships remain safe rather than distant.",
        "A boundary clearly states what protects safety, dignity or wellbeing; it is not a threat used to control another person.",
        "Respect reasonable boundaries from children and explain limits placed for safety. Never teach that family status cancels consent or bodily autonomy.",
        listOf("What does this boundary protect?", "How is a boundary different from punishment?", "What can we do when a no is ignored?", "Which boundaries must adults keep too?"),
        "Boundary Sentence Practice",
        listOf("Use: When this happens, I feel; I need; I will.", "Practise one ordinary boundary such as knocking or quiet study time.", "Name a trusted adult for help when a safety boundary is ignored."),
        listOf("One person asks for space using a clean boundary sentence.", "The other accepts it or negotiates without ridicule.", "Switch roles and practise seeking help if the boundary is repeatedly ignored."),
        "Choose one healthy family boundary, write it clearly and have adults and children follow it for seven days."
    ),
    expandedValueSession(
        "service-generosity", "Service and generosity", "Serve Without Turning Help into a Performance",
        "A family prepares food and useful supplies for people in need. Before taking photographs, they ask whether public attention would protect or reduce dignity. They choose to serve quietly, learn what is actually needed and let the receiver keep choice and respect.",
        "Generosity offers useful care with humility; service should meet a real need without making another person an object of praise.",
        "Do not force children to surrender cherished possessions or use suffering as a lesson display. Offer choices and work with responsible local organisations where appropriate.",
        listOf("Who decides what help is useful?", "Why does dignity matter while giving?", "Can service include time and skill?", "When can sharing a photo cause harm?"),
        "A Family Seva Plan",
        listOf("Choose one real, safe need the family understands.", "Ask what form of help is wanted and divide tasks fairly.", "Complete the service without seeking personal publicity and reflect afterward."),
        listOf("One person offers help that the receiver does not need.", "Practise asking, listening and adjusting the offer.", "Switch roles and notice the difference between pity and respect."),
        "Complete one family act of service this week and discuss the need met rather than the praise received."
    )
)

fun parentingValuesSession(id: String?): ParentingValuesSession =
    parentingValuesSessions.firstOrNull { it.id == id } ?: parentingValuesSessions.first()
