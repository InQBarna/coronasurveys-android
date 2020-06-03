package org.coronasurveys.mobile.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.webkit.WebView
import androidx.viewbinding.ViewBinding
import org.coronasurveys.mobile.ReminderBroadcast
import org.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency
import org.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency.*
import java.util.*

val ViewBinding.context: Context get() = root.context

fun Int.toPx(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
    context.resources.displayMetrics).toInt()

fun Context.openUrl(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}

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
        add(Calendar.DATE, if (freq == DAILY) 1 else 7)
    }

    setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        if (freq == DAILY) AlarmManager.INTERVAL_DAY else AlarmManager.INTERVAL_DAY * 7,
        pendingIntent
    )
}

fun WebView.configure(url: String? = null): WebView = this.apply {
    settings.javaScriptEnabled = true
    settings.useWideViewPort = true
    settings.loadWithOverviewMode = true
    settings.javaScriptCanOpenWindowsAutomatically = true
    settings.databaseEnabled = true
    settings.domStorageEnabled = true
    url?.let { loadUrl(it) }
}