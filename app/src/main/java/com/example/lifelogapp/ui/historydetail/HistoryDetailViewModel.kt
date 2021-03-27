package com.example.lifelogapp.ui.historydetail


import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.database.Preview
import com.example.lifelogapp.formatDaylogs
import kotlinx.coroutines.launch


class HistoryDetailViewModel (
        private val dayLogsKey: String? = "",
    dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    private var newStatus = MutableLiveData<Lifelog?>()


    val daylog = database.getStatusWithId(dayLogsKey)

    val theDay = dayLogsKey

//    うまくいかないのはここです
//    val theComment = database.getReviewComment(dayLogsKey)

//    init{
//        theComment.value?.reviewComment
//        }

    val template = "How was your day?"

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



