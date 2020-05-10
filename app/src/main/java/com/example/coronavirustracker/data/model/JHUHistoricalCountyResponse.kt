package com.example.coronavirustracker.data.model

data class JHUHistoricalCountyResponse(
    val county: String,
    val province: String,
    val timeline: Timeline
)