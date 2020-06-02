package com.inqbarna.coronasurveys.presentation.countrylist

import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.inqbarna.coronasurveys.databinding.ActivityCountryListBinding
import com.inqbarna.coronasurveys.presentation.adapters.BasicAdapter
import com.inqbarna.coronasurveys.presentation.items.CountryItem
import com.inqbarna.coronasurveys.data.PreferencesRepo
import com.inqbarna.coronasurveys.utils.RecyclerUtils

class CountryListPresenter(
    private val activity: CountryListActivity,
    private val binding: ActivityCountryListBinding,
    private val adapter: BasicAdapter
) : CountryItem.OnCLickListener {

    private val preferencesRepo = PreferencesRepo(activity)

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
        preferencesRepo.saveCountry(country)
        activity.finish()
    }
}