package com.example.coronavirustracker.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coronavirustracker.R
import com.example.coronavirustracker.data.model.ViewPager2Fragments
import com.example.coronavirustracker.view.adapter.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    val retrofitHelper by lazy { RetrofitHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPager2Adapter(this)

        // TabLayoutMediator needed to use ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(ViewPager2Fragments.values()[position].titleResId)
        }.attach()
    }
}
