package com.example.coronavirustracker.data.remote.retrofit

import android.util.Log
import com.example.coronavirustracker.data.model.JHUCountyResponse
import com.example.coronavirustracker.data.remote.BASE_URL_JHU
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    fun getRetrofitInstance(): Retrofit {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // automatically does the json parsing gives us the object back
            .baseUrl(BASE_URL_JHU)
        return builder.build()
    }

    fun getJHUByCounty() =
        getRetrofitInstance().create(JHUService::class.java)

    fun startJHURequest(location: String) {
        getJHUByCounty()
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
}