package com.example.appreparto.model

data class Evento(
    val id: Long = System.currentTimeMillis(),
    var tipo: String,
    var producto: String,
    var cantidad: Int,
    var fechaHora: String,
    var usuario: String,
    var observaciones: String,
    var stockFinal: Int
)
