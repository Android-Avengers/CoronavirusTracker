package com.example.coronavirustracker.data.roomdb

import android.content.Context
import android.util.Log
import com.example.coronavirustracker.data.model.JHUCountyResponse
import com.example.coronavirustracker.data.remote.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountyRepository(applicationContext: Context){
    private val retrofitHelper by lazy { RetrofitHelper() }
    private val db  by lazy { AppDatabase.getDatabase(applicationContext) }

    fun getCounty(county: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var data: JHUCountyResponse? = get(county)
            if (data != null) {
                Log.d("TAG-REP", "Data found in database")
                EventBus.getDefault().post(arrayListOf(data))
            } else {
                Log.d("TAG-REP", "No data found in database, making network call")
                retrofitHelper.getJHUByCounty()
                    .getJHUByCounty(county)
                    .enqueue(object : Callback<List<JHUCountyResponse>> {
                        override fun onResponse(
                            call: Call<List<JHUCountyResponse>>,
                            response: Response<List<JHUCountyResponse>>
                        ) {
                            EventBus.getDefault().post(response.body())
                            CoroutineScope(Dispatchers.IO).launch {
                                upsert(response.body()?.get(0))
                            }
                            Log.d("TAG-Response:", "" + response.body())
                        }

                        override fun onFailure(call: Call<List<JHUCountyResponse>>, t: Throwable) {
                            EventBus.getDefault().post(t)
                            Log.e("TAG", "ERROR IN RETROFIT --> ", t)
                        }
                    })
            }
        }
    }

    // Update or insert
    suspend fun upsert(county: JHUCountyResponse?) {
        county?.let { db.countyDao().upsert(county) }
    }

    suspend fun get(county: String): JHUCountyResponse {
        return db.countyDao().get(county)
    }

    suspend fun deleteAll() {
        return db.countyDao().deleteAll()
    }
}