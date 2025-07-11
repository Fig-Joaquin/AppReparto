package com.example.appreparto.viewmodel

import Material
import MaterialRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MaterialViewModel : ViewModel() {
    private val _materials = MutableLiveData<List<Material>>()
    val materials: LiveData<List<Material>> = _materials
    fun loadAll() { _materials.value = MaterialRepository.getAll() }
    fun save(m: Material) { MaterialRepository.save(m); loadAll() }
    fun delete(id: Long) { MaterialRepository.delete(id); loadAll() }
}

/* Factory para inyectar el repositorio */
