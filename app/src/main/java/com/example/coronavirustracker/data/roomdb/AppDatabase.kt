package com.example.coronavirustracker.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coronavirustracker.data.model.JHUCountyResponse

@Database(entities = arrayOf(JHUCountyResponse::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countyDao(): CountyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration() // all db data is droppped
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}