package com.example.appreparto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Se reutiliza tu modelo existente como entidad de Room.
 * `fechaHora` se guarda como epoch millis (Long) para simplicidad.
 * `notified` evita reenviar la misma notificación dos veces.
 */
@Entity(tableName = "notificaciones")
data class Notificacion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val eventId: Int,
    val mensaje: String,
    val fechaHora: Long, // epoch millis
    val notified: Boolean = false
)
