package com.indianservers.krishna4u.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.indianservers.krishna4u.core.localization.supportedLanguageCode
import com.indianservers.krishna4u.core.localization.supportedReadingMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.krishnaDataStore by preferencesDataStore("krishna_preferences")

data class UserPreferences(
    val onboardingComplete: Boolean = false,
    val language: String = "en",
    val interests: Set<String> = emptySet(),
    val displayName: String = "Devotee",
    val krishnaMessageIndex: Int = 0,
    val bookmarks: Set<String> = emptySet(),
    val reflections: Set<String> = emptySet(),
    val readSlokas: Set<String> = emptySet(),
    val textSize: String = "Comfortable",
    val notificationsEnabled: Boolean = true,
    val notificationHour: Int = 8,
    val notificationMinute: Int = 0,
    val bedtimeMessageEnabled: Boolean = false,
    val bedtimeHour: Int = 21,
    val bedtimeMinute: Int = 0,
    val darkTheme: Boolean = true,
    val reducedMotion: Boolean = false,
    val readingMode: String = "teens"
)

class PreferencesRepository(private val context: Context) {
    private object Keys {
        val onboarding = booleanPreferencesKey("onboarding_complete")
        val language = stringPreferencesKey("language")
        val interests = stringPreferencesKey("interests")
        val displayName = stringPreferencesKey("display_name")
        val krishnaMessageIndex = intPreferencesKey("krishna_message_index")
        val bookmarks = stringSetPreferencesKey("bookmarks")
        val reflections = stringSetPreferencesKey("journal_reflections")
        val readSlokas = stringSetPreferencesKey("read_slokas")
        val textSize = stringPreferencesKey("text_size")
        val notificationsEnabled = booleanPreferencesKey("notifications_enabled")
        val notificationHour = intPreferencesKey("notification_hour")
        val notificationMinute = intPreferencesKey("notification_minute")
        val bedtimeMessageEnabled = booleanPreferencesKey("bedtime_message_enabled")
        val bedtimeHour = intPreferencesKey("bedtime_hour")
        val bedtimeMinute = intPreferencesKey("bedtime_minute")
        val darkTheme = booleanPreferencesKey("dark_theme")
        val reducedMotion = booleanPreferencesKey("reduced_motion")
        val readingMode = stringPreferencesKey("reading_mode")
    }

    val preferences: Flow<UserPreferences> = context.krishnaDataStore.data.map { values ->
        UserPreferences(
            onboardingComplete = values[Keys.onboarding] ?: false,
            language = supportedLanguageCode(values[Keys.language] ?: "en"),
            interests = values[Keys.interests]?.split('|')?.filter(String::isNotBlank)?.toSet() ?: emptySet(),
            displayName = values[Keys.displayName]?.takeIf(String::isNotBlank) ?: "Devotee",
            krishnaMessageIndex = values[Keys.krishnaMessageIndex] ?: 0,
            bookmarks = values[Keys.bookmarks]?.toSet() ?: emptySet(),
            reflections = values[Keys.reflections]?.toSet() ?: emptySet(),
            readSlokas = values[Keys.readSlokas]?.toSet() ?: emptySet(),
            textSize = values[Keys.textSize] ?: "Comfortable",
            notificationsEnabled = values[Keys.notificationsEnabled] ?: true,
            notificationHour = (values[Keys.notificationHour] ?: 8).coerceIn(0, 23),
            notificationMinute = (values[Keys.notificationMinute] ?: 0).coerceIn(0, 59),
            bedtimeMessageEnabled = values[Keys.bedtimeMessageEnabled] ?: false,
            bedtimeHour = (values[Keys.bedtimeHour] ?: 21).coerceIn(0, 23),
            bedtimeMinute = (values[Keys.bedtimeMinute] ?: 0).coerceIn(0, 59),
            darkTheme = values[Keys.darkTheme] ?: true,
            reducedMotion = values[Keys.reducedMotion] ?: false,
            readingMode = supportedReadingMode(values[Keys.readingMode] ?: "teens")
        )
    }

    suspend fun setLanguage(value: String) = context.krishnaDataStore.edit { it[Keys.language] = supportedLanguageCode(value) }
    suspend fun setTextSize(value: String) = context.krishnaDataStore.edit { it[Keys.textSize] = value }
    suspend fun setNotificationsEnabled(value: Boolean) = context.krishnaDataStore.edit { it[Keys.notificationsEnabled] = value }
    suspend fun setNotificationTime(hour: Int, minute: Int) = context.krishnaDataStore.edit {
        it[Keys.notificationHour] = hour.coerceIn(0, 23)
        it[Keys.notificationMinute] = minute.coerceIn(0, 59)
    }
    suspend fun setBedtimeMessageEnabled(value: Boolean) = context.krishnaDataStore.edit { it[Keys.bedtimeMessageEnabled] = value }
    suspend fun setBedtime(hour: Int, minute: Int) = context.krishnaDataStore.edit {
        it[Keys.bedtimeHour] = hour.coerceIn(0, 23)
        it[Keys.bedtimeMinute] = minute.coerceIn(0, 59)
    }
    suspend fun setDarkTheme(value: Boolean) = context.krishnaDataStore.edit { it[Keys.darkTheme] = value }
    suspend fun setReducedMotion(value: Boolean) = context.krishnaDataStore.edit { it[Keys.reducedMotion] = value }
    suspend fun setReadingMode(value: String) = context.krishnaDataStore.edit { it[Keys.readingMode] = supportedReadingMode(value) }
    suspend fun setDisplayName(value: String) = context.krishnaDataStore.edit {
        it[Keys.displayName] = value.trim().take(40).ifBlank { "Devotee" }
    }
    suspend fun finishOnboarding(interests: Set<String>) = context.krishnaDataStore.edit {
        it[Keys.interests] = interests.joinToString("|")
        it[Keys.krishnaMessageIndex] = 0
        it[Keys.onboarding] = true
    }

    suspend fun setKrishnaMessageIndex(value: Int) = context.krishnaDataStore.edit {
        it[Keys.krishnaMessageIndex] = value.coerceAtLeast(0)
    }

    suspend fun toggleBookmark(id: String) = context.krishnaDataStore.edit { preferences ->
        val updated = preferences[Keys.bookmarks]?.toMutableSet() ?: mutableSetOf()
        if (!updated.add(id)) updated.remove(id)
        preferences[Keys.bookmarks] = updated
    }

    suspend fun saveReflection(text: String) = context.krishnaDataStore.edit { preferences ->
        val cleanText = text.trim().take(2_000)
        if (cleanText.isNotBlank()) {
            val updated = preferences[Keys.reflections]?.toMutableSet() ?: mutableSetOf()
            updated.add("${System.currentTimeMillis()}|$cleanText")
            preferences[Keys.reflections] = updated
        }
    }

    suspend fun deleteReflection(id: String) = context.krishnaDataStore.edit { preferences ->
        val updated = preferences[Keys.reflections]?.toMutableSet() ?: mutableSetOf()
        updated.remove(id)
        preferences[Keys.reflections] = updated
    }

    suspend fun markSlokaRead(chapter: Int, verse: Int) = context.krishnaDataStore.edit { preferences ->
        val read = preferences[Keys.readSlokas]?.toMutableSet() ?: mutableSetOf()
        read.add("$chapter.$verse")
        preferences[Keys.readSlokas] = read
    }

    suspend fun resetJourney() = context.krishnaDataStore.edit { preferences ->
        preferences.clear()
    }
}
