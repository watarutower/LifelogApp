package com.example.lifelogapp.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch


class HomeViewModel (
    dataSource: LifelogDao,
    application: Application) : ViewModel() {

    val database = dataSource

    //for adapter
    val daylog = database.getDayLogs()

    val _aStatus = MutableLiveData<Int>(0)
    val aStatus: LiveData<Int>
        get() = _aStatus

    private val _averageCondition = MutableLiveData<Int>()
//    val averageCondition: LiveData<Int>
//        get() = _averageCondition

    private val _averageSelection = MutableLiveData<Int>()
    val averageSelection: LiveData<Int>
        get() = _averageSelection

    init {
        initializeAStatus()
    }



    private fun initializeAStatus() {
        viewModelScope.launch {
            var theStatus = database.getOneStatus()
            _aStatus.value = theStatus

            var theCondition = database.getAverageConditionInDay(System.currentTimeMillis())
            _averageCondition.value = theCondition
        }
    }

    val averageCondition: LiveData<Int> = Transformations(_averageCondition.value)

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






    fun setAverageSelected(timerLengthSelection: Int) {
        _averageSelection.value = timerLengthSelection
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


