package com.indianservers.krishna4u.feature.difficultquestions

data class DifficultKrishnaQuestion(
    val id: String,
    val theme: String,
    val question: String,
    val shortAnswer: String,
    val context: String,
    val ethicalTension: String,
    val readings: List<String>,
    val doNotConclude: String,
    val reflection: String,
    val sourceNote: String
)

val difficultKrishnaQuestions = listOf(
    DifficultKrishnaQuestion(
        "why-war", "War & Peace", "Why did Krishna not simply prevent the Kurukshetra war?",
        "The Mahabharata presents Krishna as pursuing peace while refusing to call continued injustice peace. The war follows accumulated choices made by many people, not a divine desire for violence.",
        "The conflict grows through the dice-game humiliation, dispossession, broken agreements, failed reconciliation and repeated refusal to restore a just place for the Pandavas. Krishna enters as adviser, envoy and later non-combatant charioteer. He does not remove the agency of either side.",
        "If Krishna is divine, readers naturally ask why persuasion was allowed to fail and why innocent people had to suffer. The epic does not make the war clean: victory leaves grief, moral injury and destruction even among those fighting for a more just order.",
        listOf(
            "A Dharma reading says peace without justice can preserve oppression; force becomes a tragic last resort after meaningful alternatives fail.",
            "A freedom-and-consequence reading emphasises that divine guidance does not erase human agency. Duryodhana and others repeatedly choose pride despite warnings.",
            "A critical reading asks whether every peaceful possibility was exhausted and keeps attention on the enormous human cost rather than celebrating war."
        ),
        "Do not conclude that any conflict called ‘righteous’ automatically has divine approval. Dharma requires evidence, proportion, legitimate responsibility, protection of non-combatants and serious pursuit of peaceful alternatives.",
        "When does avoiding conflict protect peace, and when does it merely leave injustice unchallenged?",
        "Primary narrative: Mahabharata, especially the peace negotiations and war books; philosophical setting: Bhagavad Gita. Retellings vary in detail."
    ),
    DifficultKrishnaQuestion(
        "gita-violence", "War & Peace", "Does the Bhagavad Gita encourage violence?",
        "The Gita addresses one warrior inside a specific moral crisis; it does not give every angry person permission to fight. Its central disciplines include self-mastery, non-hatred, duty, discernment and freedom from selfish motive.",
        "Arjuna’s refusal arises on a battlefield after political and ethical breakdown, not during an ordinary disagreement. Krishna examines the soul, action, motive, social duty and attachment before Arjuna acts. The text has also inspired nonviolent interpreters who read the battlefield inwardly or stress action without hatred.",
        "The literal narrative undeniably involves war. Reading it only as an inner metaphor ignores the epic setting; reading it as a universal command to use violence ignores its ethical restrictions and wider teachings.",
        listOf(
            "A contextual reading limits Arjuna’s duty to a trained warrior facing an exceptional public crisis.",
            "An inward reading sees the battle as a symbol of the struggle against ignorance, attachment and destructive tendencies.",
            "A responsibility reading focuses less on violence itself and more on acting without hatred or personal gain when withdrawal would abandon a grave duty."
        ),
        "Do not use the Gita to sanctify revenge, communal hatred, personal aggression or obedience without conscience. Arjuna questions deeply; discernment is part of the teaching.",
        "What safeguards should exist before anyone claims that harmful action is a necessary duty?",
        "Primary text: Bhagavad Gita within the Bhishma Parva of the Mahabharata. Interpretive traditions include literal, philosophical, devotional and allegorical readings."
    ),
    DifficultKrishnaQuestion(
        "strategy-deception", "Strategy", "Why does Krishna sometimes recommend strategies that seem deceptive?",
        "The Mahabharata portrays a world where battlefield rules have already been repeatedly broken. Krishna’s strategies aim to stop powerful actors who exploit honour while harming others—but the epic still shows moral cost.",
        "Several warriors possess vows, protections or advantages that make ordinary combat ineffective. Krishna helps the Pandavas identify openings involving Bhishma, Drona, Karna and Duryodhana. These are not presented in a morally untouched world; Abhimanyu’s death and many earlier injustices have already damaged the shared code.",
        "If good ends excuse every means, Dharma collapses into convenience. Yet if ethical actors follow rules that aggressors strategically abuse, rules may become tools of oppression. The epic forces readers to sit inside that conflict rather than offering a painless formula.",
        listOf(
            "A consequential reading argues that limited rule-breaking prevented a more unjust victory and ended actors who could not otherwise be stopped.",
            "A tragic-Dharma reading says the choices may have been necessary yet still morally injuring; necessity does not turn them pure.",
            "A critical reading warns that claiming superior purpose can make anyone rationalise manipulation, so strategic exceptions require severe scrutiny."
        ),
        "Do not conclude that cleverness is Dharma or that a good intention automatically purifies dishonest means. Modern life usually offers lawful, transparent alternatives unavailable in epic warfare.",
        "Can an action be the least harmful available choice and still leave a moral debt?",
        "Primary narrative: Mahabharata war books. Details and moral emphasis differ across editions, regional tellings and commentarial traditions."
    ),
    DifficultKrishnaQuestion(
        "drona-ashwatthama", "Mahabharata Ethics", "Was the ‘Ashwatthama is dead’ strategy morally right?",
        "The strategy stops Drona by exploiting his love for his son. It is effective, but the Mahabharata surrounds it with discomfort and consequences rather than presenting a simple model of truthful speech.",
        "Drona is devastating the opposing army. After an elephant named Ashwatthama is killed, Yudhishthira announces that Ashwatthama has died and adds that it was the elephant in a way Drona does not fully receive. Drona lays down his resistance and is killed by Dhrishtadyumna.",
        "The statement uses literal wording to create a false belief. It may save many lives, yet it wounds Yudhishthira’s identity as a truth-speaker and contributes to the war’s moral darkness. Drona’s killing after he ceases fighting adds another ethical layer.",
        listOf(
            "A necessity reading sees the ruse as the only available way to stop catastrophic killing.",
            "A speech-ethics reading says technically accurate words can still be lies when designed to deceive.",
            "A tragic reading refuses to declare either option clean: allowing slaughter and using deception both carry grave cost."
        ),
        "Do not cite this episode as permission for everyday lying. Most modern dilemmas do not involve an immediate battlefield threat with no transparent alternative.",
        "When, if ever, can withholding or shaping truth be justified to prevent immediate serious harm?",
        "Primary narrative: Drona Parva of the Mahabharata. Translations differ in wording and narrative detail."
    ),
    DifficultKrishnaQuestion(
        "karna-chariot", "Mahabharata Ethics", "Why did Krishna urge Arjuna to strike Karna when his chariot was stuck?",
        "Krishna answers Karna’s appeal to battlefield fairness by recalling grave wrongs in which Karna participated. The scene argues that rules cannot be invoked only when they protect us—but it remains ethically disturbing.",
        "During the duel, Karna struggles with his chariot and asks for a pause under warrior rules. Krishna recalls Draupadi’s humiliation, the plot against the Pandavas and the collective killing of Abhimanyu, where fair-combat norms were not honoured. Arjuna then kills Karna.",
        "Accountability for past injustice does not automatically answer whether a vulnerable combatant may be killed in that moment. The narrative confronts selective morality, accumulated retaliation and what remains of ethical rules after both sides violate them.",
        listOf(
            "A reciprocity reading says Karna cannot demand protections he helped deny to others when it served him.",
            "A strategic reading treats Karna as an immediate lethal threat who would resume combat if allowed to recover.",
            "A critical reading maintains that another person’s violation does not erase one’s own duty and sees the act as part of the victory’s moral stain."
        ),
        "Do not use Karna’s past wrongdoing to justify harming a helpless person in ordinary life. Justice requires legitimate process, evidence and proportion—not personal vengeance.",
        "Should moral rules remain binding when an opponent has repeatedly broken them?",
        "Primary narrative: Karna Parva of the Mahabharata. Karna’s character and culpability receive very different emphasis across later retellings."
    ),
    DifficultKrishnaQuestion(
        "bhishma-shikhandi", "Mahabharata Ethics", "Was using Shikhandi against Bhishma fair?",
        "The strategy uses Bhishma’s own refusal to fight Shikhandi so Arjuna can bring him down. It can be read as exploiting a vow, but also as the return of an unresolved injustice connected with Amba and Bhishma.",
        "Bhishma is nearly undefeatable and remains the military pillar of Duryodhana’s side despite recognising many of its wrongs. Shikhandi’s story is linked to Amba, whose life was devastated by choices surrounding Bhishma’s vow. Bhishma will not direct weapons against Shikhandi, and Arjuna fights from behind that protection.",
        "Bhishma’s personal greatness coexists with institutional loyalty that sustains injustice. Shikhandi is both a person with agency and part of a strategy. Modern readers also rightly question translations that mishandle Shikhandi’s gendered identity.",
        listOf(
            "A karmic-justice reading sees Shikhandi as the return of an injustice Bhishma never adequately repaired.",
            "A responsibility reading argues that a noble vow cannot excuse supporting an unjust regime indefinitely.",
            "A critical reading asks whether using someone’s identity as a tactical shield respects that person or reduces them to an instrument."
        ),
        "Do not turn this story into ridicule or hostility toward transgender or gender-diverse people. Shikhandi deserves textual and human respect beyond the tactical role.",
        "When can loyalty to an institution become participation in its injustice?",
        "Primary narrative: Udyoga and Bhishma Parvas, with Amba’s earlier story. Shikhandi’s identity is rendered differently by translations and traditions."
    ),
    DifficultKrishnaQuestion(
        "duryodhana-thigh", "Mahabharata Ethics", "Why was Bhima told to strike Duryodhana below the waist?",
        "The blow fulfils Bhima’s vow and defeats a highly skilled opponent, but it violates the stated rules of mace combat. The epic does not hide the breach or the outrage that follows.",
        "Duryodhana is superior in the formal mace duel. Krishna signals the vulnerability of his thigh, which Bhima had vowed to break after Draupadi’s humiliation. Bhima strikes, and Balarama strongly condemns the violation of combat rules.",
        "The scene joins punishment, revenge, strategy and broken rules. Duryodhana has caused immense injustice, yet his wrongdoing does not make every method against him ethically uncomplicated.",
        listOf(
            "A retributive reading connects the thigh to Duryodhana’s earlier obscene humiliation of Draupadi and sees the blow as vowed justice.",
            "A war-ending reading views the breach as necessary to stop a fighter unlikely to lose under normal conditions.",
            "A rule-based reading agrees with Balarama that victory gained through a prohibited strike compromises the victor."
        ),
        "Do not conclude that an opponent’s bad character permits any punishment. Modern justice should reject revenge, bodily humiliation and private enforcement.",
        "If following a rule allows an unjust outcome, who has authority to change the rule—and when?",
        "Primary narrative: Shalya Parva of the Mahabharata. The disagreement between Krishna’s strategy and Balarama’s protest is ethically significant."
    ),
    DifficultKrishnaQuestion(
        "punishment-shishupala", "Punishment", "Why did Krishna punish Shishupala so severely?",
        "Traditional accounts frame the episode as the end of repeated, escalating offences after extraordinary patience. It should not be converted into permission for personal violence over insult.",
        "At Yudhishthira’s Rajasuya gathering, Shishupala repeatedly abuses Krishna and disrupts the assembly. The narrative connects this moment to an earlier promise that many offences would be tolerated before consequence. Krishna ultimately kills him with the discus.",
        "The punishment appears extreme if treated as a response to speech alone. Traditional readings place Shishupala inside a longer history of violence and cosmic conflict, while modern readers ask about proportion, authority and freedom of expression.",
        listOf(
            "A devotional reading sees divine judgment after prolonged mercy and also describes Shishupala’s final absorption into the divine.",
            "A governance reading treats the public abuse as part of repeated dangerous conduct, not merely a private insult.",
            "A modern ethical reading questions capital punishment and insists that criticism or offence cannot justify personal lethal retaliation."
        ),
        "Do not imitate divine-punishment narratives. Human beings are limited, biased and accountable to law; insults should be met with boundaries and proportionate processes.",
        "How should patience, public safety, proportionality and accountability be balanced?",
        "The episode appears in the Sabha Parva and devotional retellings, which may frame Shishupala’s history and liberation differently."
    ),
    DifficultKrishnaQuestion(
        "kamsa-death", "Punishment", "Why did Krishna kill Kamsa instead of forgiving him?",
        "Kamsa is portrayed not as a single offender asking forgiveness but as an active tyrant responsible for imprisonment, child-killing and continued threats. Stopping him protects others; it is not presented as anger over a personal slight.",
        "Fear of prophecy leads Kamsa to imprison Devaki and Vasudeva and destroy their children. He sends repeated threats toward Krishna and maintains violent rule. Krishna’s confrontation ends that rule and restores Ugrasena rather than claiming power for himself.",
        "The story raises enduring questions: when does protection require force, who may exercise it, and could restraint or lawful confinement have worked? Ancient royal narratives do not map neatly onto modern legal institutions.",
        listOf(
            "A protection reading understands Kamsa’s death as ending an immediate, continuing threat to many lives.",
            "A liberation reading in devotional tradition sometimes interprets even antagonists as ultimately drawn back into divine reality.",
            "A modern governance reading separates the moral need to stop tyranny from the methods available today through law, institutions and due process."
        ),
        "Do not use the episode to justify private violence against someone labelled evil. Modern Dharma includes lawful protection, evidence, restraint and accountable institutions.",
        "What makes protective force different from revenge?",
        "Primary narratives occur in the Bhagavata Purana, Vishnu Purana, Harivamsha and other traditions, with differences in emphasis."
    ),
    DifficultKrishnaQuestion(
        "rasa-lila", "Divine Play", "How should the rasa-lila be understood without romanticising harmful behaviour?",
        "Major devotional traditions read the rasa-lila as sacred theology about the soul’s total love for the divine, not as ordinary social conduct to copy. Its poetic symbolism must not be used to bypass consent or responsibility.",
        "The Bhagavata Purana places the episode within Krishna’s divine play and the gopis’ intense devotion. Commentators often stress that Krishna is not an ordinary human seeker of pleasure and that hearing the story requires ethical and spiritual preparation.",
        "Modern readers may still feel concern about marriage, age, power and consent. Simply saying ‘it is divine’ can silence legitimate questions; reducing the episode to ordinary romance can also erase its theological purpose.",
        listOf(
            "A bhakti reading sees the gopis as souls abandoning ego and social self-importance for complete divine love.",
            "A symbolic reading treats the night dance as divine presence responding uniquely to every devoted heart.",
            "A critical reading asks how retellings can preserve women’s agency and prevent sacred language from excusing exploitation."
        ),
        "Do not use rasa-lila to justify secrecy, infidelity, coercion, unequal adult–minor relationships or a teacher exploiting followers. Human relationships require consent, honesty and accountability.",
        "How can sacred poetry transform desire without being misused to excuse it?",
        "Primary devotional narrative: Bhagavata Purana, Book 10. Vaishnava schools offer extensive and differing theological commentaries."
    ),
    DifficultKrishnaQuestion(
        "butter-stealing", "Divine Play", "Why celebrate Krishna stealing butter if stealing is wrong?",
        "The butter stories belong to affectionate child-lila and devotional symbolism; they are not moral instructions telling children to take what belongs to others.",
        "In Vrindavan stories, little Krishna takes butter, shares it and charms or frustrates the households around him. Yashoda and the community still correct and contain his mischief. Devotional poetry reads butter as the heart’s love, churned through practice and irresistible to the divine.",
        "Humour and sacred play can communicate intimacy, but young readers may understandably ask why behaviour called wrong elsewhere is adored here. Context and genre matter.",
        listOf(
            "A childhood reading enjoys the exaggeration and affection surrounding a beloved mischievous child while preserving parental boundaries.",
            "A symbolic reading says Krishna ‘steals’ the devotee’s carefully prepared heart and removes possessive ego.",
            "An ethical reading distinguishes playful literature from rules for property, honesty and consent in ordinary life."
        ),
        "Do not tell children that invoking Krishna makes theft harmless. Taking another person’s property without permission requires return, apology and repair.",
        "How do story, symbolism and ordinary moral rules differ without contradicting one another?",
        "Butter-lila appears across Puranic, regional and devotional literary traditions; particular incidents and symbolism vary."
    ),
    DifficultKrishnaQuestion(
        "rescued-women", "Dignity & Agency", "Why do traditions say Krishna married thousands of rescued women?",
        "The traditional account presents Krishna as restoring security and social dignity to women imprisoned by Narakasura, not collecting them as trophies. Modern readers should still ask how each woman’s agency is represented.",
        "After Narakasura’s defeat, a large number of captive women are freed. In a social world where captivity could lead to stigma and abandonment, the narrative says they seek Krishna’s protection and he accepts them, appearing with each through divine power in devotional tellings.",
        "The story aims to reverse shame, yet modern readers may question mass marriage, consent and whether social acceptance should depend on marriage. Ancient social solutions should not automatically become modern prescriptions.",
        listOf(
            "A dignity reading emphasises that the survivors are not blamed or treated as impure because of crimes committed against them.",
            "A devotional reading interprets the many marriages as the divine being fully present to countless individual souls.",
            "A feminist reading welcomes the rejection of stigma while asking for fuller attention to the women’s voices, choices and lives beyond rescue."
        ),
        "Do not use the story to romanticise captivity, erase survivor agency or claim marriage is the required repair for sexual or social violence. Survivors deserve choice, safety and dignity on their own terms.",
        "How can a community restore dignity without deciding a survivor’s future for them?",
        "The Narakasura and marriage narratives appear in the Bhagavata Purana and related traditions; numbers and details vary across texts and retellings."
    )
)

fun difficultKrishnaQuestion(id: String?): DifficultKrishnaQuestion =
    difficultKrishnaQuestions.firstOrNull { it.id == id } ?: difficultKrishnaQuestions.first()
