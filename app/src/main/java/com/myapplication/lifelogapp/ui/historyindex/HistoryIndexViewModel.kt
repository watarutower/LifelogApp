package com.myapplication.lifelogapp.ui.historyindex

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapplication.lifelogapp.database.LifelogDao

class HistoryIndexViewModel(dataSource: LifelogDao,
                            application: Application) : ViewModel() {
    val database = dataSource

    val dayIndex = database.getStatusByDay()

//    val dayComment = database.getDayComment()

    private val _navigateToHistoryDetail = MutableLiveData<String?>()
    val navigateToHistoryDetail
        get() = _navigateToHistoryDetail


    fun onDayClicked(day: String?) {
        _navigateToHistoryDetail.value = day
    }

    fun onHistoryDetailNavigated() {
        _navigateToHistoryDetail.value = null
    }





}