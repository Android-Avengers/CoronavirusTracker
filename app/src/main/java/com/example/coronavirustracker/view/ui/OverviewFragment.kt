package com.example.coronavirustracker.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.coronavirustracker.R
import com.example.coronavirustracker.data.model.JHUCountyResponse
import com.example.coronavirustracker.data.remote.retrofit.RetrofitHelper
import com.example.coronavirustracker.data.roomdb.CountyRepository
import kotlinx.android.synthetic.main.fragment_overview.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

internal class OverviewFragment( val position: Int) : Fragment() {
    val retrofitHelper by lazy { RetrofitHelper() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        svCountySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            // NOTE: Submits twice on non emulator submit (i.e. enter on physical keyboard)
            //       Issue may not arise in practice
            override fun onQueryTextSubmit(query: String): Boolean {
                pbSearch.visibility = View.VISIBLE
                Log.d("TAG-QUERY", query)
//                retrofitHelper.startJHURequest(query)
                activity?.let { CountyRepository(it.applicationContext).getCounty(query) }
                return false
            }
        })
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister((this))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onJHUCountyResponse(jhuCountyResponse: List<JHUCountyResponse>) {
        pbSearch.visibility = View.INVISIBLE
        tvCountyData.visibility = View.INVISIBLE
        svCountyData.visibility = View.VISIBLE
        tvCountyConfirmed.text = "${jhuCountyResponse[0].stats.confirmed}"
        tvCountyData.text = "Confirmed: ${jhuCountyResponse[0].stats.confirmed} \n" +
                "Deaths: ${jhuCountyResponse[0].stats.deaths} \n" +
                "Recovered: ${jhuCountyResponse[0].stats.recovered} \n"
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onJHUCountyFailResponse(t: Throwable) {
        pbSearch.visibility = View.INVISIBLE
        svCountyData.visibility = View.INVISIBLE
        tvCountyData.visibility = View.VISIBLE
        Log.d("TAG-Network", "FAILURE NETWORK: $t")
    }
}