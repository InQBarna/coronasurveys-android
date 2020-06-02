package com.inqbarna.coronasurveys

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.inqbarna.coronasurveys.presentation.main.MainActivity
import com.inqbarna.coronasurveys.utils.CHANNEL_ID

class ReminderBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val pendingIntent = PendingIntent.getActivity(context,
            201, MainActivity.getCallingIntent(context), 0)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Reminder survey")
            .setContentText("Please fill the survey")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(200, builder.build())
    }
}