package com.udacity

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(
    messageBody: String,
    applicationContext: Context,
    fileDownloaded: String
) {
    val contentIntent = DetailActivity.getStartIntent(applicationContext,fileDownloaded)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.load_app_channel_id)
    )
        .setSmallIcon(R.drawable.ic_assistant_black_24dp)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .addAction(
            R.drawable.ic_assistant_black_24dp,
            "Check the status",
            contentPendingIntent
        )
        .setContentText(messageBody)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    notify(NOTIFICATION_ID, builder.build())
}