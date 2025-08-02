package com.example.appreparto.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appreparto.repository.NotificationRepository

class NotificationViewModelFactory(
    private val context: Context,
    private val eventId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            val repo = NotificationRepository(context)
            return NotificationViewModel(repo, eventId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
