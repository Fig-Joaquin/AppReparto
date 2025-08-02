package com.example.appreparto.viewmodel

import com.example.appreparto.Material
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appreparto.MaterialRepository
import com.example.appreparto.data.MaterialDatabase
import kotlinx.coroutines.launch

class MaterialViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: MaterialRepository

    val materiales: LiveData<List<Material>>

    init {
        val db = MaterialDatabase.getInstance(application)
        repo = MaterialRepository(db.materialDao())
        materiales = repo.materiales
    }

    fun save(m: Material) = viewModelScope.launch { repo.save(m) }
    fun delete(id: Long) = viewModelScope.launch { repo.delete(id) }
}

