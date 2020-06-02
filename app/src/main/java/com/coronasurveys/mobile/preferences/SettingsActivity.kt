package com.coronasurveys.mobile.preferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coronasurveys.mobile.R

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, SettingsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}