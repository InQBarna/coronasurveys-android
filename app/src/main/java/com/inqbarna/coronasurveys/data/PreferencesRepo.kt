package com.inqbarna.coronasurveys.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.blongho.country_data.Country
import com.inqbarna.coronasurveys.preferences.SettingsFragment

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
}