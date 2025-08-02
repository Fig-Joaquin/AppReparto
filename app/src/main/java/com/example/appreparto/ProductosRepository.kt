package com.example.appreparto


import androidx.lifecycle.LiveData
import com.example.appreparto.Dao.ProductosDao

class ProductosRepository(private val dao: ProductosDao) {
    val productos: LiveData<List<Productos>> = dao.getALl()
    suspend fun save(m: Productos) = dao.insert(m)
    suspend fun delete(id: Long) = dao.deleteByID(id)
}
