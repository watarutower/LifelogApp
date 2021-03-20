package com.example.lifelogapp.ui.historyindex

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.LifelogDao

class HistoryIndexViewModel(dataSource: LifelogDao,
                            application: Application) : ViewModel() {
    val database = dataSource

    val dayLogs = database.getDayLogs()


    private val _navigateToHistoryDetail = MutableLiveData<Long>()
    val navigateToHistoryDetail
        get() = _navigateToHistoryDetail

    fun onHistoryDetailNavigated() {
        _navigateToHistoryDetail.value = null
    }
    fun onDayClicked(id: Long) {
        _navigateToHistoryDetail.value = id
    }
}