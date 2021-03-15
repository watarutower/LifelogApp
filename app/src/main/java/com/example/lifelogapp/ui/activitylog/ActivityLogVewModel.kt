package com.example.lifelogapp.ui.activitylog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityLogViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ActivityLogFragment"
    }
    val text: LiveData<String> = _text
}