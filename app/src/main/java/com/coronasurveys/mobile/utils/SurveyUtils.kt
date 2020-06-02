package com.coronasurveys.mobile.utils

import com.blongho.country_data.Country
import com.coronasurveys.mobile.BuildConfig

object SurveyUtils {

    fun getSurveyUrl(country: Country, language: String): String {
        return if (BuildConfig.DEBUG) {
            BuildConfig.SURVEY_URL
        } else {
            composeUrl(country, language)
        }
    }

    private fun composeUrl(country: Country, language: String): String {
        return BuildConfig.SURVEY_URL
            .replace("xx", country.alpha2)
            .replace("yy", language)
    }
}

