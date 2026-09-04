package com.indianservers.krishna4u.feature.letters

data class KrishnaLetterTranslation(
    val situation: String,
    val title: String,
    val preview: String,
    val paragraphs: List<String>,
    val reflection: String,
    val nextStep: String
)

data class LocalizedKrishnaLetter(
    val source: KrishnaLetter,
    val languageCode: String,
    val situation: String,
    val title: String,
    val preview: String,
    val paragraphs: List<String>,
    val reflection: String,
    val nextStep: String
) {
    fun personalizedParagraphs(name: String): List<String> {
        val readerName = name.trim().ifBlank {
            when (languageCode) {
                "te" -> "సాధకుడు"
                "hi" -> "साधक"
                else -> "Seeker"
            }
        }
        if (languageCode == "en") return source.personalizedReadingParagraphs(readerName)
        val greeting = when (languageCode) {
            "te" -> "నా ప్రియమైన $readerName,"
            "hi" -> "मेरे प्रिय $readerName,"
            else -> "My dear $readerName,"
        }
        return paragraphs.mapIndexed { index, paragraph ->
            if (index == 0) "$greeting $paragraph" else paragraph
        }
    }

    fun spokenText(name: String): String {
        val ending = when (languageCode) {
            "te" -> "గుర్తుంచుకో: $reflection నేను నీతో ఉన్నాను, కృష్ణుడు."
            "hi" -> "याद रखो: $reflection मैं तुम्हारे साथ हूँ, कृष्ण।"
            else -> "Remember: $reflection I am with you, Krishna."
        }
        return "${personalizedParagraphs(name).joinToString(" ")} $ending"
    }
}

private val teluguTitleCorrections = mapOf(
    "failure" to "మీరు ఒక ఫలితం కంటే ఎంతో ఎక్కువ",
    "loneliness" to "మిమ్మల్ని మరచిపోలేదు",
    "tired-strong" to "ఆ భారాన్ని దించవచ్చు",
    "self-forgiveness" to "ఒక తప్పును జీవితాంత శిక్షగా మార్చుకోవద్దు",
    "stuck" to "ముందడుగు మీరు అనుకున్నదానికంటే చిన్నగా మొదలవచ్చు",
    "change" to "కొత్తదాన్ని ఒక్కో అడుగుగా ఎదుర్కోవచ్చు",
    "grateful" to "కృతజ్ఞతను సేవగా మార్చండి",
    "hopeful" to "ఆశను తదుపరి సరైన అడుగుగా మార్చండి",
    "confident" to "ఆత్మవిశ్వాసం పనికి ఉపయోగపడనివ్వండి",
    "inspired" to "స్ఫూర్తి తగ్గకముందే ప్రారంభించండి",
    "belonging" to "మీ ఆప్యాయత వలయాన్ని విస్తరించండి",
    "homesick" to "మీరు జీవించే విలువల్లో ఇంటిని వెంట తీసుకెళ్లండి",
    "identity-confusion" to "మీరు ఇంకా ఎదుగుతున్నారనే విషయాన్ని అంగీకరించండి",
    "parent-failing" to "పరిపూర్ణతకంటే మీ తదుపరి సరిదిద్దుకోవడమే ముఖ్యం",
    "rebuilding-trust" to "నిజాన్ని మళ్లీ మళ్లీ జీవించినప్పుడు నమ్మకం తిరిగి వస్తుంది",
    "comfort-growth" to "సౌకర్యం మిమ్మల్ని నిలబెట్టాలి, నియంత్రించకూడదు",
    "smart-work" to "ప్రయత్నానికి వివేకాన్ని జోడించండి",
    "someone-only-takes" to "ప్రేమలో ఇవ్వడమూ స్వీకరించడమూ ఉండాలి",
    "rise-again" to "భారాన్ని కాదు, పాఠాన్ని తీసుకుని మళ్లీ లేవండి",
    "life-purpose" to "మీ దగ్గర ఉన్న మంచితోనే ప్రయోజనం మొదలవుతుంది",
    "dream-discipline" to "రోజువారీ సాధనే కలను ముందుకు నడిపిస్తుంది",
    "valuable-gift" to "మీరు అందించగల మంచితనం ప్రపంచానికి అవసరం",
    "next-step-strength" to "ఒక్క అడుగుకు సరిపడ వెలుగు చాలు"
)

private val hindiTitleCorrections = mapOf(
    "failure" to "आप एक परिणाम से कहीं अधिक हैं",
    "tired-strong" to "आप यह बोझ नीचे रख सकते हैं",
    "self-forgiveness" to "एक गलती को जीवन भर की सज़ा मत बनाइए",
    "stuck" to "आगे बढ़ना एक छोटे कदम से शुरू हो सकता है",
    "unanswered-prayers" to "मौन यह प्रमाण नहीं कि आपको भुला दिया गया है",
    "change" to "नए बदलाव का सामना एक-एक कदम करके करें",
    "belonging" to "अपने अपनत्व के दायरे को व्यापक बनाइए",
    "homesick" to "अपने जीवन-मूल्यों में घर को साथ लेकर चलें",
    "identity-confusion" to "आपका अभी अधूरा होना ठीक है",
    "parent-failing" to "पूर्णता से अधिक अगला सुधार मायने रखता है",
    "smart-work" to "प्रयास के साथ बुद्धि जोड़ें",
    "returning-to-hurt" to "एक परिचित दरवाज़ा फिर भी दर्द तक ले जा सकता है",
    "memories-pull-back" to "आज को छोड़े बिना अतीत को याद रखें",
    "closure-never-came" to "आप अपनी ओर से उस दरवाज़े को बंद कर सकते हैं",
    "rise-again" to "बोझ नहीं, सीख लेकर फिर उठें",
    "life-purpose" to "उद्देश्य आपके पास मौजूद अच्छाई से शुरू होता है",
    "dream-discipline" to "रोज़ का अभ्यास आपके सपने को आगे ले जाए",
    "future-still-open" to "आपका भविष्य एक परिणाम से बड़ा है",
    "next-step-strength" to "अगले एक कदम भर का प्रकाश पर्याप्त है"
)

private fun localizedTranslation(languageCode: String, id: String): KrishnaLetterTranslation? = when (languageCode) {
    "te" -> teluguKrishnaLetterTranslations[id]
    "hi" -> hindiKrishnaLetterTranslations[id]
    else -> null
}?.let { translation ->
    val correctedTitle = when (languageCode) {
        "te" -> teluguTitleCorrections[id]
        "hi" -> hindiTitleCorrections[id]
        else -> null
    }
    if (correctedTitle == null) translation else translation.copy(title = correctedTitle)
}

fun localizedKrishnaLetters(languageCode: String): List<LocalizedKrishnaLetter> = krishnaLetters.map { source ->
    val translation = localizedTranslation(languageCode, source.id)
    if (translation == null) {
        LocalizedKrishnaLetter(
            source, "en", source.situation, source.title, source.preview,
            source.readingParagraphs, source.reflection, source.nextStep
        )
    } else {
        LocalizedKrishnaLetter(
            source, languageCode, translation.situation, translation.title, translation.preview,
            translation.paragraphs, translation.reflection, translation.nextStep
        )
    }
}

fun localizedKrishnaLetter(id: String?, languageCode: String): LocalizedKrishnaLetter {
    val letters = localizedKrishnaLetters(languageCode)
    return letters.firstOrNull { it.source.id == id } ?: letters.first()
}
