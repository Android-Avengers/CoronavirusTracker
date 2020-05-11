package com.example.coronavirustracker.data.remote.retrofit

import com.example.coronavirustracker.data.model.JHUHistoricalCountryResponse
import com.example.coronavirustracker.data.model.JHUHistoricalCountyResponse
import com.example.coronavirustracker.data.model.Timeline
import com.example.coronavirustracker.data.remote.BASE_URL_JHU_HISTORICAL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Default for lastDays = 30
interface JHUHistoricalService {
    @GET(BASE_URL_JHU_HISTORICAL)
    fun getJHUHistoricalAllCountries(@Query("lastDays") lastDays: String?)
            : Call<List<JHUHistoricalCountryResponse>>

    @GET("usacounties")
    fun getJHUHistoricalAvailableStates()
            : Call<List<String>>

    @GET("usacounties/{state}")
    fun getJHUHistoricalCountiesByState(
        @Path("state") state: String,
        @Query("lastDays") lastDays: String?)
            : Call<List<JHUHistoricalCountyResponse>>

    @GET("{query}")
    fun getJHUHistoricalCountryByName(
        @Path("query") query: String, @Query("lastDays") lastDays: String?)
            : Call<List<JHUHistoricalCountryResponse>>

    // ​/v2​/historical​/{country,country,...} does not work
    // Get Historical Data by multiple Country Names

    // /v2/historical/{query}/{province} can have one or more comma separated provinces
    @GET("{query}/{province}")
    fun getJHUHistoricalCountryByNameProvince(
        @Path("query") query: String,
        @Path("province") province: String,
        @Query("lastDays") lastDays: String?)
            : Call<List<JHUHistoricalCountryResponse>>

    @GET("all")
    fun getJHUHistoricalAll(@Query("lastDays") lastDays: String?)
            : Call<Timeline>
}