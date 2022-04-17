package com.example.chart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item): Long

    @Query("SELECT * FROM Item Where date = :pickDate")
    fun getAllWithTime(pickDate: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT date, SUM(price) as 'sumPrice' FROM Item WHERE date BETWEEN :fromDate AND :toDate GROUP BY date")
    fun getAllInRange(fromDate: Long, toDate: Long): Flow<List<GraphicsDataCount>>
}