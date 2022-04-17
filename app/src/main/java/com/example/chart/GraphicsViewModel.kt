package com.example.chart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chart.database.GraphicsDataCount
import com.example.chart.database.ItemDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GraphicsViewModel @ViewModelInject constructor(private val dao: ItemDao): ViewModel() {

    private val _dataLiveData = MutableLiveData<List<GraphicsDataCount>>()
    val dataLiveData: LiveData<List<GraphicsDataCount>> = _dataLiveData

    fun getDataForGraphicsCount(fromData: Long, toDate: Long){
        viewModelScope.launch {
            dao.getAllInRange(fromData, toDate).collect {
                _dataLiveData.postValue(it)
            }
        }
    }

}