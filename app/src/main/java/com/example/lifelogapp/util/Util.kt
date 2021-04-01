package com.example.lifelogapp.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.lifelogapp.R
import com.example.lifelogapp.database.Lifelog
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EE MM-dd' Time: 'HH:mm")
        .format(systemTime).toString()
}

@SuppressLint("SimpleDateFormat")
fun convertLongToTimeString(systemTime: Long): String {
    return SimpleDateFormat("HH:mm")
            .format(systemTime).toString()
}




@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(item: Lifelog?) {
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

//fun qualityToImage(quality: Int?) {
//    when (quality) {
//
//        in 0..10 -> imageUrl = R.drawable.ic_sentiment_very_dissatisfied_24px
//
//        in 11..34 -> imageUrl = R.drawable.ic_sentiment_dissatisfied_black_18dp
//        in 35..69 -> imageUrl = R.drawable.ic_sentiment_neutral_24px
//
//        in 70..85 -> imageUrl = R.drawable.ic_sentiment_satisfied_24px
//
//        in 86..100 -> imageUrl = R.drawable.ic_sentiment_very_satisfied_24px
//        else -> imageUrl
//    }
//}

//  全然ダメ
//data class Image(val uri: Uri =  R.drawable.ic_sentiment_very_dissatisfied_24px)
//
//@BindingAdapter("imageURL")
//fun ImageView.imageURI(uri: URI) {
//    Glide.with(this)
//            .load(uri)
//            .into(this)
//}

//@BindingAdapter("bind:imageUrl")
//fun loadImage(view: ImageView, url: String?) {
//    Glide.with(view.getContext()).load(url).into(view)
//}


//@BindingAdapter({"bind:imageUrl"})
//fun loadImage(view: ImageView, url: String?) {
//    Glide.with(view.getContext()).load(url).into(view)
//
//}

//fun qualityToImage(quality: Int?) :Int{
//    when(quality) {
//        in 0..10 ->
//            return R.drawable.ic_sentiment_very_dissatisfied_24px
//
//        in 11..34 ->
//            return R.drawable.ic_sentiment_dissatisfied_black_18dp
//        in 35..69 ->
//            return R.drawable.ic_sentiment_neutral_24px
//
//        in 70..85 ->
//            return R.drawable.ic_sentiment_satisfied_24px
//
//        in 86..100 ->
//            return R.drawable.ic_sentiment_very_satisfied_24px
//        else ->
//            return R.drawable.ic_baseline_autorenew_24
//    }
//






//fun convertNumericQualityToImage(quality: Float, resources: Resources): String {
//    setImageResource(when (item.sleepQuality) {
//        -1 -> qualityString = "--"
//        0 -> qualityString = resources.getString(R.string.zero_very_bad)
//        1 -> qualityString = resources.getString(R.string.one_poor)
//        2 -> qualityString = resources.getString(R.string.two_soso)
//        4 -> qualityString = resources.getString(R.string.four_pretty_good)
//        5 -> qualityString = resources.getString(R.string.five_excellent)
//    }
//    return qualityString
//}

fun formatDaylogs(daylogs: List<Lifelog>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<he>HERE IS YOUR DAY LOG</h3>")
        daylogs.forEach {
            append("<br>")
            append("<b>Time:</b>")
            append("\t${convertLongToDateString(it.submitTime)}<br>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            return Html.fromHtml(sb.toString())
        }
    }
}


data class commentData(var comment: String = "")