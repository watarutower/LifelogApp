package com.example.lifelogapp.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.LifelogDao

class HomeViewModel (
        dataSource: LifelogDao,
        application: Application) : ViewModel() {

    val database = dataSource

    val daylog = database.getDayLogs()

    private val _navigateToUpdate = MutableLiveData<Boolean?>()

    val navigateToUpdate: LiveData<Boolean?>
        get() = _navigateToUpdate

    fun doneNavigating() {
        _navigateToUpdate.value = null
    }

    fun onFabClicked() {
        _navigateToUpdate.value = true
    }
}
