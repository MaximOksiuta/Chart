package com.example.chart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.chart.database.GraphicsDataCount
import com.example.chart.databinding.ActivityGraphicsCountBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphicsCountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraphicsCountBinding
    private val viewModel: GraphicsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphicsCountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.dataLiveData.observe(this){
            showBarChart(it)
        }

        binding.btnSetDateRange.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker().setTitleText("Выберите диапазон")
                    .build()

            dateRangePicker.show(supportFragmentManager, "date_range_picker")
            dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
                val firstDateUnix = datePicked.first/1000
                val secondDateUnix = datePicked.second/1000
                viewModel.getDataForGraphicsCount(firstDateUnix, secondDateUnix)
            }
        }

    }

    private fun showBarChart(data: List<GraphicsDataCount>){
        val entries = mutableListOf<BarEntry>()
        data.forEach { item ->
            entries.add(BarEntry(item.date.toFloat()/10000000, item.sumPrice))
        }
        val barDataset = BarDataSet(entries, "Items")
        val barData = BarData(barDataset)
        binding.apply {
            chart.data = barData
            chart.animateY(1000)
            chart.invalidate()
        }


    }
}