package com.example.appreparto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Productos(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nombre: String,
    val descripcion: String,
    val stock: Int,
    val categoria: String,
    val valor: Double
)