package com.coronasurveys.mobile.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.webkit.WebView
import androidx.viewbinding.ViewBinding
import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.coronasurveys.mobile.ReminderBroadcast
import com.coronasurveys.mobile.preferences.SettingsFragment.Companion.COUNTRY
import com.coronasurveys.mobile.preferences.SettingsFragment.Companion.PREFERENCES
import com.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency
import com.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency.*
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

fun Context.getLanguage(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales.get(0).language
    } else {
        resources.configuration.locale.language
    }
}

fun Context.getCountry(): Country {
    return getCountryFromPreferences() ?: getCountryFromLocale()
}

private fun Context.getCountryFromLocale(): Country {
    val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales.get(0).country
    } else {
        resources.configuration.locale.country
    }
    return World.getCountryFrom(country)
}

private fun Context.getCountryFromPreferences(): Country? {
    val sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE)
    val country = sharedPreferences.getString(COUNTRY, null)
    return country?.let { World.getCountryFrom(it) }
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