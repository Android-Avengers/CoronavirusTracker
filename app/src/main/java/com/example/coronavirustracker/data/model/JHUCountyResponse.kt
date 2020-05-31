package com.example.coronavirustracker.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JHUCountyResponse(
    @PrimaryKey
    val id: Int,
    @Embedded
    val coordinates: Coordinates,
    val country: String,
    val county: String,
    val province: String,
    @Embedded
    val stats: Stats,
    val updatedAt: String

)