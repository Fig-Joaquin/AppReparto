package com.example.appreparto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appreparto.model.Evento
import com.example.appreparto.repository.EventoRepository

class EventoViewModel : ViewModel() {
    private val _eventos = MutableLiveData<List<Evento>>()
    val eventos: LiveData<List<Evento>> = _eventos

    fun loadAll() {
        _eventos.value = EventoRepository.getAll()
    }

    fun save(evento: Evento) {
        EventoRepository.save(evento)
        loadAll()
    }

    fun delete(id: Long) {
        EventoRepository.delete(id)
        loadAll()
    }

    fun getById(id: Long): Evento? {
        return EventoRepository.getById(id)
    }
}