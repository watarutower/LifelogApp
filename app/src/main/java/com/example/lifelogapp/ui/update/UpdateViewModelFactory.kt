package com.example.lifelogapp.ui.update

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.LifelogDao
import androidx.lifecycle.ViewModelProvider


class UpdateViewModelFactory (
    private val dataSource: LifelogDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
