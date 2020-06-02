package com.inqbarna.coronasurveys.presentation.countrylist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inqbarna.coronasurveys.databinding.ActivityCountryListBinding
import com.inqbarna.coronasurveys.presentation.adapters.BasicAdapter

class CountryListActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, CountryListActivity::class.java)
        }
    }

    private val adapter =
        BasicAdapter()
    lateinit var presenter: CountryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter =
            CountryListPresenter(
                this,
                binding,
                adapter
            )
    }
}