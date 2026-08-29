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
import com.indianservers.krishna4u.feature.night.krishnaNightMessages
import java.time.LocalDate
import java.util.Calendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private const val NIGHT_CHANNEL_ID = "krishna_night_messages"
private const val NIGHT_NOTIFICATION_ID = 109
private const val NIGHT_ALARM_REQUEST_CODE = 109

object NightMessageNotifications {
    fun sync(context: Context, preferences: UserPreferences) {
        createChannel(context)
        if (preferences.bedtimeMessageEnabled) schedule(context, preferences.bedtimeHour, preferences.bedtimeMinute)
        else cancel(context)
    }

    fun schedule(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.cancel(alarmIntent(context))
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextOccurrence(hour, minute), alarmIntent(context))
    }

    fun cancel(context: Context) {
        context.getSystemService(AlarmManager::class.java).cancel(alarmIntent(context))
    }

    fun show(context: Context, displayName: String) {
        createChannel(context)
        val message = krishnaNightMessages[(LocalDate.now().dayOfYear - 1) % krishnaNightMessages.size]
        val name = displayName.trim().ifBlank { "Friend" }
        val openNightMessage = PendingIntent.getActivity(
            context,
            NIGHT_ALARM_REQUEST_CODE,
            Intent(context, MainActivity::class.java)
                .putExtra(MainActivity.DESTINATION_EXTRA, "night_message")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, NIGHT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Good night, $name · ${message.title}")
            .setContentText(message.message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message.message))
            .setColor(0xFFE9B84A.toInt())
            .setContentIntent(openNightMessage)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        try {
            NotificationManagerCompat.from(context).notify(NIGHT_NOTIFICATION_ID, notification)
        } catch (_: SecurityException) {
            // Android 13+ may deny notification permission.
        }
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NIGHT_CHANNEL_ID, "Krishna’s night message", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "A calm, locally stored Krishna-inspired reflection at your chosen bedtime"
            }
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun alarmIntent(context: Context) = PendingIntent.getBroadcast(
        context,
        NIGHT_ALARM_REQUEST_CODE,
        Intent(context, NightMessageReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    private fun nextOccurrence(hour: Int, minute: Int): Long {
        val now = Calendar.getInstance()
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour.coerceIn(0, 23))
            set(Calendar.MINUTE, minute.coerceIn(0, 59))
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (!after(now)) add(Calendar.DAY_OF_YEAR, 1)
        }.timeInMillis
    }
}

class NightMessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val preferences = PreferencesRepository(context.applicationContext).preferences.first()
                if (preferences.bedtimeMessageEnabled) {
                    NightMessageNotifications.show(context.applicationContext, preferences.displayName)
                    NightMessageNotifications.schedule(context.applicationContext, preferences.bedtimeHour, preferences.bedtimeMinute)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}
