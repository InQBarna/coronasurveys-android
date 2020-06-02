package com.coronasurveys.mobile.presentation.items

import com.blongho.country_data.Country
import com.coronasurveys.mobile.R
import com.coronasurveys.mobile.databinding.CountryViewBinding
import com.xwray.groupie.databinding.BindableItem

class CountryItem (private val country: Country, private val listener: OnCLickListener)
    : BindableItem<CountryViewBinding>() {

    override fun getId(): Long {
        return country.id.toLong()
    }

    override fun getLayout(): Int = R.layout.country_view

    override fun bind(viewBinding: CountryViewBinding, position: Int) {
        viewBinding.countryFlag.setImageResource(country.flagResource)
        viewBinding.countryName.text = country.name
        viewBinding.rootCountryView.setOnClickListener { listener.onClick(country) }
    }

    interface OnCLickListener {
        fun onClick(country: Country)
    }
}