package com.example.lifelogapp.ui.worklog

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lifelogapp.database.LifelogDao


class WorkLogViewModelFactory (
    private val dataSource: LifelogDao,
    private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun<T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WorkLogViewModel::class.java)) {
                return WorkLogViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }