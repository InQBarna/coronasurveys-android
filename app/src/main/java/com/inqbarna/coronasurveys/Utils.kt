package com.inqbarna.coronasurveys

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.ReminderFrequency
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.ReminderFrequency.*
import java.util.*

object Utils {

    fun createAlarm(context: Context, freq: ReminderFrequency) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, ReminderBroadcast::class.java)

        //Needs to be always constructed with same params, so it can be cancelled
        val pendingIntent: PendingIntent
                = PendingIntent.getBroadcast(context, 0, intent, 0)

        when (freq) {
            OFF -> alarmManager?.cancel(pendingIntent)
            DAILY, WEEKLY -> alarmManager?.setupAlarm(freq, pendingIntent)
        }

    }

    private fun AlarmManager.setupAlarm(freq: ReminderFrequency, pendingIntent: PendingIntent) {
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            if (freq == DAILY) AlarmManager.INTERVAL_DAY else AlarmManager.INTERVAL_DAY * 7,
            pendingIntent
        )
    }
}