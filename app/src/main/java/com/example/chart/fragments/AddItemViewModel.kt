package com.example.chart.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.chart.database.GraphicsDataCount
import com.example.chart.database.Item
import com.example.chart.database.ItemDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddItemViewModel @ViewModelInject constructor(private val dao: ItemDao): ViewModel() {
    private val _inserItemLiveData = MutableLiveData<Long>()
    var insertItemLiveData: LiveData<Long> = _inserItemLiveData
    val itemsLiveData = MutableLiveData<List<Item>>()
    val items2LiveData = dao.getItems().asLiveData()
    val graphicsLiveData1 = MutableLiveData<List<GraphicsDataCount>>()

    fun insertItem(item: Item){
        viewModelScope.launch {
            val response = dao.addItem(item)
            _inserItemLiveData.postValue(response)
        }
    }

    fun getAllItems(pickDate: Long){
        viewModelScope.launch {
            dao.getAllWithTime(pickDate).collect {
                itemsLiveData.postValue(it)
            }
        }
    }

    fun getAllItemsInRange(fromDate: Long, toDate: Long){
        viewModelScope.launch {
            dao.getAllInRange(fromDate, toDate).collect {
                graphicsLiveData1.postValue(it)
            }
        }
    }
}