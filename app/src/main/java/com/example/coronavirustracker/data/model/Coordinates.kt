package com.example.coronavirustracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coordinates(
    @PrimaryKey(autoGenerate = true)
    val latitude: String,
    val longitude: String
)