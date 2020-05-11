package com.example.coronavirustracker.data.model

data class JHUCountriesResponse(
    val coordinates: Coordinates,
    val country: String,
    val province: String,
    val stats: Stats,
    val updatedAt: String
)