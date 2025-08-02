package com.example.appreparto.dataBase

import android.content.Context
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.IntState
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appreparto.Dao.ProductosDao
import com.example.appreparto.model.Productos

@Database(entities = [Productos::class], version = 1, exportSchema = false) abstract class ProductosDataBase : RoomDatabase() {
    abstract fun productosDao() : ProductosDao

    companion object{
        @Volatile private var INSTANCE: ProductosDataBase? = null

        fun getInstance(ctx: Context): ProductosDataBase = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(
                ctx.applicationContext,
                ProductosDataBase::class.java,
                "productos.db"
            ).build().also {INSTANCE = it }
        }
    }
}
