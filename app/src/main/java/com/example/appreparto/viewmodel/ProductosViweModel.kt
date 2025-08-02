package com.example.appreparto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appreparto.model.Productos
import com.example.appreparto.repository.ProductosRepository
import com.example.appreparto.dataBase.ProductosDataBase
import kotlinx.coroutines.launch

class ProductosViweModel(application: Application) : AndroidViewModel(application){
    private val repo: ProductosRepository

    val productos: LiveData<List<Productos>>

    init {
        val db = ProductosDataBase.getInstance(application)
        repo = ProductosRepository(db.productosDao())
        productos = repo.productos
    }

    fun save(p: Productos) = viewModelScope.launch { repo.save(p) }
    fun delete(id: Long) = viewModelScope.launch { repo.delete(id) }
}