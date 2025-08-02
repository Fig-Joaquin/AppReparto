package com.example.appreparto.viewmodel

import androidx.lifecycle.*
import com.example.appreparto.model.Notificacion
import com.example.appreparto.repository.NotificationRepository
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val repo: NotificationRepository,
    private val eventId: Int
) : ViewModel() {

    private val _notificaciones = MutableLiveData<List<Notificacion>>()
    val notificaciones: LiveData<List<Notificacion>> = _notificaciones

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _notificaciones.value = if (eventId >= 0) {
                repo.getByEvent(eventId)
            } else {
                repo.getAll()
            }
        }
    }

    fun refresh() = load()
}
