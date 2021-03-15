package com.example.lifelogapp.ui.historydetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao

class HistoryDetailViewModel (
    dataSource: LifelogDao,
    application: Application) : ViewModel() {

    val database = dataSource

    private var each_log = MutableLiveData<Lifelog?>()

    val day_log = database.getDayLog()





    }
