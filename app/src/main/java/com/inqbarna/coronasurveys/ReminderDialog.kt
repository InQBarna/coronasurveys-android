package com.inqbarna.coronasurveys

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.inqbarna.coronasurveys.preferences.SettingsFragment

class ReminderDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = requireContext().resources.getStringArray(R.array.reminder_entries)
        return AlertDialog.Builder(requireContext())
            .setMessage("Quieres que te recordemos el survey?")
            .setSingleChoiceItems(choices, - 1, ::onSelection)
            .create()
    }

    private fun onSelection(dialogInterface: DialogInterface, index: Int) {
        val selection = requireContext().resources.getStringArray(R.array.reminder_values)[index]
        val freq = SettingsFragment.Companion.ReminderFrequency.valueOf(selection)
        Utils.createAlarm(requireContext(), freq)
        dialogInterface.dismiss()
    }

}