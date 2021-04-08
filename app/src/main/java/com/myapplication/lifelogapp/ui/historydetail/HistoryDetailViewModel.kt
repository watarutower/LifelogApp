package com.myapplication.lifelogapp.ui.historydetail


import androidx.lifecycle.*
import com.myapplication.lifelogapp.database.Lifelog
import com.myapplication.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch


class HistoryDetailViewModel (
        private val dayLogsKey: String? = "",
    dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    private var newStatus = MutableLiveData<Lifelog?>()

    val theDay = dayLogsKey?.substring(startIndex = 4)
    val daylog = database.getStatusWithId(theDay)

    val withWeekday = dayLogsKey



    private val _dayAverage = MutableLiveData<Float>(0.0F)

    val _reviewComment = MutableLiveData<String?>("")

    init{
            initilizeComment()
            initializeConditionAverage()
        }

    private fun initializeConditionAverage() {
        viewModelScope.launch {
            val theCondition = database.getAverageConditionInADay(theDay)
            _dayAverage.value = theCondition
        }
    }


    private fun initilizeComment() {
    viewModelScope.launch {
        var theComment = database.getReviewComment(theDay)
        _reviewComment.value = theComment
    }
}
    val template = "How was your day?"

    val reviewComment: LiveData<String?> = Transformations.map(_reviewComment) {
     when {
         it != null -> _reviewComment.value
         else -> ""
     }
    }

    val dayAverage: LiveData<Float> = Transformations.map(_dayAverage) {
        when {
            it != null -> _dayAverage.value
            else -> -1.0F
        }
    }

    val navigateToWriteReview: LiveData<String?>
        get() = _navigateToWriteReview

    val _navigateToWriteReview = MutableLiveData<String?>()

    fun onWriteReviewNavigated() {
        _navigateToWriteReview.value = null
    }
        fun onWriteClicked(day: String?) {
            _navigateToWriteReview.value = day
        }
}
