package com.example.coronavirustracker.data.remote.retrofit

import com.example.coronavirustracker.data.model.JHUCountyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JHUService {
    @GET("counties/")
    fun getJHUByCounty(@Query("location") location: String)
            : Call<List<JHUCountyResponse>>
}