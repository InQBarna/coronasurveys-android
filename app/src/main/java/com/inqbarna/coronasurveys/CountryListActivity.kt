package com.inqbarna.coronasurveys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blongho.country_data.World
import com.inqbarna.coronasurveys.databinding.ActivityCountryListBinding
import com.inqbarna.coronasurveys.databinding.ActivityMainBinding
import com.inqbarna.coronasurveys.presentation.BasicAdapter
import com.inqbarna.coronasurveys.presentation.RecyclerUtils
import com.inqbarna.coronasurveys.presentation.items.CountryItem
import com.inqbarna.coronasurveys.survey.WebViewActivity

class CountryListActivity : AppCompatActivity() {

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, CountryListActivity::class.java)
        }
    }

    private val adapter = BasicAdapter()
    lateinit var presenter: CountryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CountryListPresenter(this, binding, adapter)
    }
}