package com.example.coronavirustracker.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coronavirustracker.data.model.JHUCountyResponse

@Dao
interface CountyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(county: JHUCountyResponse)

    @Query("SELECT * FROM JHUCountyResponse WHERE COUNTY == :county COLLATE NOCASE")
    suspend fun get(county: String) : JHUCountyResponse

    @Query("SELECT * FROM JHUCountyResponse")
    suspend fun getAll() : List<JHUCountyResponse>

    @Query("DELETE FROM JHUCountyResponse")
    suspend fun deleteAll()
}