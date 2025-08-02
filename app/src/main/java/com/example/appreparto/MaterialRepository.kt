
package com.example.appreparto

import androidx.lifecycle.LiveData
import com.example.appreparto.data.MaterialDao

class MaterialRepository(private val dao: MaterialDao) {


    val materiales: LiveData<List<Material>> = dao.getAll()

    suspend fun save(m: Material) = dao.insert(m)
    suspend fun delete(id: Long) = dao.deleteById(id)
}
