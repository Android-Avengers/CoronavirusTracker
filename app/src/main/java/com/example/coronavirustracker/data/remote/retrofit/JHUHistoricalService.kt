package com.example.coronavirustracker.data.remote.retrofit

import com.example.coronavirustracker.data.model.JHUHistoricalCountyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JHUHistoricalService {
    @GET("usacounties/{state}")
    fun getJHUCountyByState(@Path("state") state: String, @Query("lastDays") lastDays: String?)
            : Call<List<JHUHistoricalCountyResponse>>
}