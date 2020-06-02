package org.coronasurveys.mobile.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.blongho.country_data.Country
import org.coronasurveys.mobile.preferences.SettingsFragment
import org.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency.DAILY
import org.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency.WEEKLY

class PreferencesRepo(val context: Context) {

    private val sharedPreferences: SharedPreferences
            = context.getSharedPreferences(SettingsFragment.PREFERENCES, MODE_PRIVATE)

    fun saveCountry(country: Country) {
        sharedPreferences.edit()
            .putString(SettingsFragment.COUNTRY, country.alpha2)
            .apply()
    }

    fun saveReminderFreq(freq: SettingsFragment.Companion.ReminderFrequency) {
        sharedPreferences.edit()
            .putString(SettingsFragment.REMINDER_PREF, freq.toString())
            .apply()
    }

    fun isReminderSet(): Boolean {
        val reminder= sharedPreferences.getString(SettingsFragment.REMINDER_PREF, null)
        return !(reminder != WEEKLY.toString() &&
                reminder != DAILY.toString())
    }
}