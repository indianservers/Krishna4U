package com.indianservers.krishna4u.core.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.indianservers.krishna4u.MainActivity
import com.indianservers.krishna4u.R
import com.indianservers.krishna4u.data.local.PreferencesRepository
import com.indianservers.krishna4u.data.local.UserPreferences
import com.indianservers.krishna4u.data.repository.OfflineGitaRepository
import java.util.Calendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private const val CHANNEL_ID = "daily_gita_wisdom"
private const val NOTIFICATION_ID = 108
private const val ALARM_REQUEST_CODE = 108

object DailyVerseNotifications {
    fun sync(context: Context, preferences: UserPreferences) {
        createChannel(context)
        if (preferences.notificationsEnabled) {
            schedule(context, preferences.notificationHour, preferences.notificationMinute)
        } else {
            cancel(context)
        }
    }

    fun schedule(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.cancel(alarmIntent(context))
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextOccurrence(hour, minute),
            alarmIntent(context)
        )
    }

    fun cancel(context: Context) {
        context.getSystemService(AlarmManager::class.java).cancel(alarmIntent(context))
    }

    fun showVerse(context: Context) {
        createChannel(context)
        val importantVerses = listOf(
            2 to 14, 2 to 47, 2 to 50, 3 to 19, 3 to 21, 4 to 7, 4 to 38,
            5 to 18, 6 to 5, 6 to 26, 9 to 22, 9 to 34, 12 to 13, 12 to 15,
            15 to 7, 16 to 1, 18 to 47, 18 to 66
        )
        val reference = importantVerses.random()
        val verse = OfflineGitaRepository(context).verse(reference.first, reference.second) ?: return
        val openApp = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Bhagavad Gita ${verse.chapter}.${verse.verse} · Today’s wisdom")
            .setContentText(verse.englishSummary)
            .setStyle(NotificationCompat.BigTextStyle().bigText(verse.englishSummary))
            .setColor(0xFFE9B84A.toInt())
            .setContentIntent(openApp)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        } catch (_: SecurityException) {
            // Android 13+ can deny notification permission; Settings lets the user try again.
        }
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Daily Gita wisdom",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "A locally selected Bhagavad Gita verse at your chosen time" }
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun alarmIntent(context: Context) = PendingIntent.getBroadcast(
        context,
        ALARM_REQUEST_CODE,
        Intent(context, DailyVerseReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    private fun nextOccurrence(hour: Int, minute: Int): Long {
        val now = Calendar.getInstance()
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (!after(now)) add(Calendar.DAY_OF_YEAR, 1)
        }.timeInMillis
    }
}

class DailyVerseReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val preferences = PreferencesRepository(context.applicationContext).preferences.first()
                if (preferences.notificationsEnabled) {
                    DailyVerseNotifications.showVerse(context.applicationContext)
                    DailyVerseNotifications.schedule(
                        context.applicationContext,
                        preferences.notificationHour,
                        preferences.notificationMinute
                    )
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}

class VerseBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val preferences = PreferencesRepository(context.applicationContext).preferences.first()
                DailyVerseNotifications.sync(context.applicationContext, preferences)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
