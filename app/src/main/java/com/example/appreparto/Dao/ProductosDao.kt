package com.example.appreparto.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appreparto.model.Productos

@Dao
interface ProductosDao {
    @Query("SELECT * FROM productos ORDER BY nombre")
    fun getALl(): LiveData<List<Productos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productos: Productos)

    @Query("DELETE FROM productos WHERE id = :id")
    suspend fun deleteByID(id: Long)
}