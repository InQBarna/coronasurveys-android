package com.inqbarna.coronasurveys

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.inqbarna.coronasurveys.utils.CHANNEL_ID

class ReminderBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_baseline_settings_24)
            .setContentTitle("Reminder survey")
            .setContentText("Please fill the survey")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).notify(200, builder.build())
    }
}