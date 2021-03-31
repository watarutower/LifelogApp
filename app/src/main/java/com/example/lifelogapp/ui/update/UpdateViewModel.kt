package com.example.lifelogapp.ui.update

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.EditText
import androidx.lifecycle.*

import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.receiver.AlarmReceiver
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateViewModel (
    dataSource: LifelogDao,
    application: Application): AndroidViewModel(application){

    val database = dataSource

    private val _navigateToHome = MutableLiveData<Boolean?>()

    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    var editText: String= ""

    var sliderFigures: Float = 0F


    private suspend fun insert(eachStatus: Lifelog) {
        database.insert(eachStatus)
    }

    fun doneNavigating() {
        _navigateToHome.value = null
    }


    private val REQUEST_CODE = 0
    private val TRIGGER_TIME = "TRIGGER_AT"

    private val second: Long = 1_000L

    private val notifyPendingIntent: PendingIntent
    private var prefs =
            application.getSharedPreferences("com.example.lifelogapp", Context.MODE_PRIVATE)
    private val notifyIntent = Intent(application, AlarmReceiver::class.java)

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    private val _startNotification = MutableLiveData<Boolean>()
    val startNotification: LiveData<Boolean>
        get() = _startNotification

    private lateinit var timer: CountDownTimer



    init {
        _startNotification.value = PendingIntent.getBroadcast(
                getApplication(),
                REQUEST_CODE,
                notifyIntent,
                PendingIntent.FLAG_NO_CREATE
        ) != null

        notifyPendingIntent = PendingIntent.getBroadcast(
                getApplication(),
                REQUEST_CODE,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (_startNotification.value!!) {
            createTimer()
        }
    }

    private fun createTimer() {
        viewModelScope.launch {
        val triggerTime = loadTime()
        timer = object : CountDownTimer(triggerTime, second) {
            override fun onTick(millisUntilFinished: Long) {
                _elapsedTime.value = triggerTime - SystemClock.elapsedRealtime()
                if (_elapsedTime.value!! <= 0) {
                    resetTimer()
                }
            }

            override fun onFinish() {
                resetTimer()
            }
        }
            timer.start()
        }
    }

    private fun resetTimer() {
        timer.cancel()
        _elapsedTime.value = 0
        _startNotification.value = false
    }


    private suspend fun loadTime(): Long =
            withContext(Dispatchers.IO) {
                prefs.getLong(TRIGGER_TIME, 0)
            }

    private fun beginInterval() {
        _startNotification.value?.let {
            if (!it) {
                _startNotification.value = true
                val testInterval = second * 8

                val triggerTime = SystemClock.elapsedRealtime() + testInterval

            }
        }
        createTimer()
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            startNotification.value?.let { beginInterval()}
//





//-------------
            val newStatus = Lifelog()
            newStatus.oneCondition = sliderFigures?.toInt()

            newStatus.oneComment = editText
            insert(newStatus)
            _navigateToHome.value = true
        }
    }

    fun onCancelClicked() {
        _navigateToHome.value = true
    }

}