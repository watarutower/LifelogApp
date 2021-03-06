package com.myapplication.lifelogapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lifelog::class ,Preview::class, WorkLog::class], version = 2, exportSchema = false)
abstract class LifelogDatabase : RoomDatabase() {

    abstract val lifeLogDao: LifelogDao

    companion object {
        @Volatile
        private var INSTANCE: LifelogDatabase? = null

        fun getInstance(context: Context): LifelogDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LifelogDatabase::class.java,
                        "lifelog_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}