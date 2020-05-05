package com.example.coronavirustracker.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronavirustracker.data.model.ViewPager2Fragments

class TestFragment(val position: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val modelObject =  ViewPager2Fragments.values()[position]
        val view = inflater.inflate(modelObject.layoutResId, container, false)
        return view
    }
}