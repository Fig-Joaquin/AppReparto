package com.example.appreparto.model

import java.util.Date

data class Notificacion(
    val id: Int,
    val eventId: Int,
    val mensaje: String,
    val fechaHora: Date
)
