// app/src/main/java/com/example/appreparto/data/AppDatabase.kt
package com.example.appreparto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appreparto.Material
import com.example.appreparto.model.Notificacion

@Database(entities = [Material::class, Notificacion::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun materialDao(): MaterialDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(ctx: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    ctx.applicationContext,
                    AppDatabase::class.java,
                    "appreparto.db"
                ).build().also { INSTANCE = it }
            }
    }
}
