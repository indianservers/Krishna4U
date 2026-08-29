package com.indianservers.krishna4u.feature.decisions

data class DharmaChoice(
    val text: String,
    val consequence: String,
    val dharmaAligned: Boolean
)

data class DharmaDecisionStory(
    val id: String,
    val category: String,
    val title: String,
    val situation: String,
    val question: String,
    val choices: List<DharmaChoice>,
    val krishnaGuidance: String,
    val principle: String,
    val action: String
)

private fun choice(text: String, consequence: String, aligned: Boolean = false) = DharmaChoice(text, consequence, aligned)

val dharmaDecisionStories = listOf(
    DharmaDecisionStory(
        "exam-cheating", "Integrity", "The Answer on the Desk",
        "During an important exam, you notice that the student beside you has left an answer sheet visible. You did not prepare enough, and one copied answer could help you pass. Nobody appears to be watching.",
        "What would you do?",
        listOf(
            choice("Copy only the answer you need; the pressure makes it understandable.", "Pressure explains the temptation, but copying turns another person’s work into a false claim about your own learning."),
            choice("Ignore the sheet, answer honestly and accept the result.", "You protect integrity and receive truthful information about what you need to learn next.", true),
            choice("Copy now and promise yourself that you will study properly next time.", "A future promise cannot make a dishonest present action truthful, and success gained this way makes the habit easier to repeat.")
        ),
        "Krishna teaches that your responsibility is sincere action, not control of the result. A lower honest mark can guide growth; a higher dishonest mark hides the lesson and weakens trust in yourself.",
        "Dharma values honest effort above a result obtained through deception.",
        "Complete the exam honestly, then ask for help creating a realistic study plan."
    ),
    DharmaDecisionStory(
        "bullying", "Courage", "The Corridor Crowd",
        "A group is mocking a younger student’s accent. Your friends are laughing, and the student is trying not to cry. Speaking up may make the group turn against you.",
        "What would you do?",
        listOf(
            choice("Laugh lightly so your friends do not target you.", "Joining the laughter makes the victim less safe and tells the group that humiliation is acceptable."),
            choice("Say nothing and leave; at least you did not participate.", "Leaving avoids direct participation, but the vulnerable student remains alone and responsible adults may never learn what happened."),
            choice("Interrupt safely, stand beside the student and report repeated bullying.", "You protect dignity without escalating the situation and bring in people responsible for safety.", true)
        ),
        "Krishna’s strength repeatedly serves protection. Dharma does not require reckless confrontation, but it does ask that fear not become silent permission for cruelty. Courage can be a clear sentence, a supportive presence or a timely report.",
        "Protect the vulnerable in a way that is brave, safe and effective.",
        "Say, “That is not okay,” move beside the student and involve a trusted adult."
    ),
    DharmaDecisionStory(
        "peer-pressure", "Self-Mastery", "The Party Challenge",
        "At a gathering, friends pressure you to try something unsafe. They say everyone does it and record people who refuse. You want to belong and fear becoming the next joke.",
        "What would you do?",
        listOf(
            choice("Do it once so the group accepts you.", "Belonging purchased by violating your safety gives the group control over your boundaries."),
            choice("Refuse clearly, leave if pressure continues and contact a safe person.", "You protect your wellbeing and show that friendship must respect a clear no.", true),
            choice("Pretend to agree, then secretly switch or hide it.", "Avoiding immediate conflict may feel easier, but it keeps you inside an unsafe situation and invites further pressure.")
        ),
        "Krishna teaches mastery of the senses and steadiness before pressure. Your value is not decided by a group’s approval. A friend may invite you, but a trustworthy friend will not punish your boundary.",
        "Dharma does not ask you to harm yourself in order to belong.",
        "Practise one exit sentence: “No. I’m not doing that. I’m leaving now.”"
    ),
    DharmaDecisionStory(
        "hide-mistake", "Truth", "The Broken Project",
        "You accidentally delete part of a group project shortly before submission. You can blame a software error, and your teammates may never discover what actually happened.",
        "What would you do?",
        listOf(
            choice("Blame the software so everyone stays calm.", "The lie protects you briefly but prevents the group from responding to the real problem and damages trust if discovered."),
            choice("Stay quiet and hope somebody else repairs it.", "Silence transfers the cost of your mistake to people who do not know what needs repair."),
            choice("Tell the group immediately, apologise and help rebuild the work.", "Early truth gives the team the best chance to recover and turns guilt into responsible repair.", true)
        ),
        "Truth is not merely admitting a mistake after every escape closes. Krishna-inspired Dharma joins honesty with action: name what happened, protect others from further harm and participate fully in repair.",
        "A mistake tests responsibility; hiding it multiplies the harm.",
        "Use four sentences: what happened, your responsibility, your apology and your repair plan."
    ),
    DharmaDecisionStory(
        "group-chat-gossip", "Speech", "The Screenshot",
        "A friend sends you a private screenshot that embarrasses a classmate. The group chat is excited, and forwarding it would make you feel included.",
        "What would you do?",
        listOf(
            choice("Forward it only to your closest friend.", "The circle of harm still grows, and private humiliation cannot be recovered once it spreads."),
            choice("Do not forward it, ask the sender to delete it and check on the person affected.", "You interrupt the harm, protect privacy and offer support without adding more public attention.", true),
            choice("Keep it silently in case it becomes useful later.", "Saving private harm as future power treats another person’s dignity as a tool.")
        ),
        "Krishna teaches disciplined speech: truth should be beneficial and should not needlessly wound. Digital actions are still actions. Silence can stop a cruel reply, but active protection may also require reporting serious harassment.",
        "Before sharing, ask whether the content is yours to reveal and whom it may harm.",
        "Delete the screenshot, refuse to forward it and report it if safety or harassment is involved."
    ),
    DharmaDecisionStory(
        "found-money", "Honesty", "The Wallet Without a Witness",
        "You find a wallet containing money and identification near an empty bus stop. You need money for something important, and nobody saw you pick it up.",
        "What would you do?",
        listOf(
            choice("Keep the cash but return the wallet.", "Returning part of what belongs to another person does not make taking the rest honest."),
            choice("Leave it where it is so the decision is not your problem.", "Walking away may expose the owner to greater loss when you have a safe way to help."),
            choice("Secure it and use safe official contact to return everything.", "You protect another person’s property and preserve integrity when no praise is guaranteed.", true)
        ),
        "Dharma is most visible when no audience is present. Krishna’s teaching on selfless action invites you to do what is right without making reward the condition.",
        "What is not yours does not become yours because nobody is watching.",
        "Give the intact wallet to verified authorities or safely contact its owner with an adult’s help."
    ),
    DharmaDecisionStory(
        "lie-for-friend", "Friendship", "The Alibi",
        "A close friend skipped an important responsibility and asks you to lie to a teacher or parent. They say a real friend would protect them.",
        "What would you do?",
        listOf(
            choice("Lie because loyalty means standing together.", "The lie shields the behaviour from accountability and pulls your integrity into the same problem."),
            choice("Refuse to lie, encourage your friend to tell the truth and support the repair.", "You protect both your integrity and your friend’s opportunity to mature.", true),
            choice("Avoid answering and let somebody else be blamed.", "Silence that knowingly redirects blame still allows an innocent person to carry the consequence.")
        ),
        "Krishna’s friendship with Arjuna includes difficult truth, not blind approval. A true friend does not help another person escape every consequence; they help them become strong enough to face truth and repair harm.",
        "Loyalty serves a friend’s character, not every request.",
        "Say, “I will not lie, but I will stand with you while you tell the truth.”"
    ),
    DharmaDecisionStory(
        "stolen-credit", "Fairness", "The Presentation Applause",
        "Your group receives praise for a project, but the teacher assumes the best idea was yours. A quiet teammate actually created it, and correcting the misunderstanding may reduce your recognition.",
        "What would you do?",
        listOf(
            choice("Accept the praise; everyone contributed somehow.", "General contribution does not justify silently accepting specific credit that belongs to someone else."),
            choice("Correct the teacher and name your teammate’s contribution.", "You protect fairness and make the team’s trust more valuable than one moment of applause.", true),
            choice("Thank the teammate privately but say nothing publicly.", "Private gratitude does not repair the public misunderstanding that benefited you.")
        ),
        "Krishna teaches that leadership is example and service. Recognition is not a scarce resource that must be captured. Giving accurate credit strengthens both truth and the people who worked with you.",
        "A leader shares praise accurately and accepts responsibility openly.",
        "Correct one mistaken attribution and describe each person’s real contribution."
    ),
    DharmaDecisionStory(
        "family-argument", "Respect", "The Message Typed in Anger",
        "After an argument with a parent or guardian, you type a harsh message listing every past mistake. Sending it would feel powerful for a moment.",
        "What would you do?",
        listOf(
            choice("Send it immediately so they understand your pain.", "Pain deserves expression, but words sent at the emotional peak often add wounds that hide the real need."),
            choice("Delete everything and never discuss the issue.", "Avoiding every conversation may preserve temporary quiet while allowing resentment and the original problem to grow."),
            choice("Pause, calm down and later express the specific issue and request respectfully.", "You honour both your feelings and the relationship while giving the conversation a workable focus.", true)
        ),
        "Krishna traces how anger can cloud memory and judgment. Regulation is not surrender. Calm first, then speak truth gently and specifically. Respect must move in both directions, and serious harm should be brought to a safe adult.",
        "A wise pause protects truth from becoming a weapon.",
        "Rewrite the message using: “When this happened, I felt… I need… Can we…?”"
    ),
    DharmaDecisionStory(
        "animal-cruelty", "Compassion", "The Stone Near the Puppy",
        "Some children are frightening a stray puppy by throwing small stones near it. They invite you to join and say the puppy is not actually being hit.",
        "What would you do?",
        listOf(
            choice("Watch without joining because the puppy is not being hit.", "Fear and distress are still harm, and an audience can encourage the behaviour to continue."),
            choice("Throw a stone at the children to make them understand.", "Copying harm escalates danger and abandons the compassion you are trying to defend."),
            choice("Stop the behaviour safely and call an adult or animal helper.", "You protect the animal without creating another target of violence.", true)
        ),
        "Krishna’s care for cows widens Dharma beyond human convenience. Compassion is not only feeling sorry; it is informed, safe action that reduces suffering.",
        "Protect living beings without becoming cruel toward the wrongdoer.",
        "Involve a trusted adult and learn the safe local contact for animal help."
    ),
    DharmaDecisionStory(
        "ai-plagiarism", "Learning", "The Perfect Assignment",
        "A tool can generate your entire assignment in seconds. The teacher expects your own reasoning, but submitting the generated answer would save time and likely earn a good grade.",
        "What would you do?",
        listOf(
            choice("Submit it unchanged because using tools is part of modern learning.", "A useful tool becomes dishonest when its output is presented as your understanding against the stated rules."),
            choice("Use permitted help for ideas, verify everything and write the work yourself with disclosure where required.", "You keep the tool in service of learning rather than using it to replace learning and authorship.", true),
            choice("Rewrite a few sentences so nobody can detect it.", "Hiding the source more carefully changes detection, not the underlying dishonesty.")
        ),
        "Krishna praises knowledge joined with sincere inquiry. Technology can support Dharma when it strengthens understanding, truth and service. It weakens learning when it creates a false appearance of mastery.",
        "Use tools to deepen honest effort, not imitate it.",
        "Check the rules, verify sources, write in your own understanding and disclose assistance when required."
    ),
    DharmaDecisionStory(
        "unfair-exclusion", "Inclusion", "The Team Nobody Picks",
        "Your friends are forming a team and deliberately exclude a capable student because they are considered unpopular. Including them may annoy the group.",
        "What would you do?",
        listOf(
            choice("Stay with your friends; group harmony matters most.", "Harmony built by excluding someone unfairly protects comfort rather than justice."),
            choice("Invite the student and explain that selection should use fair, relevant reasons.", "You defend equal dignity while keeping the conversation focused on fair participation.", true),
            choice("Secretly tell the student the group dislikes them.", "Passing on rejection without challenging the unfair decision may deepen hurt without improving the situation.")
        ),
        "Krishna teaches equal vision: status and popularity do not decide spiritual dignity. Fairness does not mean ignoring skill or responsibility; it means using relevant standards rather than social contempt.",
        "Belonging should not be purchased through another person’s exclusion.",
        "Invite the excluded person or ask the group to state a fair rule that applies to everyone."
    )
)

fun dharmaDecisionStory(id: String?): DharmaDecisionStory =
    dharmaDecisionStories.firstOrNull { it.id == id } ?: dharmaDecisionStories.first()
