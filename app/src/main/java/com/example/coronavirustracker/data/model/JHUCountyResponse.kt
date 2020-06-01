package com.example.coronavirustracker.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JHUCountyResponse(
    @PrimaryKey
    val country: String,
    @Embedded
    val coordinates: Coordinates,
    val county: String,
    val province: String,
    @Embedded
    val stats: Stats,
    val updatedAt: String
)