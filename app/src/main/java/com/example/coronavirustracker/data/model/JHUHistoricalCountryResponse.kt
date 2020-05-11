package com.example.coronavirustracker.data.model

data class JHUHistoricalCountryResponse(
    val country: String,
    val province: String,
    val timeline: Timeline
)