package com.example.lifelogapp.ui.home

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.formatDaylogs


class HomeViewModel (
        dataSource: LifelogDao,
        application: Application) : ViewModel() {

    val database = dataSource
    val daylog = database.getDayLogs()

//    private var _latestStatus = MutableLiveData<Lifelog?>()
//
//    val latestStatus: LiveData<Lifelog?>
//        get() = _latestStatus

//    val statusString =  Transformations.map(daylog {
//        daylog ->
//        formatDaylogs(daylog, applicaton.resources)
//    })
//
    private var aStatus: Lifelog? = null

    var statuslog = aStatus


    var imageUrl = R.drawable.ic_baseline_star_border_24

//    init {
//        aStatus = database.getOneStatus()
////        qualityToImage(aStatus?.oneCondition)
//    }

//    private val _imageUrl = MutableLiveData<Int>()
//    val imageUrl: LiveData<Int?>
//        get() = _imageUrl



//    var homeImage =  qualityToImage(aStatus?.oneCondition)

//
//    fun qualityToImage(quality: Int?){
//        when (quality) {
//            in 0..10 -> homeImage = "ic_sentiment_very_dissatisfied_24px"
//
//            in 11..34 -> homeImage = "ic_sentiment_dissatisfied_black_18dp"
//            in 35..69 -> homeImage = "ic_sentiment_neutral_24px"
//
//            in 70..85 -> homeImage = "ic_sentiment_satisfied_24px"
//
//            in 86..100 -> homeImage = "ic_sentiment_very_satisfied_24px"
//            else -> homeImage = "ic_baseline_autorenew_24"
//        }
//    }

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
