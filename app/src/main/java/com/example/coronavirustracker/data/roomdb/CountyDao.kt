package com.example.coronavirustracker.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coronavirustracker.data.model.JHUCountyResponse

@Dao
interface CountyDao {
    @Insert
    fun insert(county: JHUCountyResponse)

    // retrieve by county name
    @Query("SELECT * FROM JHUCountyResponse LIMIT 1")
    fun getFirst() : JHUCountyResponse

    @Query("SELECT * FROM JHUCountyResponse")
    fun getAll() : List<JHUCountyResponse>

    @Query("DELETE FROM JHUCountyResponse")
    fun deleteAll()
}