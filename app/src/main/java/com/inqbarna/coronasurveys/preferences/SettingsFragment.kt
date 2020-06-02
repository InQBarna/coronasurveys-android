package com.inqbarna.coronasurveys.preferences

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.inqbarna.coronasurveys.R
import com.inqbarna.coronasurveys.Utils

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = PREFERENCES
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == REMINDER_PREF) {
            val newValue = sharedPreferences.getString(REMINDER_PREF, null)
            val freq = ReminderFrequency.valueOf(newValue!!)
            Utils.createAlarm(requireContext(), freq)
        }
    }

    companion object {
        const val PREFERENCES = "PREFERENCES"
        const val REMINDER_PREF = "prefReminder"
        enum class ReminderFrequency {
            OFF, DAILY, WEEKLY
        }
    }
}