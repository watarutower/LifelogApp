package com.example.lifelogapp.ui.history

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.database.LifelogDao

class HistoryViewModelFactory (
        private val dataSource: LifelogDao,
        private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun<T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
                return HistoryViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}