package com.example.lifelogapp.ui.update

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.EditText
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*

import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.LifelogDao
import com.example.lifelogapp.receiver.AlarmReceiver
import com.example.lifelogapp.util.cancelNotifications
import com.example.lifelogapp.util.sendNotification
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateViewModel (private val app: Application,
    dataSource: LifelogDao): AndroidViewModel(app){

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
    private val minute: Long = 60_000L
    private val hour: Long = 360_000L

    private val notifyPendingIntent: PendingIntent

    private val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var prefs =
            app.getSharedPreferences("com.example.lifelogapp", Context.MODE_PRIVATE)
    private val notifyIntent = Intent(app, AlarmReceiver::class.java)

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


    private fun beginInterval() {
        _startNotification.value?.let {
            if (!it) {
                _startNotification.value = true
//                テスト用
//                val testInterval = second * 5
                val notifyInterval = hour * 1

                val triggerTime = SystemClock.elapsedRealtime() + notifyInterval

//                val notificationManager =
//                        ContextCompat.getSystemService(
//                                app,
//                                NotificationManager::class.java
//                        ) as NotificationManager
//                notificationManager.sendNotification(app.getString(R.string.timer_running), app)

                val notificationManager =
                        ContextCompat.getSystemService(
                    app,
                    NotificationManager::class.java
                ) as NotificationManager
                               notificationManager.cancelNotifications()

                AlarmManagerCompat.setAndAllowWhileIdle(
                        alarmManager,
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime,
                        notifyPendingIntent
                )
                viewModelScope.launch {
                    saveTime(triggerTime)
                }
            }
        }
        createTimer()
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

    private fun cancelNotification() {
        resetTimer()
        alarmManager.cancel(notifyPendingIntent)
    }

    /**
     * Resets the timer on screen and sets alarm value false
     */
    private fun resetTimer() {
        timer.cancel()
        _elapsedTime.value = 0
        _startNotification.value = false
    }

    private suspend fun loadTime(): Long =
            withContext(Dispatchers.IO) {
                prefs.getLong(TRIGGER_TIME, 0)
            }
    private suspend fun saveTime(triggerTime: Long) =
            withContext(Dispatchers.IO) {
                prefs.edit().putLong(TRIGGER_TIME, triggerTime).apply()
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

