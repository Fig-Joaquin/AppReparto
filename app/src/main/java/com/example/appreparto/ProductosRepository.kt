package com.example.appreparto

object ProductosRepository {
    private val list = mutableListOf<Productos>()
    fun getAll(): List<Productos> = list
    fun save(p: Productos) { list.removeAll { it.id == p.id }; list.add(p) }
    fun delete(id: Long) { list.removeAll { it.id == id } }
}


