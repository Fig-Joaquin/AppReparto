package com.example.appreparto.data

import androidx.room.*
import com.example.appreparto.model.Notificacion

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notificaciones WHERE eventId = :eventId ORDER BY fechaHora DESC")
    suspend fun getByEvent(eventId: Int): List<Notificacion>

    @Query("SELECT * FROM notificaciones WHERE notified = 0 AND fechaHora <= :threshold")
    suspend fun getPendingToNotify(threshold: Long): List<Notificacion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: Notificacion)

    @Update
    suspend fun update(notification: Notificacion)

    @Query("UPDATE notificaciones SET notified = 1 WHERE id = :id")
    suspend fun markNotified(id: Long)

    @Query("SELECT * FROM notificaciones ORDER BY fechaHora DESC")
    suspend fun getAll(): List<Notificacion>
}
