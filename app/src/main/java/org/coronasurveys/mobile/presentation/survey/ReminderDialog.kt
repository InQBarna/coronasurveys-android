package org.coronasurveys.mobile.presentation.survey

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import org.coronasurveys.mobile.R
import org.coronasurveys.mobile.data.PreferencesRepo
import org.coronasurveys.mobile.preferences.SettingsFragment.Companion.ReminderFrequency
import org.coronasurveys.mobile.utils.createAlarm

class ReminderDialog: DialogFragment(), DialogInterface.OnClickListener {

    companion object {
        const val TAG = "ReminderDialog"
    }

    private lateinit var preferencesRepo: PreferencesRepo

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        preferencesRepo =
            PreferencesRepo(requireContext())
        return AlertDialog.Builder(requireContext())
            .setTitle(requireContext().getString(R.string.add_reminder_title))
            .setMessage(requireContext().getString(R.string.add_reminder_text))
            .setPositiveButton(requireContext().getString(R.string.daily_reminder), this)
            .setNegativeButton(requireContext().getString(R.string.weekly_reminder), this)
            .setNeutralButton(requireContext().getString(R.string.reminder_disabled), this)
            .create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        val freq = when (which) {
            BUTTON_POSITIVE -> ReminderFrequency.DAILY
            BUTTON_NEGATIVE ->ReminderFrequency.WEEKLY
            else -> ReminderFrequency.OFF
        }
        savePreferences(freq)
        dialog.dismiss()
        activity?.finish()
    }

    private fun savePreferences(frequency: ReminderFrequency) {
        preferencesRepo.saveReminderFreq(frequency)
        createAlarm(requireContext(), frequency)
    }
}