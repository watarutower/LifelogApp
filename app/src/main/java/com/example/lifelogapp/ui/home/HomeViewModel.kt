package com.example.lifelogapp.ui.home

import android.app.Application
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.*
import com.example.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Preview


class HomeViewModel(private val app: Application,
                    dataSource: LifelogDao): AndroidViewModel(app) {

    val database = dataSource
//    アダプター
    val daylog = database.getDayLogs()

    val _memoFetch = MutableLiveData<String?>("")

    var editText: String? = ""

    val _aStatus = MutableLiveData<Int>(0)
    val aStatus: LiveData<Int>
        get() = _aStatus

    private val _averageCondition = MutableLiveData<Float>(0.0F)
//    val averageCondition: LiveData<Int>
//        get() = _averageCondition

//    for Spinner
    private val _averageSelection = MutableLiveData<Int?>()
    val averageSelection: LiveData<Int?>
        get() = _averageSelection

    private val _refreshDisplay = MutableLiveData<Boolean?>()
    val refreshDisplay: LiveData<Boolean?>
        get() = _refreshDisplay

//    private val _textViewVisibility = MutableLiveData<Int?>()
    private val _editTextVisibility = MutableLiveData<Int?>()


    fun onTextClicked() {
//        _textViewVisibility.value = 8
        _editTextVisibility.value = 0
    }
//    val textViewVisibility = Transformations.map (_textViewVisibility) {
//        _textViewVisibility.value
//    }

    private suspend fun insert(newPreview: Preview) {
        database.insert(newPreview)
    }

//    val memoEditText = Transformations.map(_memoEditText) {
//        _memoEditText.value
//    }



    fun onDoneClicked() {
        viewModelScope.launch {
            val newMemo = Preview()
            newMemo.flag = 0
            newMemo.theDate = ""
            newMemo.reviewComment = editText
            insert(newMemo)
        }
        _editTextVisibility.value = 8
//        _textViewVisibility.value = 0
        _refreshDisplay.value = true
    }

    val editTextVisibility = Transformations.map (_editTextVisibility) {
        _editTextVisibility.value
    }

    init {
        initializeAStatus()
        initializeMemo()
        _editTextVisibility.value = 8
    }

    private fun initializeMemo() {
        viewModelScope.launch {
            val theMemo = database.getMemo()
            _memoFetch.value = theMemo
        }
    }
    val memoFetch: LiveData<String?> = Transformations.map(_memoFetch) {
        when {
            it != null -> {
                _memoFetch.value
            }
            else -> null
        }
    }

//   for Spinner
    fun setAverageSelected(timerLengthSelection: Int) {
        _averageSelection.value = timerLengthSelection
        _averageSelection.value?.let {showAverage(it)}
    }

    fun showAverage(averageLengthSelection: Int) {
      viewModelScope.launch {
         var theCondition: Float
          when (averageLengthSelection) {
             0 -> {
                 theCondition = database.getAverageConditionInDay(System.currentTimeMillis())
                 _averageCondition.value = theCondition
             }
             1 -> {
                 theCondition = database.getAverageConditionInThreeDay(System.currentTimeMillis())
                 _averageCondition.value = theCondition
             }
             2 -> {
                 theCondition = database.getAverageConditionInWeek(System.currentTimeMillis())
                 _averageCondition.value = theCondition
             }
             3 -> {
                 theCondition = database.getAverageConditionInMonth(System.currentTimeMillis())
                 _averageCondition.value = theCondition
             }
             else -> _averageCondition.value = -77.0F
         }
     }
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


