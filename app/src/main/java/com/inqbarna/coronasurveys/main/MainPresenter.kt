package com.inqbarna.coronasurveys.main

import android.content.Intent
import android.net.Uri
import com.inqbarna.coronasurveys.databinding.ActivityMainBinding
import com.inqbarna.coronasurveys.preferences.SettingsActivity
import com.inqbarna.coronasurveys.survey.WebViewActivity
import com.inqbarna.coronasurveys.utils.*

class MainPresenter(private val binding: ActivityMainBinding) {

    init {
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.plot.apply {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.databaseEnabled = true
            settings.domStorageEnabled = true
            loadUrl(PLOT_URL)
        }
        binding.teamButton.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(TEAM_URL)
            binding.context.startActivity(i)
        }

        binding.dataButton.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(GITHUB)
            binding.context.startActivity(i)
        }

        binding.emailButton.setOnClickListener {
            startEmail()
        }

        binding.fillSurveyButton.setOnClickListener {
            goToSurvey()
        }
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