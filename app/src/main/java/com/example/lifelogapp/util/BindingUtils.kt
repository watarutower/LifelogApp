package com.example.lifelogapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import com.example.lifelogapp.database.WorkLog
import com.example.lifelogapp.ui.home.ConditionQuality

@BindingAdapter("submitTime")
fun TextView.setTime(item: Lifelog) {
    text = convertLongToTimeString(item.submitTime)
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
        text =
            convertLongToDateString(item.submitTime)
    }


}
@BindingAdapter("daysIndex")
fun TextView.setDays(item: String) {
    text = item
}

//    WorkLogsのアダプター
@BindingAdapter("startTime")
fun TextView.setStartTime(item: WorkLog) {
    text = convertLongToTimeString(item.startTimeMilli)
}

@BindingAdapter("finishTime")
fun TextView.setFinishTime(item: WorkLog) {
    text = convertLongToTimeString(item.endTimeMilli)
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
    @JvmStatic fun statusImage(view: ImageView, conditionQuality: ConditionQuality) {
        view.setImageDrawable(getDrawableConditionQuality(conditionQuality, view.context))
    }

    @BindingAdapter("dayComment")
    @JvmStatic fun dayComment(view: TextView, review: String) {
        view.setText(formatReview(review))
    }

    @BindingAdapter("carryOverComment")
    @JvmStatic fun takeOverComment(view: EditText, review: String) {
        view.setText(formatReview(review))
    }
}

fun formatReview (review: String): Spanned {
    val sb = StringBuilder()
    sb.apply{
        append(review)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}

fun getDrawableConditionQuality(conditionQuality: ConditionQuality, context: Context): Drawable? {
    return when (conditionQuality) {
        ConditionQuality.VERY_DISSATISFIED -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_very_dissatisfied_24px)
        }
        ConditionQuality.DISSATISFIED -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_dissatisfied_black_18dp)
        }
        ConditionQuality.NEUTRAL -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_neutral_24px)
        }
        ConditionQuality.SATISFIED -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_satisfied_24px)
        }
        ConditionQuality.SMILE -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_very_satisfied_black_18dp)
        }

        ConditionQuality.VERY_SATISFIED -> {
            ContextCompat.getDrawable(context, R.drawable.ic_sentiment_very_satisfied_24px)
        }
        else -> {
            ContextCompat.getDrawable(context, R.drawable.ic_baseline_autorenew_24)
        }
    }
}





//    @BindingAdapter("statusImage")
//    @JvmStatic
//    fun ImageView.setSleepImage(item: Lifelog?) {
//        item?.let {
//            setImageResource(when (item.oneCondition) {
//                in 0..10 -> R.drawable.ic_sentiment_very_dissatisfied_24px
//                in 11..34 -> R.drawable.ic_sentiment_dissatisfied_black_18dp
//                in 35..69 -> R.drawable.ic_sentiment_neutral_24px
//
//                in 70..85 -> R.drawable.ic_sentiment_satisfied_24px
//
//                in 86..100 -> R.drawable.ic_sentiment_very_satisfied_24px
//                else -> R.drawable.ic_baseline_autorenew_24
//            })
//        }
//    }