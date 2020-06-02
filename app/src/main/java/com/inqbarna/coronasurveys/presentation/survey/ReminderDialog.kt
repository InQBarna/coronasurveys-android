package com.inqbarna.coronasurveys.presentation.survey

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.inqbarna.coronasurveys.R
import com.inqbarna.coronasurveys.preferences.SettingsFragment.Companion.ReminderFrequency
import com.inqbarna.coronasurveys.data.PreferencesRepo
import com.inqbarna.coronasurveys.utils.createAlarm

class ReminderDialog: DialogFragment() {

    companion object {
        const val TAG = "ReminderDialog"
    }

    lateinit var preferencesRepo: PreferencesRepo

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        preferencesRepo =
            PreferencesRepo(requireContext())
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
        preferencesRepo.saveReminderFreq(frequency)
        createAlarm(requireContext(), frequency)
    }


}