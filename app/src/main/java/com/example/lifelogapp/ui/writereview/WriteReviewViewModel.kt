package com.example.lifelogapp.ui.writereview

import androidx.lifecycle.*
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.database.Preview
import kotlinx.coroutines.launch
import java.util.*

class WriteReviewViewModel (
        private val dayLogsKey: String? = "",
        dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    var editText: String?= ""

    val _commentFetch = MutableLiveData<String?>("")

    init{
        initializeLastComment()
    }

    private fun initializeLastComment(){
        viewModelScope.launch {
            val lastComment = database.getLastComment(dayLogsKey)
            _commentFetch.value = lastComment
        }
    }
    val commentFetch: LiveData<String?> = Transformations.map(_commentFetch) {
        when {
            it != null -> _commentFetch.value
            else -> ""
        }
    }



    val theDay = dayLogsKey
//
//    val editWrite = ""
//
    val navigateToHistoryDetail: LiveData<String?>
        get() = _navigateToHistoryDetail

    val _navigateToHistoryDetail = MutableLiveData<String?>()
//
//    fun onWriteClicked(){

    private suspend fun insert(newPreview: Preview) {
        database.insert(newPreview)
    }

    fun doneNavigating() {
        _navigateToHistoryDetail.value = null
    }



    fun onSubmitClicked() {
        viewModelScope.launch {
            val newReview = Preview()
            newReview.flag = 1
            newReview.theDate = dayLogsKey
            newReview.reviewComment = editText
            insert(newReview)

            _navigateToHistoryDetail.value = dayLogsKey
        }
    }

//    fun onReviseClicked() {
//        viewModelScope.launch {
//            val revisedPreview = _getOnePreview.value
//            revisedPreview?.reviewComment = editText.value
//            if (revisedPreview != null) {
//                update(revisedPreview)
//            }
//            _navigateToHistoryDetail.value = dayLogsKey
//        }
//
//    }



//    }
}