package com.example.lifelogapp.ui.historydetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.database.LifelogDao

class HistoryDetailViewModelFactory (
        private val dayLogsKey: String,
    private val dataSource: LifelogDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryDetailViewModel::class.java)) {
            return HistoryDetailViewModel(dayLogsKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
