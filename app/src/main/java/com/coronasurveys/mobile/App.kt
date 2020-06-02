package com.coronasurveys.mobile

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.blongho.country_data.World
import com.coronasurveys.mobile.utils.CHANNEL_ID

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        World.init(applicationContext)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}