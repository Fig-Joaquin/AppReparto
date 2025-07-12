package com.example.appreparto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appreparto.Productos
import com.example.appreparto.ProductosRepository

class ProductosViweModel: ViewModel() {
    private val _productos = MutableLiveData<List<Productos>>()
    val productos: LiveData<List<Productos>> = _productos
    fun loadAll() { _productos.value = ProductosRepository.getAll() }
    fun save(p: Productos) { ProductosRepository.save(p); loadAll() }
    fun delete(id: Long) { ProductosRepository.delete(id); loadAll() }
}