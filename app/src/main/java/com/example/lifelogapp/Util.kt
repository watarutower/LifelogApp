package com.example.lifelogapp

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import com.example.lifelogapp.database.Lifelog
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}


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