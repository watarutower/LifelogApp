package com.example.lifelogapp.ui.writereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao

class WriteReviewViewModel (
        private val dayLogsKey: String? = "",
        dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    var editText: String= ""

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

    fun onSubmitClicked() {
        _navigateToHistoryDetail.value = dayLogsKey

    }



//    }
}