package org.coronasurveys.mobile.utils

import android.content.Context
import android.os.Build
import com.blongho.country_data.Country
import com.blongho.country_data.World
import org.coronasurveys.mobile.preferences.SettingsFragment

//Add special language cases where we want BCP 47 code instead of the ISO 639-1 ones
val languageExceptions = listOf("pt-BR")

fun Context.getLanguage(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val locale = resources.configuration.locales.get(0)
        if (locale.toLanguageTag() in languageExceptions)
            locale.toLanguageTag() else locale.language
    } else {
        val locale = resources.configuration.locale
        if (locale.toLanguageTag() in languageExceptions)
            locale.toLanguageTag() else locale.language
    }
}

fun Context.getCountry(): Country {
    return getCountryFromPreferences() ?: getCountryFromLocale()
}

private fun Context.getCountryFromLocale(): Country {
    val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales.get(0).country
    } else {
        resources.configuration.locale.country
    }
    return World.getCountryFrom(country)
}

private fun Context.getCountryFromPreferences(): Country? {
    val sharedPreferences = getSharedPreferences(SettingsFragment.PREFERENCES, Context.MODE_PRIVATE)
    val country = sharedPreferences.getString(SettingsFragment.COUNTRY, null)
    return country?.let { World.getCountryFrom(it) }
}