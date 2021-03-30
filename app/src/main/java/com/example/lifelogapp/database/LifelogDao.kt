package com.example.lifelogapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LifelogDao {

    @Insert
    suspend fun insert(newStatus: Lifelog )

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM each_status_table ORDER BY statusId DESC")
    fun getDayLogs(): LiveData<List<Lifelog>>
    /**
     * Selects and returns the latest status.
     */


    @Query("SELECT condition FROM each_status_table ORDER BY statusId DESC LIMIT 1")
    suspend fun getOneStatus(): Int
//    MutableLiveData<Lifelog?>

    /** まだ不明
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from each_status_table WHERE statusId = :key")
    suspend fun get(key: Long): Lifelog

    /**
     * Selects and returns the night with given statusId.
     */
//    @Query("SELECT * from each_status_table WHERE statusId = :key")
//    fun getStatusWithId(key: String): LiveData<List<Lifelog>>

//    @Query("select statusId,condition,comment,date(substr(printf('%d',submit_time),1,10),'unixepoch','localtime') from each_status_table WHERE date(substr(printf('%d',submit_time),1,10),'unixepoch','localtime') = :key order by statusId desc")
//    fun getStatusWithId(key: String): LiveData<List<Lifelog>>

    @Query("SELECT * FROM each_status_table WHERE date(substr(printf('%d',submit_time),1,10),'unixepoch','localtime') = :key order by statusId asc")
    fun getStatusWithId(key: String?): LiveData<List<Lifelog>>
//    @Query("SELECT condition FROM each_status_table ORDER BY statusId DESC LIMIT 1")

    @Query("SELECT * FROM each_status_table ORDER BY statusId DESC")
    fun getDayLogsList(): List<Lifelog>


    @Query("select distinct date(substr(printf('%d',submit_time),1,10),'unixepoch','localtime')from each_status_table order BY statusId DESC")
    fun getStatusByDay(): LiveData<List<String>>

//   -------- history detail
    @Insert
    suspend fun insert(newPreview: Preview)

    @Query("SELECT review_comment FROM preview_table WHERE the_date = :day ORDER BY dateId DESC LIMIT 1")
    suspend fun getReviewComment(day: String?): String?

    @Query("SELECT * FROM preview_table WHERE the_date = :day ORDER BY dateId DESC LIMIT 1")
    suspend fun getOnePreview(day: String?): Preview

    //    WriteReview
    @Query("SELECT review_comment FROM preview_table WHERE the_date = :day ORDER BY dateId DESC LIMIT 1")
    suspend fun getLastComment(day: String?): String?

    @Update
    suspend fun update(preview: Preview)

//    ----Work Log
    @Insert
    suspend fun insert(work: WorkLog)

    @Update
    suspend fun update(work: WorkLog)

    @Query("SELECT * FROM each_work_log_table ORDER BY workId DESC LIMIT 1")
    suspend fun getWorkLog(): WorkLog?

    @Query("SELECT * FROM each_work_log_table ORDER BY workId DESC")
    fun getWorkedLogs(): LiveData<List<WorkLog>>



}
//@Dao interface PreviewDao {
//
//}
