package com.example.chart.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val price: Float,
    val date: Long
)