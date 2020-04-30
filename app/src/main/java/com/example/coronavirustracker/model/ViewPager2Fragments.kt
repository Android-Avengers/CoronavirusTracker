package com.example.coronavirustracker.model

import com.example.coronavirustracker.R

enum class ViewPager2Fragments (val titleResId: Int, val layoutResId: Int) {
    MAP(R.string.map, R.layout.fragment_map),
    CHART(R.string.chart, R.layout.fragment_chart),
    NEWS(R.string.news, R.layout.fragment_news)
}