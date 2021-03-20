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


class HomeViewModel (
        dataSource: LifelogDao,
        application: Application) : ViewModel() {

    val database = dataSource

    //for adapter
    val daylog = database.getDayLogs()

//    val daylogList = database.getDayLogsList()
//
//    val aDaylog = daylogList[0]



    var imageUrl = R.drawable.ic_baseline_star_border_24

// init {
//        qualityToImage(aDaylog?.oneCondition)
//    }

    private fun qualityToImage(quality: Int?) {

        when (quality) {

            in 0..10 -> imageUrl = R.drawable.ic_sentiment_very_dissatisfied_24px

            in 11..34 -> imageUrl = R.drawable.ic_sentiment_dissatisfied_black_18dp
            in 35..69 -> imageUrl = R.drawable.ic_sentiment_neutral_24px

            in 70..85 -> imageUrl = R.drawable.ic_sentiment_satisfied_24px

            in 86..100 -> imageUrl = R.drawable.ic_sentiment_very_satisfied_24px
            else -> imageUrl
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
