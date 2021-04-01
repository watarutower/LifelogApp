package com.example.lifelogapp.ui.update

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.LifelogDao
import androidx.lifecycle.ViewModelProvider


class UpdateViewModelFactory (
        private val application: Application,
    private val dataSource: LifelogDao

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
