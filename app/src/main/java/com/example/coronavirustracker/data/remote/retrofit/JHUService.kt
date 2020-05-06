package com.example.coronavirustracker.data.remote.retrofit

import com.example.coronavirustracker.data.model.JHUCountyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JHUService {
    @GET("counties/{county}")
    fun getJHUByCounty(@Path("county") county: String)
            : Call<List<JHUCountyResponse>>
}