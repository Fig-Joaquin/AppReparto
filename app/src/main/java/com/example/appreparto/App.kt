package com.example.appreparto

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.appreparto.worker.NotificationWorker
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        scheduleNotificationChecker()
    }

    private fun scheduleNotificationChecker() {
        Log.d("App", "Agendando NotificationWorker periódico")
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val work = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                30_000L, // reintento inicial tras 30 segundos si falla
                TimeUnit.MILLISECONDS
            )
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "notification_checker",
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val name = "Alertas"
            val description = "Canal para notificaciones de tareas/eventos"
            val importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
            val channel = android.app.NotificationChannel("CANAL_NOTIF", name, importance).apply {
                this.description = description
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
