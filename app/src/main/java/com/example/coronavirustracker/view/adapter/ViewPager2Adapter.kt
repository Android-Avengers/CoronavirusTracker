package com.example.coronavirustracker.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coronavirustracker.view.ui.ChartFragment
import com.example.coronavirustracker.view.ui.OverviewFragment
import com.example.coronavirustracker.view.ui.TestFragment


// FragmentActivity is similar to Activity with additional functionality (backwards compatible)
class ViewPager2Adapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragmentList = arrayOf(OverviewFragment(0), ChartFragment(1), TestFragment(2))

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    override fun getItemCount(): Int {
        return fragmentList.size
    }
}