package com.example.lifelogapp.ui.historydetail


import androidx.lifecycle.*
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch


class HistoryDetailViewModel (
        private val dayLogsKey: String? = "",
    dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    private var newStatus = MutableLiveData<Lifelog?>()


    val daylog = database.getStatusWithId(dayLogsKey)

    val theDay = dayLogsKey

    private val _dayAverage = MutableLiveData<Float>(0.0F)

    val _reviewComment = MutableLiveData<String?>("")
//    val reviewComment: LiveData<String?>
//        get() = _reviewComment

    init{
            initilizeComment()
            initializeConditionAverage()
        }

    private fun initializeConditionAverage() {
        viewModelScope.launch {
            val theCondition = database.getAverageConditionInADay(dayLogsKey)
            _dayAverage.value = theCondition
        }
    }


    private fun initilizeComment() {
    viewModelScope.launch {
        var theComment = database.getReviewComment(dayLogsKey)
        _reviewComment.value = theComment
    }
}
    val template = "How was your day?"

    val reviewComment: LiveData<String?> = Transformations.map(_reviewComment) {
     when {
         it != null -> _reviewComment.value
         else -> "I can't do it now"
     }
    }

    val dayAverage: LiveData<Float> = Transformations.map(_dayAverage) {
        when {
            it != null -> _dayAverage.value
            else -> -1.0F
        }
    }

//init {
//    viewModelScope.launch {
//        reviewC = database.getComment(theDay)
//    }
//}

    val navigateToWriteReview: LiveData<String?>
        get() = _navigateToWriteReview

    val _navigateToWriteReview = MutableLiveData<String?>()

    fun onWriteReviewNavigated() {
        _navigateToWriteReview.value = null
    }
        fun onWriteClicked(day: String?) {
            _navigateToWriteReview.value = day
        }
//    class WriteReviewListener(val clickListener: (statusId: String) -> Unit) {
//        fun onClick(oneStatus: String) {
//            clickListener(oneStatus)
//        }
//    }

}



//
//    var textVisibility = View.VISIBLE
//    var writeButtonVisibility = View.VISIBLE
//    var doneButtonVisibility = View.GONE
//
//
//    val editVisibility: LiveData<Int>
//        get() = _editVisibility
//
//    val _editVisibility = MutableLiveData<Int>()
//
//
//    init {
//        _editVisibility.value = View.GONE
//    }

//    fun onWriteClicked() {
//        viewModelScope.launch {
////            _editVisibility.value = true
////            editWrite.visibility = View.VISIBLE
//            _editVisibility.value = View.VISIBLE
//            textVisibility = View.GONE
//            writeButtonVisibility = View.GONE
//            doneButtonVisibility = View.VISIBLE
//
//
//        }
//    }
//    fun onDoneClicked() {
//        viewModelScope.launch {
//
//            _editVisibility.value = View.GONE
//            textVisibility = View.VISIBLE
//            writeButtonVisibility = View.VISIBLE
//            doneButtonVisibility = View.GONE
//
//
//        }
//    }

//    val daylogsString = Transformations.map(daylog) { daylogs ->
//        formatDaylogs(daylogs, application.resources)
//    }



