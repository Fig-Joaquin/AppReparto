package com.example.appreparto.repository

import com.example.appreparto.model.Material

object MaterialRepository {           // ←  ahora es singleton
    private val list = mutableListOf<Material>()
    fun getAll(): List<Material> = list
    fun save(m: Material) { list.removeAll { it.id == m.id }; list.add(m) }
    fun delete(id: Long) { list.removeAll { it.id == id } }
}

