package com.example.lifelogapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.time.LocalDateTime



@Entity(tableName = "each_status_table")
data class Lifelog(
    @PrimaryKey(autoGenerate = true)
    var statusId: Long = 0L,

    @ColumnInfo(name = "condition")
    var oneCondition: Int = 0,
//
//    @ColumnInfo(name = "one_mind")
//    val oneMind: Int? = null ,
//
    @ColumnInfo(name = "comment")
    var oneComment: String = "",

    @ColumnInfo(name = "submit_time")
    val submitTime: Long =  System.currentTimeMillis()
)


@Entity(tableName = "preview_table")
data class Preview(
        @PrimaryKey(autoGenerate = true)
        var dateId: Long = 0L,

        @ColumnInfo(name ="flag")
        var flag: Long = 0L,

        @ColumnInfo(name = "the_date")
        var theDate: String? = "",

        @ColumnInfo(name = "review_comment")
        var reviewComment: String? = ""
)

@Entity(tableName = "each_work_log_table")
data class WorkLog(
        @PrimaryKey(autoGenerate = true)
        var workId: Long = 0L,

//        @ColumnInfo(name = "work_type")
//        var type: String = "",
//
        @ColumnInfo(name = "start_time_milli")
        val startTimeMilli: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "end_time_milli")
        var endTimeMilli: Long = startTimeMilli)
