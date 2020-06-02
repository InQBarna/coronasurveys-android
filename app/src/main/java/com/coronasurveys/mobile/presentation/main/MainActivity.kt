package com.coronasurveys.mobile.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.coronasurveys.mobile.R
import com.coronasurveys.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, MainActivity::class.java)
        }
    }

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(binding, lifecycle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> presenter.goToSettings()
        }
        return true
    }

}