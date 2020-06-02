package com.coronasurveys.mobile.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.coronasurveys.mobile.databinding.CountryViewBinding
import com.coronasurveys.mobile.presentation.countrylist.CountryListActivity
import com.coronasurveys.mobile.utils.getCountry

class CountryView @JvmOverloads constructor (
    context: Context, attrs:
    AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr), DefaultLifecycleObserver {

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
        val intent = CountryListActivity.getCallingIntent(context)
        context.startActivity(intent)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        initCountry()
    }
}