package com.example.lifelogapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LifelogDao {

    @Insert
    suspend fun insert(newStatus: Lifelog )

    /** まだ不明
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from each_status_table WHERE statusId = :key")
    suspend fun get(key: Long): Lifelog

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
    @Query("SELECT * FROM each_status_table ORDER BY statusId DESC LIMIT 1")
    suspend fun getOneStatus(): Lifelog
//    MutableLiveData<Lifelog?>

    /**
     * Selects and returns the night with given statusId.
     */
    @Query("SELECT * from each_status_table WHERE statusId = :key")
    fun getStatusWithId(key: Long): LiveData<Lifelog>
}

