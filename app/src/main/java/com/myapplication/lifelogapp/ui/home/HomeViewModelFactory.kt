package com.myapplication.lifelogapp.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.lifelogapp.database.LifelogDao


class HomeViewModelFactory  (
        private val application: Application,
        private val dataSource: LifelogDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
