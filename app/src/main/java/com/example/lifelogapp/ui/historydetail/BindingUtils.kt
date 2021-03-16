package com.example.lifelogapp.ui.historydetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.lifelogapp.convertLongToDateString
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.formatDaylogs
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("submitTime")
fun TextView.setTime(item: Lifelog) {
    text = convertLongToDateString(item.submitTime)
}