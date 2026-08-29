package com.indianservers.krishna4u

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.indianservers.krishna4u.core.notifications.DailyVerseNotifications
import com.indianservers.krishna4u.core.notifications.NightMessageNotifications
import com.indianservers.krishna4u.data.local.PreferencesRepository
import com.indianservers.krishna4u.data.local.UserPreferences
import com.indianservers.krishna4u.feature.gallery.*
import com.indianservers.krishna4u.feature.home.HomeScreen
import com.indianservers.krishna4u.feature.onboarding.*
import com.indianservers.krishna4u.feature.krishnalife.*
import com.indianservers.krishna4u.feature.teachings.*
import com.indianservers.krishna4u.feature.gita.*
import com.indianservers.krishna4u.feature.guidance.*
import com.indianservers.krishna4u.feature.personal.*
import com.indianservers.krishna4u.feature.wisdom.*
import com.indianservers.krishna4u.feature.emotions.*
import com.indianservers.krishna4u.feature.letters.*
import com.indianservers.krishna4u.feature.oneminute.*
import com.indianservers.krishna4u.feature.decisions.*
import com.indianservers.krishna4u.feature.difficultquestions.*
import com.indianservers.krishna4u.feature.parenting.*
import com.indianservers.krishna4u.feature.night.*
import com.indianservers.krishna4u.feature.collectibles.*
import com.indianservers.krishna4u.ui.theme.KrishnaPreferenceTheme
import kotlinx.coroutines.launch

@Composable
fun KrishnaApp(pendingDestination: String? = null, onDestinationConsumed: () -> Unit = {}) {
    val context = LocalContext.current
    val repository = remember { PreferencesRepository(context.applicationContext) }
    val prefs by repository.preferences.collectAsState(initial = UserPreferences())
    val nav = rememberNavController()
    val scope = rememberCoroutineScope()
    fun go(route: String) { nav.navigate(route) { launchSingleTop = true } }
    LaunchedEffect(prefs.notificationsEnabled, prefs.notificationHour, prefs.notificationMinute) {
        DailyVerseNotifications.sync(context.applicationContext, prefs)
    }
    LaunchedEffect(prefs.bedtimeMessageEnabled, prefs.bedtimeHour, prefs.bedtimeMinute) {
        NightMessageNotifications.sync(context.applicationContext, prefs)
    }
    LaunchedEffect(pendingDestination, prefs.onboardingComplete) {
        if (pendingDestination != null && prefs.onboardingComplete) {
            go(pendingDestination)
            onDestinationConsumed()
        }
    }
    KrishnaPreferenceTheme(prefs.darkTheme, prefs.textSize, prefs.reducedMotion) {
    NavHost(
        nav,
        startDestination = "01",
        enterTransition = { androidx.compose.animation.EnterTransition.None },
        exitTransition = { androidx.compose.animation.ExitTransition.None },
        popEnterTransition = { androidx.compose.animation.EnterTransition.None },
        popExitTransition = { androidx.compose.animation.ExitTransition.None }
    ) {
        composable("01") { SplashScreen { go(if (prefs.onboardingComplete) "05" else "02") } }
        composable("02") { DivineOnboardingScreen({ go("03") }, { go("03") }) }
        composable("03") { ChooseLanguageScreen(prefs.language, { scope.launch { repository.setLanguage(it) } }, { go("04") }) }
        composable("04") { PersonaliseJourneyScreen { scope.launch { repository.finishOnboarding(it); go("krishna_speaks") } } }
        composable("05") {
            HomeScreen(
                displayName = prefs.displayName,
                selectedNeeds = prefs.interests,
                readSlokaCount = prefs.readSlokas.size,
                homeShortcuts = prefs.homeShortcuts,
                onToggleHomeShortcut = { route -> scope.launch { repository.toggleHomeShortcut(route) } },
                onOpen = ::go
            )
        }
        composable("gallery") { MockupGalleryScreen(::go) }
        composable("06") { KrishnaLifeJourneyScreen(prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("07") { LifeEventDetailsScreen("govardhan", prefs.readingMode, "life:govardhan" in prefs.bookmarks, { scope.launch { repository.toggleBookmark("life:govardhan") } }, { nav.popBackStack() }, ::go) }
        composable("07/{eventId}") { entry ->
            val eventId = entry.arguments?.getString("eventId") ?: "govardhan"
            LifeEventDetailsScreen(eventId, prefs.readingMode, "life:$eventId" in prefs.bookmarks, { scope.launch { repository.toggleBookmark("life:$eventId") } }, { nav.popBackStack() }, ::go)
        }
        composable("family_stories") { FamilyStoryLibraryScreen(prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("family_story/{eventId}") { entry -> FamilyStoryDetailsScreen(entry.arguments?.getString("eventId"), prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("08") {
            TeachingsLibraryScreen(
                bookmarks = prefs.bookmarks,
                onToggleBookmark = { id -> scope.launch { repository.toggleBookmark("teaching:$id") } },
                onBack = { nav.popBackStack() },
                onNavigate = ::go
            )
        }
        composable("09") { TeachingDetailsScreen("karma-action", "teaching:karma-action" in prefs.bookmarks, { scope.launch { repository.toggleBookmark("teaching:karma-action") } }, { nav.popBackStack() }, ::go) }
        composable("teaching/{teachingId}") { entry ->
            val teachingId = entry.arguments?.getString("teachingId") ?: "karma-action"
            val bookmarkId = "teaching:$teachingId"
            TeachingDetailsScreen(teachingId, bookmarkId in prefs.bookmarks, { scope.launch { repository.toggleBookmark(bookmarkId) } }, { nav.popBackStack() }, ::go)
        }
        composable("10") { LessonsFromKrishnaScreen({ nav.popBackStack() }, ::go) }
        composable("11") { ApplyToYourLifeScreen({ nav.popBackStack() }, ::go) }
        composable("12") { GitaOverviewScreen({ nav.popBackStack() }, ::go) }
        composable("13") { ChapterExplorerScreen({ nav.popBackStack() }, ::go) }
        composable("14") { ChapterSummaryScreen({ nav.popBackStack() }, ::go) }
        composable("15") { AllSlokasScreen({ nav.popBackStack() }, ::go) }
        composable("16") {
            LaunchedEffect(Unit) { repository.markSlokaRead(2, 47) }
            IndividualSlokaScreen("sloka:2.47" in prefs.bookmarks, { scope.launch { repository.toggleBookmark("sloka:2.47") } }, { nav.popBackStack() }, ::go)
        }
        composable("17") { SlokaExplanationScreen({ nav.popBackStack() }, ::go) }
        composable("18") { ListenToGitaScreen({ nav.popBackStack() }, ::go) }
        composable("19") { GitaStudyModeScreen({ nav.popBackStack() }, ::go) }
        composable("20") { FaqLibraryScreen({ nav.popBackStack() }, ::go) }
        composable("21") { WhatIsDharmaScreen({ nav.popBackStack() }, ::go) }
        composable("22") { AskKrishnaScreen(prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("23") { LifeSituationsScreen({ nav.popBackStack() }, ::go) }
        composable("24") { TodayWithKrishnaScreen({ nav.popBackStack() }, ::go) }
        composable("25") { MeditationAndChantingScreen({ nav.popBackStack() }, ::go) }
        composable("26") {
            JournalScreen(
                bookmarks = prefs.bookmarks,
                reflections = prefs.reflections,
                onToggleBookmark = { id -> scope.launch { repository.toggleBookmark(id) } },
                onSaveReflection = { text -> scope.launch { repository.saveReflection(text) } },
                onDeleteReflection = { id -> scope.launch { repository.deleteReflection(id) } },
                onBack = { nav.popBackStack() },
                onNavigate = ::go
            )
        }
        composable("27") { LearningProgressScreen({ nav.popBackStack() }, ::go) }
        composable("28") {
            ProfileSettingsScreen(
                displayName = prefs.displayName,
                language = prefs.language,
                textSize = prefs.textSize,
                readingModeId = prefs.readingMode,
                notificationsEnabled = prefs.notificationsEnabled,
                notificationHour = prefs.notificationHour,
                notificationMinute = prefs.notificationMinute,
                bedtimeMessageEnabled = prefs.bedtimeMessageEnabled,
                bedtimeHour = prefs.bedtimeHour,
                bedtimeMinute = prefs.bedtimeMinute,
                darkTheme = prefs.darkTheme,
                reducedMotion = prefs.reducedMotion,
                onSaveDisplayName = { scope.launch { repository.setDisplayName(it) } },
                onLanguageChanged = { scope.launch { repository.setLanguage(it) } },
                onTextSizeChanged = { scope.launch { repository.setTextSize(it) } },
                onReadingModeChanged = { scope.launch { repository.setReadingMode(it) } },
                onNotificationsChanged = { scope.launch { repository.setNotificationsEnabled(it) } },
                onNotificationTimeChanged = { hour, minute -> scope.launch { repository.setNotificationTime(hour, minute) } },
                onBedtimeMessageChanged = { scope.launch { repository.setBedtimeMessageEnabled(it) } },
                onBedtimeChanged = { hour, minute -> scope.launch { repository.setBedtime(hour, minute) } },
                onDarkThemeChanged = { scope.launch { repository.setDarkTheme(it) } },
                onReducedMotionChanged = { scope.launch { repository.setReducedMotion(it) } },
                onResetJourney = {
                    scope.launch {
                        repository.resetJourney()
                        DailyVerseNotifications.cancel(context.applicationContext)
                        NightMessageNotifications.cancel(context.applicationContext)
                        nav.navigate("02") { popUpTo("01") { inclusive = true } }
                    }
                },
                onBack = { nav.popBackStack() },
                onNavigate = ::go
            )
        }
        composable("krishna_speaks") {
            KrishnaSpeaksScreen(
                displayName = prefs.displayName,
                selectedNeeds = prefs.interests,
                messageIndex = prefs.krishnaMessageIndex,
                onNext = { next -> scope.launch { repository.setKrishnaMessageIndex(next) } },
                bookmarked = { id -> "message:$id" in prefs.bookmarks },
                onToggleBookmark = { id -> scope.launch { repository.toggleBookmark("message:$id") } },
                onBack = { if (!nav.popBackStack()) go("05") },
                onNavigate = ::go
            )
        }
        composable("wisdom") {
            WisdomForLifeScreen(
                homeShortcuts = prefs.homeShortcuts,
                onToggleHomeShortcut = { route -> scope.launch { repository.toggleHomeShortcut(route) } },
                onBack = { nav.popBackStack() },
                onNavigate = ::go
            )
        }
        composable("emotional_intelligence") { EmotionalIntelligenceLibraryScreen(prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("emotional_intelligence/{lessonId}") { entry -> EmotionalIntelligenceLessonScreen(entry.arguments?.getString("lessonId"), prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("emotion_wheel") { EmotionWheelScreen({ nav.popBackStack() }, ::go) }
        composable("night_message") { KrishnaNightMessageScreen(prefs.displayName, { nav.popBackStack() }, ::go) }
        composable("collectibles") {
            SacredCollectiblesScreen(LearningActivity(prefs.bookmarks, prefs.reflections, prefs.readSlokas), { nav.popBackStack() }, ::go)
        }
        composable("collectible/{collectibleId}") { entry ->
            SacredCollectibleDetailsScreen(entry.arguments?.getString("collectibleId"), LearningActivity(prefs.bookmarks, prefs.reflections, prefs.readSlokas), { nav.popBackStack() }, ::go)
        }
        composable("krishna_letters") { KrishnaLettersLibraryScreen(prefs.displayName, { nav.popBackStack() }, ::go) }
        composable("krishna_letters/{letterId}") { entry -> KrishnaLetterScreen(entry.arguments?.getString("letterId"), prefs.displayName, { nav.popBackStack() }, ::go) }
        composable("one_minute_stories") { OneMinuteStoriesLibraryScreen({ nav.popBackStack() }, ::go) }
        composable("one_minute_story/{storyId}") { entry -> OneMinuteStoryScreen(entry.arguments?.getString("storyId"), { nav.popBackStack() }, ::go) }
        composable("dharma_decisions") { DharmaDecisionLibraryScreen({ nav.popBackStack() }, ::go) }
        composable("dharma_decision/{storyId}") { entry -> DharmaDecisionStoryScreen(entry.arguments?.getString("storyId"), { nav.popBackStack() }, ::go) }
        composable("difficult_questions") { DifficultQuestionsLibraryScreen({ nav.popBackStack() }, ::go) }
        composable("difficult_question/{questionId}") { entry -> DifficultQuestionScreen(entry.arguments?.getString("questionId"), { nav.popBackStack() }, ::go) }
        composable("parenting_values") { ParentingValuesLibraryScreen(prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("parenting_values/{sessionId}") { entry -> ParentingValuesSessionScreen(entry.arguments?.getString("sessionId"), prefs.readingMode, { nav.popBackStack() }, ::go) }
        composable("wisdom_theme/{themeId}") { entry -> WisdomThemeScreen(entry.arguments?.getString("themeId") ?: "mind", { nav.popBackStack() }, ::go) }
        composable("wisdom_sloka/{themeId}/{chapter}/{verse}") { entry ->
            val themeId = entry.arguments?.getString("themeId") ?: "mind"
            val chapter = entry.arguments?.getString("chapter")?.toIntOrNull() ?: 6
            val verse = entry.arguments?.getString("verse")?.toIntOrNull() ?: 5
            val bookmarkId = "sloka:$chapter.$verse"
            LaunchedEffect(chapter, verse) { repository.markSlokaRead(chapter, verse) }
            WisdomSlokaScreen(themeId, chapter, verse, bookmarkId in prefs.bookmarks, { scope.launch { repository.toggleBookmark(bookmarkId) } }, { nav.popBackStack() }, ::go)
        }
        composable("gita_summaries") { ChapterWiseSummaryScreen({ nav.popBackStack() }, ::go) }
        composable("gita_chapter/{chapter}") { entry -> CompleteChapterScreen(entry.arguments?.getString("chapter")?.toIntOrNull() ?: 1, { nav.popBackStack() }, ::go) }
        composable("gita_slokas/{chapter}") { entry -> CompleteSlokaLibraryScreen(entry.arguments?.getString("chapter")?.toIntOrNull() ?: 1, { nav.popBackStack() }, ::go) }
        composable("gita_verse/{chapter}/{verse}") { entry ->
            val chapter = entry.arguments?.getString("chapter")?.toIntOrNull() ?: 1
            val verse = entry.arguments?.getString("verse")?.toIntOrNull() ?: 1
            val bookmarkId = "sloka:$chapter.$verse"
            LaunchedEffect(chapter, verse) { repository.markSlokaRead(chapter, verse) }
            CompleteVerseReaderScreen(chapter, verse, bookmarkId in prefs.bookmarks, { scope.launch { repository.toggleBookmark(bookmarkId) } }, { nav.popBackStack() }, ::go)
        }
    }
    }
}
