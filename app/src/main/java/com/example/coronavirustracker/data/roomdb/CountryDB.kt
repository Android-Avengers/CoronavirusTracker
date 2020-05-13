package com.example.coronavirustracker.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coronavirustracker.data.model.JHUCountyResponse

@Database(entities = [JHUCountyResponse::class], version = 1)

abstract class CountryDB : RoomDatabase() {

    abstract fun getCountryDAO(): CountryDAO

    companion object {


        @Volatile
        private var instance: CountryDB? = null
        private val LOCK = Any()
    }

    operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
        instance ?: buildDatabase(context).also{
            instance = it
        }
    }

    private fun buildDatabase(context : Context) = Room.databaseBuilder(
        context.applicationContext,
        CountryDB::class.java,
    "countryDB"
    ).build()

}