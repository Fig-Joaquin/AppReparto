package com.example.appreparto

public final data class Productos(
    val id: Long = System.currentTimeMillis(),
    var nombre: String,
    var descripcion: String,
    var cantidad: Int,
    var unidad: String,
    var valorUnitario: Double
)
