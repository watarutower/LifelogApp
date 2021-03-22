package com.example.lifelogapp.ui.historyindex

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao

class HistoryIndexViewModel(dataSource: LifelogDao,
                            application: Application) : ViewModel() {
    val database = dataSource

//    val dayLogs = database.getDayLogs()

    val dayIndex = database.getStatusByDay()


    private val _navigateToHistoryDetail = MutableLiveData<Lifelog>()
    val navigateToHistoryDetail
        get() = _navigateToHistoryDetail


    fun onDayClicked(id: Lifelog) {
        _navigateToHistoryDetail.value = id
    }

//    fun onHistoryDetailNavigated() {
//        _navigateToHistoryDetail.value = null
//    }



}