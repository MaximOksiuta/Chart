package com.example.chart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chart.databinding.ActivityMainBinding
import com.example.chart.fragments.AddItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener{
            val bottomSheet = AddItemFragment(1649356897, "07/04/2022")
            if (!bottomSheet.isAdded) {
                bottomSheet.show(supportFragmentManager, "")
            }
        }

        binding.btnShowMoneyCountChart.setOnClickListener {
            startActivity(Intent(this, GraphicsCountActivity::class.java))
        }
    }
}