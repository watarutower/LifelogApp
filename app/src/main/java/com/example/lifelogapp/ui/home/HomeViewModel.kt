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

    private var aStatus = MutableLiveData<Lifelog?>()
    //for adapter
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

//    private var aStatus: Lifelog? = null
    init {
        initializeStatus()
    }

    private fun initializeStatus() {
        viewModelScope.launch {
            aStatus.value = getStatusFromDatabase()
        }
    }

    private suspend fun getStatusFromDatabase(): Lifelog? {
        var funStatus = database.getOneStatus()
        return funStatus
    }

//    var statuslog = aStatus

//    var numericHolder: Int? = null
//    init{
//        numericHolder = aStatus?.oneCondition
//    }

//    init {
//        aStatus = database.getOneStatus()
//    }

    var imageUrl = R.drawable.ic_baseline_star_border_24
    //    private val _imageUrl = MutableLiveData<Int>()
//    val imageUrl: LiveData<Int?>
//        get() = _imageUrl
 init {
        qualityToImage(aStatus.value?.oneCondition)
    }

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



//    var homeImage =  qualityToImage(aStatus?.oneCondition)

//    fun qualityToImage(quality: Int?) :Int{
//        when(quality) {
//            in 0..10 ->
//                return R.drawable.ic_sentiment_very_dissatisfied_24px
//
//            in 11..34 ->
//                return R.drawable.ic_sentiment_dissatisfied_black_18dp
//            in 35..69 ->
//                return R.drawable.ic_sentiment_neutral_24px
//
//            in 70..85 ->
//                return R.drawable.ic_sentiment_satisfied_24px
//
//            in 86..100 ->
//                return R.drawable.ic_sentiment_very_satisfied_24px
//            else ->
//                return R.drawable.ic_baseline_autorenew_24
//        }



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
