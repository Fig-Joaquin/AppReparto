package com.example.appreparto.repository

import com.example.appreparto.model.Notificacion
import java.util.*

object NotificationRepository {
    // Datos de ejemplo inyectados aquí:
    private val data = mutableListOf(
        Notificacion(1, eventId = 100, mensaje = "Evento creado correctamente", fechaHora = Date()),
        Notificacion(2, eventId = 100, mensaje = "Producto X agregado al evento", fechaHora = Date()),
        Notificacion(3, eventId = 101, mensaje = "Stock bajo en Material Y", fechaHora = Date())
    )

    /** Devuelve solo las de un evento */
    fun getByEvent(eventId: Int): List<Notificacion> =
        data.filter { it.eventId == eventId }

    /** Devuelve todas si no filtramos por evento */
    fun getAll(): List<Notificacion> =
        data.toList()

    /** Añade una nueva notificación */
    fun add(n: Notificacion) {
        data.add(n)
    }
}
