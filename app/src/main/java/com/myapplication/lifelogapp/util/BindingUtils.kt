package com.myapplication.lifelogapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.myapplication.lifelogapp.R
import com.myapplication.lifelogapp.database.Lifelog
import com.myapplication.lifelogapp.database.WorkLog
import com.myapplication.lifelogapp.ui.home.ConditionQuality

//  recyclerviewのアダプター

@BindingAdapter("submitTime")
fun TextView.setTime(item: Lifelog) {
    text = convertLongToTimeString(item.submitTime)
}

@BindingAdapter("submitDate")
fun TextView.seDate(item: Lifelog) {
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



object BindingUtils {

    @BindingAdapter("statusImage")
    @JvmStatic fun statusImage(view: ImageView, conditionQuality: ConditionQuality) {
        view.setImageDrawable(getDrawableConditionQuality(conditionQuality, view.context))
    }

    @BindingAdapter("dayComment")
    @JvmStatic fun TextView.setDayComment(review: String) {
        text = review
    }


    @BindingAdapter("carryOverComment")
    @JvmStatic fun TextView.setTakeOverComment(review: String) {
       text = review
    }

    @BindingAdapter("conditionAverage")
    @JvmStatic fun TextView.setConditionAverage(average: Float) {
        text = average.toString()
    }

    @BindingAdapter("memoComment")
    @JvmStatic fun TextView.setMemoComment(memo: String) {
        text = memo
    }

    @BindingAdapter("hideKeyboardOnInputDone")
    @JvmStatic fun hideKeyboardOnInputDone(view: EditText, enabled: Boolean) {
        if (!enabled) return
        val listener = TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.clearFocus()
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                        as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }
        view.setOnEditorActionListener(listener)
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
fun formatMemo (memo: String): Spanned {
    val sb = StringBuilder()
    sb.apply{
        append(memo)
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
