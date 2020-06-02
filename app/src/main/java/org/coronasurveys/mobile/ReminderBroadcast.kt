package org.coronasurveys.mobile

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.coronasurveys.mobile.presentation.main.MainActivity
import org.coronasurveys.mobile.utils.CHANNEL_ID

class ReminderBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val pendingIntent = PendingIntent.getActivity(context,
            201, MainActivity.getCallingIntent(context), 0)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(context.getString(R.string.reminder_notification_title))
            .setContentText(context.getString(R.string.reminder_notification_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(200, builder.build())
    }
}