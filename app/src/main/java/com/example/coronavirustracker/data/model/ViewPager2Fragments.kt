package com.example.coronavirustracker.data.model

import com.example.coronavirustracker.R

enum class ViewPager2Fragments (val titleResId: Int, val layoutResId: Int) {
    OVERVIEW(R.string.overview, R.layout.fragment_overview),
//    MAP(R.string.map, R.layout.fragment_map),
    CHART(R.string.chart, R.layout.fragment_chart),
    NEWS(R.string.news, R.layout.fragment_news)
}