package com.example.coronavirustracker.view.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronavirustracker.R
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment(val position: Int) : Fragment() {
    // Can't load charts in onCreateView since views are not inflated yet
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // https://weeklycoding.com/mpandroidchart/
        val chart = lineChart
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))
        val dataSet = LineDataSet(entries, "My Type")
        val lineData = LineData(dataSet)
        chart.data = lineData
//        chart.invalidate()

        val barChart = barChart
        val entries2 = ArrayList<BarEntry>()
        entries2.add(BarEntry(1f, 10f))
        entries2.add(BarEntry(2f, 2f))
        entries2.add(BarEntry(3f, 7f))
        entries2.add(BarEntry(4f, 20f))
        entries2.add(BarEntry(5f, 16f))
        val dataSet2 = BarDataSet(entries2, "something")
        val barData = BarData(dataSet2)
        barChart.data = barData

        val pieChart = pieChart
        val entries3 = ArrayList<PieEntry>()
        entries3.add(PieEntry(1f, 10f))
        entries3.add(PieEntry(2f, 2f))
        entries3.add(PieEntry(3f, 7f))
        entries3.add(PieEntry(4f, 20f))
        entries3.add(PieEntry(5f, 16f))
        val dataSet3 = PieDataSet(entries3, "something")
        val pieData = PieData(dataSet3)
        pieChart.data = pieData

        val comboChart = combinedChart
        val comboData = CombinedData()
        comboData.setData(lineData)
        comboData.setData(barData)
        comboChart.data = comboData;
    }
}