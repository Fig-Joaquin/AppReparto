package com.example.appreparto.repository

import com.example.appreparto.model.Evento

object EventoRepository {
    private val eventos = mutableListOf<Evento>()

    fun getAll(): List<Evento> = eventos

    fun save(evento: Evento) {
        val index = eventos.indexOfFirst { it.id == evento.id }
        if (index != -1) {
            eventos[index] = evento
        } else {
            eventos.add(evento)
        }
    }

    fun delete(id: Long) {
        eventos.removeAll { it.id == id }
    }

    fun getById(id: Long): Evento? {
        return eventos.find { it.id == id }
    }
}
