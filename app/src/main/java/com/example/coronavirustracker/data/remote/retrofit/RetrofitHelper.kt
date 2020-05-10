package com.example.coronavirustracker.data.remote.retrofit

import android.util.Log
import com.example.coronavirustracker.data.model.JHUCountyResponse
import com.example.coronavirustracker.data.model.JHUHistoricalCountyResponse
import com.example.coronavirustracker.data.remote.BASE_URL_JHU
import com.example.coronavirustracker.data.remote.BASE_URL_JHU_HISTORICAL
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val client by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build() }

    private val gsonConverterFactory by lazy { GsonConverterFactory.create() }

    private val jhuRetrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(gsonConverterFactory) // automatically does the json parsing gives us the object back
            .baseUrl(BASE_URL_JHU)
            .build() }

    private val jhuHistoricalRetrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(gsonConverterFactory) // automatically does the json parsing gives us the object back
            .baseUrl(BASE_URL_JHU_HISTORICAL)
            .build() }

    private val jhuService: JHUService by lazy { jhuRetrofit.create(JHUService::class.java) }
    private val jhuHistoricalService: JHUHistoricalService by lazy {
        jhuHistoricalRetrofit.create(JHUHistoricalService::class.java) }

    fun startJHUCountyRequest(location: String) {
        jhuService
            .getJHUByCounty(location)
            .enqueue(object: Callback<List<JHUCountyResponse>> {
                override fun onResponse(call: Call<List<JHUCountyResponse>>, response: Response<List<JHUCountyResponse>>) {
                    EventBus.getDefault().post(response.body())
                    Log.d("TAG-response:", ""+response.body())
                }

                override fun onFailure(call: Call<List<JHUCountyResponse>>, t: Throwable) {
                    EventBus.getDefault().post(t)
                    Log.e("TAG", "ERROR IN RETROFIT --> ", t)
                }
            })
    }

    fun startJHUHistoricalCountyRequest(state: String, lastDays: String? = null) {
        jhuHistoricalService
            .getJHUCountyByState(state, lastDays)
            .enqueue(object: Callback<List<JHUHistoricalCountyResponse>> {
                override fun onResponse(call: Call<List<JHUHistoricalCountyResponse>>, response: Response<List<JHUHistoricalCountyResponse>>) {
//                    EventBus.getDefault().post(response.body())
                    val res = response.body() // null on unknown state
//                    Log.d("TAG-CASES", ""+res.timeline.cases["5/9/20"]) // 92
                    Log.d("TAG-HISTORICAL", ""+res) // 92
                }

                override fun onFailure(call: Call<List<JHUHistoricalCountyResponse>>, t: Throwable) {
//                    EventBus.getDefault().post(t)
                    Log.e("TAG", "ERROR IN RETROFIT --> ", t)
                }
            })
    }
}