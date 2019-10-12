package com.example.scheduleajob

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationJobService : JobService() {

    private lateinit var manager: NotificationManager
    private val CHANNEL_ID = "channel_id"

    companion object {
        val NOTIFICATION_ACTION = "LETS DOO IT"
    }


    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.job_service))
            .setContentText(getString(R.string.job_running))
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.job_running)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)

        manager.notify(12, builder.build())

        return false
    }

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        return false
    }

    private fun createNotificationChannel(){

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,getString(R.string.job_service_notification), NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = Color.RED
            channel.description = getString(R.string.notification_channel_description)
            manager.createNotificationChannel(channel)
        }
    }
}