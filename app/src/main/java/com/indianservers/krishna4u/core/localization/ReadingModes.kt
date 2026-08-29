package com.indianservers.krishna4u.core.localization

data class ReadingMode(
    val id: String,
    val title: String,
    val ageRange: String,
    val description: String,
    val answerSentenceLimit: Int?
)

val readingModes = listOf(
    ReadingMode("kids", "Kids", "Ages 6–10", "Short stories, simple ideas and three memorable lessons.", 2),
    ReadingMode("teens", "Teens", "Ages 11–17", "Real-life language, moral choices and practical takeaways.", 3),
    ReadingMode("adults", "Adults", "Ages 18+", "Complete reflections with deeper context and all takeaways.", null)
)

fun readingMode(value: String): ReadingMode = readingModes.firstOrNull { it.id == value } ?: readingModes[1]

fun supportedReadingMode(value: String): String = readingMode(value).id

fun ageAppropriateAnswer(answer: String, modeId: String): String {
    val limit = readingMode(modeId).answerSentenceLimit ?: return answer
    val sentences = Regex("(?<=[.!?])\\s+").split(answer.trim())
    return sentences.take(limit).joinToString(" ")
}
