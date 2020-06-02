package com.inqbarna.coronasurveys.presentation.items

import com.blongho.country_data.Country
import com.inqbarna.coronasurveys.R
import com.inqbarna.coronasurveys.databinding.CountryViewBinding
import com.xwray.groupie.databinding.BindableItem

class CountryItem (private val country: Country): BindableItem<CountryViewBinding>() {

    override fun getId(): Long {
        return country.id.toLong()
    }

    override fun getLayout(): Int = R.layout.country_view

    override fun bind(viewBinding: CountryViewBinding, position: Int) {
        viewBinding.countryFlag.setImageResource(country.flagResource)
        viewBinding.countryName.text = country.name
    }
}