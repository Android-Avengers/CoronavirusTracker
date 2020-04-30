package com.example.coronavirustracker.view.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coronavirustracker.view.ui.TestFragment

// FragmentActivity is similar to Activity with additional functionality (backwards compatible)
class ViewPager2Adapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    val fragmentList = arrayOf(TestFragment(0), TestFragment(1), TestFragment(2))

    override fun createFragment(position: Int): TestFragment {
        return fragmentList[position]
    }
    override fun getItemCount(): Int {
        return fragmentList.size
    }
}