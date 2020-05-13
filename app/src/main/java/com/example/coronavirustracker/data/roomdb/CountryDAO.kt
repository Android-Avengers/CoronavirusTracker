package com.example.coronavirustracker.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coronavirustracker.data.model.JHUCountyResponse

@Dao
interface CountryDAO {

    @Insert
    fun adAll(country : JHUCountyResponse)

    @Query("SELECT * FROM JHUCountyResponse")
    fun getAll() : List<JHUCountyResponse>

    @Insert
    fun addMultiple(vararg country : JHUCountyResponse)
}