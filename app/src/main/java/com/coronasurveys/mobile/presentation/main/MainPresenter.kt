package com.coronasurveys.mobile.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Lifecycle
import com.coronasurveys.mobile.databinding.ActivityMainBinding
import com.coronasurveys.mobile.preferences.SettingsActivity
import com.coronasurveys.mobile.presentation.survey.WebViewActivity
import com.coronasurveys.mobile.utils.*

class MainPresenter (
    private val binding: ActivityMainBinding,
    private val lifecycle: Lifecycle
) {

    init {
        setUpListeners()
    }

    private fun setUpListeners() {
        lifecycle.addObserver(binding.countryView)
        binding.plot.configure(PLOT_URL)

        binding.emailButton.setOnClickListener { startEmail() }
        binding.fillSurveyButton.setOnClickListener { goToSurvey() }

        binding.teamButton.setOnClickListener { binding.context.openUrl(TEAM_URL) }
        binding.dataButton.setOnClickListener { binding.context.openUrl(GITHUB) }
        binding.facebook.setOnClickListener { binding.context.openUrl(FACEBOOK) }
        binding.twitter.setOnClickListener { binding.context.openUrl(TWITTER) }
        binding.instagram.setOnClickListener { binding.context.openUrl(INSTAGRAM) }
    }

    private fun startEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            val mailto = "mailto:$CONTACT_EMAIL"
            data = Uri.parse(mailto)
        }
        if (intent.resolveActivity(binding.context.packageManager) != null) {
            binding.context.startActivity(intent)
        }
    }

    private fun goToSurvey() {
        val intent = WebViewActivity.getCallingIntent(binding.context)
        binding.context.startActivity(intent)
    }

    fun goToSettings() {
        val intent = SettingsActivity.getCallingIntent(binding.context)
        binding.context.startActivity(intent)
    }

}