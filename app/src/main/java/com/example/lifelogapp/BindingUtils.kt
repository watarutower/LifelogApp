package com.example.lifelogapp

import android.widget.ImageView
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

@BindingAdapter("newCondition")
fun TextView.setCondition(item: Lifelog) {
    text = item.oneCondition.toString()
}

@BindingAdapter("newComment")
fun TextView.setComment(item: Lifelog) {
    text = item.oneComment
}


@BindingAdapter("logTimeFormatted")
fun TextView.formatDaylogs(item: Lifelog?) {
    item?.let {
        text = convertLongToDateString(item.submitTime)
    }
}
//
//@BindingAdapter("sleepDurationFormatted2")
//fun TextView.sleepDurationFormatted2(item: SleepNight?) {
//    item?.let {
//        text = formatNights2(item, context.resources)
//    }
//}
//
//@BindingAdapter("sleepQualityString")
//fun TextView.setSleepQualityString(item: SleepNight?) {
//    item?.let {
//        text = convertNumericQualityToString(item.sleepQuality, context.resources)
//    }
//}

object BindingUtils {


    @BindingAdapter("statusImage")
    @JvmStatic
    fun ImageView.setSleepImage(item: Lifelog?) {
        item?.let {
            setImageResource(when (item.oneCondition) {
                in 0..10 -> R.drawable.ic_sentiment_very_dissatisfied_24px
                in 11..34 -> R.drawable.ic_sentiment_dissatisfied_black_18dp
                in 35..69 -> R.drawable.ic_sentiment_neutral_24px

                in 70..85 -> R.drawable.ic_sentiment_satisfied_24px

                in 86..100 -> R.drawable.ic_sentiment_very_satisfied_24px
                else -> R.drawable.ic_baseline_autorenew_24
            })
        }
    }
}