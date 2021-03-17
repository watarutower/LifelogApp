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
    var oneCondition: Float? = null,
//
//    @ColumnInfo(name = "one_mind")
//    val oneMind: Int? = null ,
//
    @ColumnInfo(name = "comment")
    var oneComment: String? = null,

    @ColumnInfo(name = "submit_time")
    val submitTime: Long =  System.currentTimeMillis()
)