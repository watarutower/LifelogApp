package com.example.lifelogapp.ui.update

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_util.*
import kotlinx.coroutines.launch

class UpdateViewModel (
    dataSource: LifelogDao,
    application: Application): ViewModel(){

    val database = dataSource


    private val _navigateToHome = MutableLiveData<Boolean?>()

    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome




    private suspend fun insert(eachStatus: Lifelog) {
        database.insert(eachStatus)
    }

    fun doneNavigating() {
        _navigateToHome.value = null
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            val newStatus = Lifelog()
            insert(newStatus)
            _navigateToHome.value = true
        }
    }

    fun onCancelClicked() {
        _navigateToHome.value = true
    }

}