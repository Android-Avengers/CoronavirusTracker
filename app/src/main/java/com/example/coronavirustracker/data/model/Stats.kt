package com.example.coronavirustracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stats(
    @PrimaryKey(autoGenerate = true)
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)