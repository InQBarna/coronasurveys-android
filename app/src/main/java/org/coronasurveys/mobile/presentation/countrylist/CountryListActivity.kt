package org.coronasurveys.mobile.presentation.countrylist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.coronasurveys.mobile.R
import org.coronasurveys.mobile.databinding.ActivityCountryListBinding
import org.coronasurveys.mobile.presentation.adapters.BasicAdapter

class CountryListActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, CountryListActivity::class.java)
        }
    }

    private val adapter = BasicAdapter()
    private lateinit var presenter: CountryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.select_country)
        val binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CountryListPresenter(this, binding, adapter)
    }
}