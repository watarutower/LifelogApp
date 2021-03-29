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

    init {
        initializeAStatus()
    }

//     var gotStatus = database.getOneStatus()

val _aStatus = MutableLiveData<Int>(0)
val aStatus: LiveData<Int>
    get() = _aStatus


//init {
//    _aStatus.value = gotStatus
//}


//
//    init {
//        initializeAStatus()
//    }
    private fun initializeAStatus() {
        viewModelScope.launch {
            var theStatus = database.getOneStatus()
            _aStatus.value = theStatus
        }
    }

//    private val _qualityImage = MutableLiveData<Int?>(0)
//    val likes: LiveData<Int?> = _qualityImage

    val conditionQuality: LiveData<ConditionQuality?> = Transformations.map(_aStatus) {
        when {
            it in 0..10 -> ConditionQuality.VERY_DISSASTISFIED
            it in 11..34 -> ConditionQuality.VERY_DISSASTISFIED
            it in 35..69 -> ConditionQuality.NEUTRAL
            it in 70..85 -> ConditionQuality.SATISFIED
            it in 86..100 -> ConditionQuality.VERY_SATISFIED
            else -> ConditionQuality.NO_STATUS
        }
    }





//    private val imageUrl = MutableLiveData(0)



//    var i :Int? = 0
//    init{
//    i = _aStatus.value?.oneCondition
//    }
//
//    init {
//        _qualityImage.value = i
//    }



//    var imageUrl:Int = i
//    var imageUrl: Int by Delegates.notNull()





//    fun qualityToImage(quality: Int?): Int {
//
//        when (quality) {
//            in 0..10 -> return  R.drawable.ic_sentiment_very_dissatisfied_24px
//
//
//            in 11..34 -> return  R.drawable.ic_sentiment_dissatisfied_black_18dp
//            in 35..69 -> return  R.drawable.ic_sentiment_neutral_24px
//
//            in 70..85 -> return R.drawable.ic_sentiment_satisfied_24px
//
//            in 86..100 -> return  R.drawable.ic_sentiment_very_satisfied_24px
//            else -> return R.drawable.ic_baseline_autorenew_24
//        }
//    }

    private val _navigateToUpdate = MutableLiveData<Boolean?>()

    val navigateToUpdate: LiveData<Boolean?>
        get() = _navigateToUpdate

    fun doneNavigating() {
        _navigateToUpdate.value = null!!
    }

    fun onFabClicked() {
        _navigateToUpdate.value = true
    }
}

enum class ConditionQuality {
    VERY_DISSASTISFIED,
    DEISSATISRFIED,
    NEUTRAL,
    SATISFIED,
    VERY_SATISFIED,
    NO_STATUS
}


