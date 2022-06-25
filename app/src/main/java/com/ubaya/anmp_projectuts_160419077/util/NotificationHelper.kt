package com.ubaya.anmp_projectuts_160419077.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "kost_channel_id"
    private val NOTIFICATION_ID = 1

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager
                .IMPORTANCE_DEFAULT).apply {
                description = "Kost channel description"
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title:String, message:String) {
        createNotificationChannel()
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.rent)

        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.kostwithkey)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                .bigPicture(icon)
                .bigLargeIcon(null))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)
    }
}