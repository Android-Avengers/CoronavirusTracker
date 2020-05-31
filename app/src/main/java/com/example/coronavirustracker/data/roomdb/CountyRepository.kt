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
    val retrofitHelper = RetrofitHelper()
    val db = AppDatabase.getDatabase(applicationContext)

    fun getCounty(location: String) {
        // TODO: possible to run immediately?
        CoroutineScope(Dispatchers.IO).launch {
            var data: JHUCountyResponse? = retrieve()
            if (data != null) {
                Log.d("TAG-REP", "data found in the database here you go")
                EventBus.getDefault().post(data)
            } else {
                Log.d("TAG-REP", "no data found lets get it from the network")
                retrofitHelper.getJHUByCounty()
                    .getJHUByCounty(location)
                    .enqueue(object : Callback<List<JHUCountyResponse>> {
                        override fun onResponse(
                            call: Call<List<JHUCountyResponse>>,
                            response: Response<List<JHUCountyResponse>>
                        ) {
                            EventBus.getDefault().post(response.body())
                            CoroutineScope(Dispatchers.IO).launch {
                                insert(response.body()?.get(0))
                            }
                            Log.d("TAG-response:", "" + response.body())
                        }

                        override fun onFailure(call: Call<List<JHUCountyResponse>>, t: Throwable) {
                            EventBus.getDefault().post(t)
                            Log.e("TAG", "ERROR IN RETROFIT --> ", t)
                        }
                    })
            }

        }

//        return db value
    }

    fun insert(county: JHUCountyResponse?) {
        county?.let { db.countyDao().insert(county) }
    }

    fun retrieve(): JHUCountyResponse {
        return db.countyDao().getFirst()
    }

    fun deleteAll() {
        return db.countyDao().deleteAll()
    }
}