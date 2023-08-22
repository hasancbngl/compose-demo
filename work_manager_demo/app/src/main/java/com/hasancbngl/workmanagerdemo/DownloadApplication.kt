package com.hasancbngl.workmanagerdemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class DownloadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //executed only in android oreo and afterwards
            val channel = NotificationChannel(
                "download_channel",
                "File download",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }
}