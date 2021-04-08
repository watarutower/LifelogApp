package com.myapplication.lifelogapp.ui.writereview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.lifelogapp.database.LifelogDao

class WriteReviewViewModelFactory (
        private val dayLogsKey: String?,
        private val dataSource: LifelogDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WriteReviewViewModel::class.java)) {
            return WriteReviewViewModel(dayLogsKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



