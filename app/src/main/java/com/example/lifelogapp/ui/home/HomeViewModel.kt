package com.example.lifelogapp.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch
import com.example.lifelogapp.R


class HomeViewModel(private val app: Application,
                    dataSource: LifelogDao): AndroidViewModel(app) {

    val database = dataSource

    //for adapter
    val daylog = database.getDayLogs()

    private val averageLengthOptions: IntArray

    val _aStatus = MutableLiveData<Int>(0)
    val aStatus: LiveData<Int>
        get() = _aStatus

    private val _averageCondition = MutableLiveData<Float>(0.0F)
//    val averageCondition: LiveData<Int>
//        get() = _averageCondition

    private val _averageSelection = MutableLiveData<Int?>()
//    val averageSelection: LiveData<Int?>
//        get() = _averageSelection

    init {
        initializeAStatus()
        averageLengthOptions = app.resources.getIntArray(R.array.days_length_array)
    }

    fun setAverageSelected(timerLengthSelection: Int) {
        _averageSelection.value = timerLengthSelection
    }




    private fun initializeAStatus() {
        viewModelScope.launch {
            var theStatus = database.getOneStatus()
            _aStatus.value = theStatus

            val theCondition = database.getAverageConditionInDay(System.currentTimeMillis())
            _averageCondition.value = theCondition
        }
    }


    val averageCondition: LiveData<Float> = Transformations.map(_averageCondition) {
        when {
            it != null -> _averageCondition.value
            else -> -1.0F
        }
    }
    val averageSelection: LiveData<Int> = Transformations.map(_averageSelection) {
        viewModelScope.launch {
            var theCondition: Float
            when {
                it == 0 -> {
                    theCondition = database.getAverageConditionInDay(System.currentTimeMillis())
                    _averageCondition.value = theCondition
                }
                it == 3 -> {
                    theCondition = database.getAverageConditionInThreeDay(System.currentTimeMillis())
                    _averageCondition.value = theCondition
                }
                it == 7 -> {
                    theCondition = database.getAverageConditionInWeek(System.currentTimeMillis())
                    _averageCondition.value = theCondition
                }
                it == 30 -> {
                    theCondition = database.getAverageConditionInMonth(System.currentTimeMillis())
                    _averageCondition.value = theCondition
                }
                else -> _averageCondition.value = -77.0F
            }
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


