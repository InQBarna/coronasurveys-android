package com.inqbarna.coronasurveys

import android.app.Application
import com.blongho.country_data.World

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        World.init(applicationContext)
    }
}