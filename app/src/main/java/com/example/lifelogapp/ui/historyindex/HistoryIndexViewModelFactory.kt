//package com.example.lifelogapp.ui.historyindex
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.lifelogapp.database.LifelogDao
//
//class HistoryIndexViewModelFactory (
//        private val dataSource: LifelogDao,
//        private val application: Application) : ViewModelProvider.Factory {
//        @Suppress("unchecked_cast")
//        override fun<T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(HistoryIndexViewModel::class.java)) {
//                return HistoryIndexViewModel(dataSource, application) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}