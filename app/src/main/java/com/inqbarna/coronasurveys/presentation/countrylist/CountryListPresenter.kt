package com.inqbarna.coronasurveys.presentation.countrylist

import android.content.Context
import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.inqbarna.coronasurveys.databinding.ActivityCountryListBinding
import com.inqbarna.coronasurveys.preferences.SettingsFragment
import com.inqbarna.coronasurveys.presentation.adapters.BasicAdapter
import com.inqbarna.coronasurveys.utils.RecyclerUtils
import com.inqbarna.coronasurveys.presentation.items.CountryItem
import com.inqbarna.coronasurveys.utils.context

class CountryListPresenter(
    private val activity: CountryListActivity,
    private val binding: ActivityCountryListBinding,
    private val adapter: BasicAdapter
)
    : CountryItem.OnCLickListener {

    init {
        setupList()
    }

    private fun setupList() {
        RecyclerUtils.setAdapter(binding.contryListRecycler, adapter)
        adapter.update(
            World.getAllCountries()
            .sortedBy { it.name }
            .map { CountryItem(it, this) })
    }

    override fun onClick(country: Country) {
        val sharedPreferences
                = binding.context.getSharedPreferences(SettingsFragment.PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(SettingsFragment.COUNTRY, country.alpha2)
            .apply()
        activity.finish()
    }
}