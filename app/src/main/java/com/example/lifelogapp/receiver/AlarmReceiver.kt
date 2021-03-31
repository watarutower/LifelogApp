package com.example.lifelogapp.receiver

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import android.widget.Toast
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import com.example.lifelogapp.R
import com.example.lifelogapp.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       Toast.makeText(context, context.getText(R.string.text_test), Toast.LENGTH_SHORT). show()

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            context.getText(R.string.text_test).toString(),
            context
        )
    }
}
//val triggerTime = SystemClock.elapsedRealtime() + DateUtils.MINUTE_IN_MILLIS
//        val notifyIntent = Intent(context, AlarmReceiver::class.java)
//        val notifyPendingIntent = PendingIntent.getBroadcast(
//            context,
//            REQUEST_CODE,
//            notifyIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        AlarmManagerCompat.setExactAndAllowWhileIdle(
//            alarmManager,
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            triggerTime,
//            notifyPendingIntent
//        )