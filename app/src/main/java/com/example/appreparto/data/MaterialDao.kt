package com.example.appreparto.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appreparto.Material

@Dao
interface MaterialDao {

    @Query("SELECT * FROM materiales ORDER BY nombre")
    fun getAll(): LiveData<List<Material>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(material: Material)

    @Query("DELETE FROM materiales WHERE id = :id")
    suspend fun deleteById(id: Long)
}
