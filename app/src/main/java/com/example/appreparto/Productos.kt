package com.example.appreparto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Productos(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
    val unidad: String,
    val valorUnitario: Double
)
