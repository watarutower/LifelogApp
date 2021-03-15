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

    @ColumnInfo(name = "one_condition")
    val oneCondition: Int,

    @ColumnInfo(name = "one_mind")
    val oneMind: Int?,

    @ColumnInfo(name = "comment")
    val oneComment: String?,

    @ColumnInfo(name = "one_time")
    val oneTime: Long =  System.currentTimeMillis()
)