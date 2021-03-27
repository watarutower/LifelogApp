package com.example.lifelogapp.ui.writereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

//    private var newStatus = MutableLiveData<Lifelog?>()
//
//    val daylog = database.getStatusWithId(dayLogsKey)
//
//    val theDay = dayLogsKey
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



//    }
}