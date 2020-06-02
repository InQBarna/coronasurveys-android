package com.inqbarna.coronasurveys

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.inqbarna.coronasurveys.databinding.CountryViewBinding
import com.inqbarna.coronasurveys.utils.getCountry

class CountryView @JvmOverloads constructor (
    context: Context, attrs:
    AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CountryViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = CountryViewBinding.inflate(inflater, this, true)
        binding.rootCountryView.setOnClickListener { onClick() }
        initCountry()
    }

    private fun initCountry() {
        val country = context.getCountry()
        binding.countryFlag.setImageResource(country.flagResource)
        binding.countryName.text = country.name
    }

    private fun onClick() {
        Log.d("TAG", "Open activity")
    }


}