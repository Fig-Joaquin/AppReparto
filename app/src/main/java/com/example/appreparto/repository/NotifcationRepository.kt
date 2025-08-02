package com.example.appreparto.repository

import android.content.Context
import com.example.appreparto.data.AppDatabase
import com.example.appreparto.model.Notificacion

class NotificationRepository(context: Context) {
    private val dao = AppDatabase.getInstance(context).notificationDao()

    suspend fun getByEvent(eventId: Int): List<Notificacion> =
        dao.getByEvent(eventId)

    suspend fun getAll(): List<Notificacion> =
        dao.getAll()

    suspend fun add(notification: Notificacion) {
        dao.insert(notification)
    }

    suspend fun getPendingToNotify(nowMillis: Long): List<Notificacion> =
        dao.getPendingToNotify(nowMillis)

    suspend fun markNotified(id: Long) {
        dao.markNotified(id)
    }
}
