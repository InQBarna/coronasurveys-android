package com.inqbarna.coronasurveys.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Lifecycle
import com.inqbarna.coronasurveys.databinding.ActivityMainBinding
import com.inqbarna.coronasurveys.preferences.SettingsActivity
import com.inqbarna.coronasurveys.presentation.survey.WebViewActivity
import com.inqbarna.coronasurveys.utils.*

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

        binding.teamButton.setOnClickListener { goToTeam() }
        binding.dataButton.setOnClickListener { goToGithub() }
        binding.emailButton.setOnClickListener { startEmail() }
        binding.fillSurveyButton.setOnClickListener { goToSurvey() }
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

    private fun goToTeam() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(TEAM_URL)
        binding.context.startActivity(i)
    }

    private fun goToSurvey() {
        val intent = WebViewActivity.getCallingIntent(binding.context)
        binding.context.startActivity(intent)
    }

    fun goToSettings() {
        val intent = SettingsActivity.getCallingIntent(binding.context)
        binding.context.startActivity(intent)
    }

    private fun goToGithub() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(GITHUB)
        binding.context.startActivity(i)
    }
}