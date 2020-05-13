package com.example.coronavirustracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JHUCountyResponse(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coordinates: Coordinates,
    val country: String,
    val county: String,
    val province: String,
    val stats: Stats,
    val updatedAt: String

)