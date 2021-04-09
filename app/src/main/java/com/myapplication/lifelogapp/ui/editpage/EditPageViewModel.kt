package com.myapplication.lifelogapp.ui.editpage

import androidx.lifecycle.*
import com.myapplication.lifelogapp.database.Lifelog
import com.myapplication.lifelogapp.database.LifelogDao


import kotlinx.coroutines.launch

class EditPageViewModel (
    private val dayLogId: Long? = 0,
dataSource: LifelogDao) : ViewModel(){
    val database = dataSource

    val aKey = dayLogId

    var editText: String= ""

    val _timeSubmitted = MutableLiveData<Long>()

    val _lifeLog = MutableLiveData<Lifelog>()

    init{
        initializeLog()
    }
    private fun initializeLog() {
        viewModelScope.launch {
            val lifeLog = database.getLogByKey(aKey)
            _lifeLog.value = lifeLog
            _timeSubmitted.value = lifeLog.submitTime

            _commentFetch.value = lifeLog.oneComment
        }
    }
    val timeSubmitted: LiveData<Long> = Transformations.map(_timeSubmitted) {
        when {
            it != null -> _timeSubmitted.value
            else -> 0
        }
    }

    val _commentFetch = MutableLiveData<String?>("")
    val commentFetch: LiveData<String?> = Transformations.map(_commentFetch) {
        when {
            it != null -> _commentFetch.value
            else -> ""
        }
    }

    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome
    val _navigateToHome = MutableLiveData<Boolean?>()

    private suspend fun update(lifelog: Lifelog?) {
        database.update(lifelog)
    }

    fun onUpdateClicked() {
        viewModelScope.launch {
           val newLifelog = _lifeLog.value
            newLifelog?.oneComment = editText
            update(newLifelog)

            _navigateToHome.value = true
        }
    }

    fun doneNavigating() {
        _navigateToHome.value = false
    }

    fun onDeleteClicked() {
        viewModelScope.launch {
            database.delete(_lifeLog.value?.statusId)
            _navigateToHome.value = true
        }
    }
    fun onCancelClicked() {
            _navigateToHome.value = true
    }




}