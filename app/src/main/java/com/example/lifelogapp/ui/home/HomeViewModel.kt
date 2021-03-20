package com.example.lifelogapp.ui.home

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.formatDaylogs
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class HomeViewModel (
    dataSource: LifelogDao,
    application: Application) : ViewModel() {

    val database = dataSource

    //for adapter
    val daylog = database.getDayLogs()

//    val daylogList = database.getDayLogsList()
//
//    val aDaylog = daylogList[0]

//val aStatus: LiveData<Lifelog?>
//    get() = _aStatus

    val _aStatus = MutableLiveData<Lifelog?>()

    init {
        initializeAStatus()
    }

    private fun initializeAStatus() {
        viewModelScope.launch {
            var theStatus = database.getOneStatus()
            _aStatus.value = theStatus
        }
    }



     var i: Int = qualityToImage(_aStatus.value?.oneCondition)

    var imageUrl:Int = i
//    var imageUrl: Int by Delegates.notNull()

    fun qualityToImage(quality: Int?): Int {

        when (quality) {
            in 0..10 -> return  R.drawable.ic_sentiment_very_dissatisfied_24px


            in 11..34 -> return  R.drawable.ic_sentiment_dissatisfied_black_18dp
            in 35..69 -> return  R.drawable.ic_sentiment_neutral_24px

            in 70..85 -> return R.drawable.ic_sentiment_satisfied_24px

            in 86..100 -> return  R.drawable.ic_sentiment_very_satisfied_24px
            else -> return R.drawable.ic_baseline_autorenew_24
        }
    }

    private val _navigateToUpdate = MutableLiveData<Boolean?>()

    val navigateToUpdate: LiveData<Boolean?>
        get() = _navigateToUpdate

    fun doneNavigating() {
        _navigateToUpdate.value = null
    }

    fun onFabClicked() {
        _navigateToUpdate.value = true
    }
}
