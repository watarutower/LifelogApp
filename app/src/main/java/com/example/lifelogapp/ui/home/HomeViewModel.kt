package com.example.lifelogapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _navigateToUpdate = MutableLiveData<Boolean?>()

    val navigateToUpdate: LiveData<Boolean?>
        get() = _navigateToUpdate

    fun doneNavigating() {
        _navigateToUpdate.value = null
    }

    fun onUpdate() {
        _navigateToUpdate.value = true
    }
}
