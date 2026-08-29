package com.indianservers.krishna4u.core.localization

/** BCP-47 language metadata shared by onboarding and Settings. */
data class AppLanguage(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val available: Boolean = false
)

val appLanguages = listOf(
    AppLanguage("en", "English", "English", available = true),
    AppLanguage("hi", "हिन्दी", "Hindi"),
    AppLanguage("te", "తెలుగు", "Telugu"),
    AppLanguage("sa", "संस्कृतम्", "Sanskrit"),
    AppLanguage("es", "Español", "Spanish"),
    AppLanguage("fr", "Français", "French"),
    AppLanguage("de", "Deutsch", "German"),
    AppLanguage("pt", "Português", "Portuguese"),
    AppLanguage("it", "Italiano", "Italian"),
    AppLanguage("ar", "العربية", "Arabic"),
    AppLanguage("ru", "Русский", "Russian"),
    AppLanguage("zh", "中文", "Chinese"),
    AppLanguage("ja", "日本語", "Japanese"),
    AppLanguage("ko", "한국어", "Korean"),
    AppLanguage("id", "Bahasa Indonesia", "Indonesian")
)

fun appLanguage(value: String): AppLanguage = appLanguages.firstOrNull {
    it.code.equals(value, ignoreCase = true) ||
        it.nativeName.equals(value, ignoreCase = true) ||
        it.englishName.equals(value, ignoreCase = true)
} ?: appLanguages.first()

fun supportedLanguageCode(value: String): String = appLanguage(value).takeIf(AppLanguage::available)?.code ?: "en"
