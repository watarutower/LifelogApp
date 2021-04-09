package com.myapplication.lifelogapp.ui.editpage

import androidx.lifecycle.*
import com.myapplication.lifelogapp.database.LifelogDao
import kotlinx.coroutines.launch

class EditPageViewModel (
    private val dayLogId: Long? = 0,
dataSource: LifelogDao) : ViewModel(){
    val database = dataSource

    val aKey = dayLogId

    val _timeSubmitted = MutableLiveData<Long>()
    init{
        initializeLog()
    }
    private fun initializeLog() {
        viewModelScope.launch {
            val lifeLog = database.getLogByKey(aKey)
            _timeSubmitted.value = lifeLog.submitTime
        }
    }
    val timeSubmitted: LiveData<Long> = Transformations.map(_timeSubmitted) {
        when {
            it != null -> _timeSubmitted.value
            else -> 0
        }
    }




}