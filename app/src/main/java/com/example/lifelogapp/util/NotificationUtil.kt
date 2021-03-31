package com.example.lifelogapp.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.lifelogapp.MainActivity
import com.example.lifelogapp.R

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.lifelog_app_id)
    )
        .setSmallIcon(R.drawable.ic_sentiment_satisfied_24px)
        .setContentTitle(applicationContext
            .getString(R.string.lifelogapp))
        .setContentText(messageBody)

    notify(NOTIFICATION_ID, builder.build())
}