package com.example.coronavirustracker.data.remote.retrofit

import com.example.coronavirustracker.data.model.JHUCountriesResponse
import com.example.coronavirustracker.data.model.JHUCountyResponse
import com.example.coronavirustracker.data.remote.BASE_URL_JHU
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JHUService {
    @GET(BASE_URL_JHU)
    fun getJHUCountries()
            : Call<List<JHUCountriesResponse>>

    @GET("counties")
    fun getJHUCounties()
            : Call<List<JHUCountyResponse>>

    @GET("counties/{county}")
    fun getJHUCounty(@Path("county") county: String)
            : Call<List<JHUCountyResponse>>
}