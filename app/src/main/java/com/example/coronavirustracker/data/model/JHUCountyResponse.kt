package com.example.coronavirustracker.data.model

data class JHUCountyResponse(
    val coordinates: Coordinates,
    val country: String,
    val county: String,
    val province: String,
    val stats: Stats,
    val updatedAt: String
)