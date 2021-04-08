package com.myapplication.lifelogapp.ui.historydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.lifelogapp.database.LifelogDao

class HistoryDetailViewModelFactory (
        private val dayLogsKey: String?,
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
