package com.myapplication.lifelogapp.ui.worklog

import android.app.Application
import androidx.lifecycle.*
import com.myapplication.lifelogapp.database.LifelogDao
import com.myapplication.lifelogapp.database.WorkLog
import kotlinx.coroutines.launch

class WorkLogViewModel (dataSource: LifelogDao, application: Application) : ViewModel() {

    val database = dataSource

    val workedlog = database.getWorkedLogs()

    private var workinglog = MutableLiveData<WorkLog>()

    var startButtonVisible = Transformations.map(workinglog) {
        null == it
    }
    var finishButtonVisible = Transformations.map(workinglog) {
        null != it
    }

    init {
        initializeWorkLog()
    }

    private fun initializeWorkLog() {
        viewModelScope.launch {
            workinglog.value = getWorkLogFromDatabase()
        }
    }

    private suspend fun getWorkLogFromDatabase(): WorkLog? {
        var work = database.getWorkLog()
        if (work?.endTimeMilli != work?.startTimeMilli) {
            work = null
        }
        return work
    }

    private suspend fun insert(work: WorkLog) {
        database.insert(work)
    }

    private suspend fun update(work: WorkLog) {
        database.update(work)
    }

    fun onStart() {
        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val workLog = WorkLog()

            insert(workLog)

            workinglog.value = getWorkLogFromDatabase()
        }
    }

    fun onFinish() {
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch().
            val oldLog = workinglog.value ?: return@launch

            // Update the night in the database to add the end time.
            oldLog.endTimeMilli = System.currentTimeMillis()

            update(oldLog)

            workinglog.value = getWorkLogFromDatabase()
        }
    }


}