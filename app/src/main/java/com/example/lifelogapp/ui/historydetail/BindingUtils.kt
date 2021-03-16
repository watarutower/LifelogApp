package com.example.lifelogapp.ui.historydetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.lifelogapp.database.Lifelog
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("submitTime")
fun TextView.setTime(item: Lifelog) {
    text = SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm", Locale.getDefault()).format(item.submitTime)

}