package com.indianservers.krishna4u.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.krishnaDataStore by preferencesDataStore("krishna_preferences")

data class UserPreferences(
    val onboardingComplete: Boolean = false,
    val language: String = "English",
    val interests: Set<String> = emptySet()
)

class PreferencesRepository(private val context: Context) {
    private object Keys {
        val onboarding = booleanPreferencesKey("onboarding_complete")
        val language = stringPreferencesKey("language")
        val interests = stringPreferencesKey("interests")
    }

    val preferences: Flow<UserPreferences> = context.krishnaDataStore.data.map { values ->
        UserPreferences(
            onboardingComplete = values[Keys.onboarding] ?: false,
            language = values[Keys.language] ?: "English",
            interests = values[Keys.interests]?.split('|')?.filter(String::isNotBlank)?.toSet() ?: emptySet()
        )
    }

    suspend fun setLanguage(value: String) = context.krishnaDataStore.edit { it[Keys.language] = value }
    suspend fun finishOnboarding(interests: Set<String>) = context.krishnaDataStore.edit {
        it[Keys.interests] = interests.joinToString("|")
        it[Keys.onboarding] = true
    }
}
