package com.example.lifelogapp.ui.update

import android.app.Application
import android.widget.EditText

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_util.*
import kotlinx.coroutines.launch

class UpdateViewModel (
    dataSource: LifelogDao,
    application: Application): ViewModel(){

    val database = dataSource


    private val _navigateToHome = MutableLiveData<Boolean?>()

    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    private val _updateComment = MutableLiveData<String?>()

    val updateComment: LiveData<String?>
        get() = _updateComment

    var editText: String= ""

    var sliderFigures: Float = 1f


    private suspend fun insert(eachStatus: Lifelog) {
        database.insert(eachStatus)
    }

    fun doneNavigating() {
        _navigateToHome.value = null
    }


//val slider = Slider(application)

    fun onSubmitClicked() {
        viewModelScope.launch {
//            val editText = mainActivity.findViewById<EditText>(R.id.newComment) {
//                addComment(it)
//
//          _updateComment.value = editText

            val newStatus = Lifelog()
            newStatus.oneCondition = sliderFigures

            newStatus.oneComment = editText
            insert(newStatus)
            _navigateToHome.value = true
        }
    }

    fun onCancelClicked() {
        _navigateToHome.value = true
    }

}