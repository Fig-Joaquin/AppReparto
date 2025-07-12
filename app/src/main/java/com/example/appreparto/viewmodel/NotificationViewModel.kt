package com.example.appreparto.viewmodel

import androidx.lifecycle.*
import com.example.appreparto.model.Notificacion
import com.example.appreparto.repository.NotificationRepository

class NotificationViewModel(private val eventId: Int) : ViewModel() {
    private val _notificaciones = MutableLiveData<List<Notificacion>>()
    val notificaciones: LiveData<List<Notificacion>> = _notificaciones

    init {
        load()
    }

    /** Carga inicial o recarga */
    fun load() {
        _notificaciones.value =
            if (eventId >= 0) NotificationRepository.getByEvent(eventId)
            else                  NotificationRepository.getAll()
    }

    /** Refresh manual */
    fun refresh() = load()
}

class NotificationViewModelFactory(private val eventId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotificationViewModel(eventId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
