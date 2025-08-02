package com.example.appreparto.data

import androidx.room.Dao
import com.example.appreparto.Material
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
@Database(entities = [Material::class], version = 1, exportSchema = false)
abstract class MaterialDatabase : RoomDatabase() {

    abstract fun materialDao(): MaterialDao

    companion object {
        @Volatile private var INSTANCE: MaterialDatabase? = null

        fun getInstance(ctx: Context): MaterialDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    ctx.applicationContext,
                    MaterialDatabase::class.java,
                    "materiales.db"
                ).build().also { INSTANCE = it }
            }
    }
}
