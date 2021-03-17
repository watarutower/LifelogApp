package com.example.lifelogapp.ui.historydetail


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.formatDaylogs

class HistoryDetailViewModel (
    dataSource: LifelogDao,
    application: Application) : ViewModel() {

    val database = dataSource

    private var newStatus = MutableLiveData<Lifelog?>()

    val daylog = database.getDayLogs()

//    val daylogsString = Transformations.map(daylog) { daylogs ->
//        formatDaylogs(daylogs, application.resources)
//    }
}

