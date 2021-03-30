package com.example.lifelogapp.ui.home

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.formatDaylogs
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class HomeViewModel (
    dataSource: LifelogDao,
    application: Application) : ViewModel() {

    val database = dataSource

    //for adapter
    val daylog = database.getDayLogs()

    init {
        initializeAStatus()
    }

val _aStatus = MutableLiveData<Int>(0)
val aStatus: LiveData<Int>
    get() = _aStatus

    private fun initializeAStatus() {
        viewModelScope.launch {
            var theStatus = database.getOneStatus()
            _aStatus.value = theStatus
        }
    }

    val conditionQuality: LiveData<ConditionQuality?> = Transformations.map(_aStatus) {
        when {
            it in 0..19 -> ConditionQuality.VERY_DISSATISFIED
            it in 20..39 -> ConditionQuality.DISSATISFIED
            it in 40..59 -> ConditionQuality.SATISFIED
            it in 60..79 -> ConditionQuality.SMILE
            it in 80..100 -> ConditionQuality.VERY_SATISFIED
            else -> ConditionQuality.NO_STATUS
        }
    }

    private val _navigateToUpdate = MutableLiveData<Boolean?>()

    val navigateToUpdate: LiveData<Boolean?>
        get() = _navigateToUpdate

    fun doneNavigating() {
        _navigateToUpdate.value = false
    }

    fun onFabClicked() {
        _navigateToUpdate.value = true
    }
}

enum class ConditionQuality {
    VERY_DISSATISFIED,
    DISSATISFIED,
    NEUTRAL,
    SATISFIED,
    SMILE,
    VERY_SATISFIED,
    NO_STATUS
}


