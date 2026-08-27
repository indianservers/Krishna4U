package com.indianservers.krishna4u

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.indianservers.krishna4u.data.local.PreferencesRepository
import com.indianservers.krishna4u.data.local.UserPreferences
import com.indianservers.krishna4u.feature.gallery.*
import com.indianservers.krishna4u.feature.home.HomeScreen
import com.indianservers.krishna4u.feature.onboarding.*
import kotlinx.coroutines.launch

@Composable
fun KrishnaApp() {
    val context = LocalContext.current
    val repository = remember { PreferencesRepository(context.applicationContext) }
    val prefs by repository.preferences.collectAsState(initial = UserPreferences())
    val nav = rememberNavController()
    val scope = rememberCoroutineScope()
    fun go(route: String) { nav.navigate(route) { launchSingleTop = true } }
    NavHost(nav, startDestination = "01") {
        composable("01") { SplashScreen { go(if (prefs.onboardingComplete) "05" else "02") } }
        composable("02") { DivineOnboardingScreen({ go("03") }, { go("03") }) }
        composable("03") { ChooseLanguageScreen(prefs.language, { scope.launch { repository.setLanguage(it) } }, { go("04") }) }
        composable("04") { PersonaliseJourneyScreen { scope.launch { repository.finishOnboarding(it); go("05") } } }
        composable("05") { HomeScreen(::go) { go("gallery") } }
        composable("gallery") { MockupGalleryScreen(::go) }
        mockupEntries.filter { it.number !in setOf("01", "02", "03", "04", "05") }.forEach { entry ->
            composable(entry.number) { PendingMockupScreen(entry) { nav.popBackStack() } }
        }
    }
}
