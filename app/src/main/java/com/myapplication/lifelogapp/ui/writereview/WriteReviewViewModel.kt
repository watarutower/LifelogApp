package com.myapplication.lifelogapp.ui.writereview

import androidx.lifecycle.*
import com.myapplication.lifelogapp.database.LifelogDao
import com.myapplication.lifelogapp.database.Preview
import kotlinx.coroutines.launch

class WriteReviewViewModel (
        private val dayLogsKey: String? = "",
        dataSource: LifelogDao) : ViewModel() {

    val database = dataSource

    var editText: String?= ""

    val previewFetch = MutableLiveData(Preview())

    val theDay = if(dayLogsKey != "MEMO"){
        dayLogsKey?.substring(startIndex = 4)
    } else {
        dayLogsKey
    }

    val withWeekday = dayLogsKey

    init{
        initializeLastComment()
    }

    private fun initializeLastComment(){
        viewModelScope.launch {
            val onePreview = database.getOnePreview(theDay)
            previewFetch.value = onePreview
        }
    }
    val commentFetch: LiveData<String?> = Transformations.map(previewFetch) {
        when {
            it != null -> previewFetch.value?.reviewComment
            else -> ""
        }
    }

    val navigateToHistoryDetail: LiveData<String?>
        get() = _navigateToHistoryDetail
    val _navigateToHistoryDetail = MutableLiveData<String?>()

    val navigateToHome: LiveData<String?>
        get() = _navigateToHome
    val _navigateToHome = MutableLiveData<String?>()

    private suspend fun insert(newPreview: Preview) {
        database.insert(newPreview)
    }

    private suspend fun update(newPreview: Preview?) {
        database.update(newPreview)
    }

    fun doneNavigating() {
        _navigateToHistoryDetail.value = null
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            val newComment = Preview()
            if(dayLogsKey == "MEMO"){
                newComment.flag = 0
                newComment.theDate = dayLogsKey
                newComment.reviewComment = editText
                insert(newComment)
                _navigateToHome.value = dayLogsKey
            }
            else {
                newComment.flag = 1
                newComment.theDate = theDay
                newComment.reviewComment = editText
                insert(newComment)
                _navigateToHistoryDetail.value = withWeekday
            }
        }
    }

    fun onReviseClicked() {
        viewModelScope.launch {
            val revisedPreview = previewFetch
            revisedPreview.value?.reviewComment = editText

                update(revisedPreview.value)

            if(dayLogsKey =="MEMO"){
                _navigateToHome.value = dayLogsKey
            }else {
                _navigateToHistoryDetail.value = withWeekday
            }
        }
    }

    fun onCancelClicked() {
        if(dayLogsKey =="MEMO"){
            _navigateToHome.value = dayLogsKey
        }else {
            _navigateToHistoryDetail.value = withWeekday
        }
    }


    val submitButtonVisible = Transformations.map (previewFetch){
        when {
            previewFetch.value?.reviewComment== null -> 0
            else -> 8
        }
    }

    val updateButtonVisible = Transformations.map (previewFetch){
        when {
            previewFetch.value?.reviewComment== null -> 8
            else -> 0
        }
    }
}