package org.coronasurveys.mobile.presentation.items

import com.blongho.country_data.Country
import com.xwray.groupie.databinding.BindableItem
import org.coronasurveys.mobile.R
import org.coronasurveys.mobile.databinding.CountryViewBinding

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