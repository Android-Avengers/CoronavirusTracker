package com.example.coronavirustracker.data.model

import com.google.gson.JsonObject

data class Timeline(
    val cases: JsonObject,
    val deaths: JsonObject
)