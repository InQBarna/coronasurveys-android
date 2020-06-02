package com.inqbarna.coronasurveys

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.PREFERENCES
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.REMINDER_PREF
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.ReminderFrequency

class ReminderDialog: DialogFragment() {

    companion object {
        const val TAG = "ReminderDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = requireContext().resources.getStringArray(R.array.reminder_entries)
        return AlertDialog.Builder(requireContext())
            .setTitle("Quieres que te recordemos el survey?")
            .setSingleChoiceItems(choices, - 1, ::onSelection)
            .create()
    }

    private fun onSelection(dialogInterface: DialogInterface, index: Int) {
        val selection = requireContext().resources.getStringArray(R.array.reminder_values)[index]
        val freq = ReminderFrequency.valueOf(selection)
        savePreferences(freq)
        dialogInterface.dismiss()
    }

    private fun savePreferences(frequency: ReminderFrequency) {
        val sharedPreferences = requireContext().getSharedPreferences(PREFERENCES, MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(REMINDER_PREF, frequency.toString())
            .apply()
        Utils.createAlarm(requireContext(), frequency)
    }

}