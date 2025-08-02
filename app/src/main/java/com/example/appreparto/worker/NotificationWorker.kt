package com.example.appreparto.worker

import android.Manifest
import android.content.Context
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.appreparto.R
import com.example.appreparto.activity.NotificationActivity
import com.example.appreparto.model.Notificacion
import com.example.appreparto.repository.NotificationRepository

class NotificationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val repo = NotificationRepository(context)

    override suspend fun doWork(): Result {
        Log.d(TAG, "Inicio doWork: buscando notificaciones pendientes")
        return try {
            val now = System.currentTimeMillis()
            val pending: List<Notificacion> = repo.getPendingToNotify(now)
            Log.d(TAG, "Notificaciones encontradas: ${pending.size}")

            for (n in pending) {
                showNotification(n)
                repo.markNotified(n.id)
                Log.d(TAG, "Notificación enviada y marcada: ${n.id}")
            }

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error en worker", e)
            Result.retry()
        }
    }

    private fun showNotification(n: Notificacion) {
        val channelId = "CANAL_NOTIF"
        val notificationId = (n.id % Int.MAX_VALUE).toInt()
        val nm = NotificationManagerCompat.from(applicationContext)

        // Verificar permiso en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                Log.w(TAG, "No tiene permiso POST_NOTIFICATIONS, se omite notificación ${n.id}")
                return
            }
        }

        // Si las notificaciones están deshabilitadas globalmente, no forzar
        if (!nm.areNotificationsEnabled()) {
            Log.w(TAG, "Notificaciones deshabilitadas en sistema, omitiendo ${n.id}")
            return
        }

        val intent = Intent(applicationContext, NotificationActivity::class.java).apply {
            putExtra("eventId", n.eventId)
            putExtra("mensaje", n.mensaje)
            putExtra("fechaHora", n.fechaHora)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntentFlags =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            else
                PendingIntent.FLAG_UPDATE_CURRENT

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            notificationId,
            intent,
            pendingIntentFlags
        )

        // Usa el ícono esperado; asegúrate de que ic_notification exista. Si no, cambia aquí.
        val smallIcon = R.drawable.ic_notification

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(smallIcon)
            .setContentTitle("Recordatorio")
            .setContentText(n.mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        nm.notify(notificationId, builder.build())
    }

    companion object {
        private const val TAG = "NotificationWorker"
    }
}
