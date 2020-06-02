package org.coronasurveys.mobile.presentation.countrylist

import com.blongho.country_data.Country
import com.blongho.country_data.World
import org.coronasurveys.mobile.presentation.adapters.BasicAdapter
import org.coronasurveys.mobile.presentation.items.CountryItem
import org.coronasurveys.mobile.data.PreferencesRepo
import org.coronasurveys.mobile.databinding.ActivityCountryListBinding
import org.coronasurveys.mobile.utils.RecyclerUtils

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
        RecyclerUtils.setAdapter(binding.contryListRecycler, adapter, itemMargin = 8)
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