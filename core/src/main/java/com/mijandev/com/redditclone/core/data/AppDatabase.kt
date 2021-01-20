package com.mijandev.com.redditclone.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mijandev.com.redditclone.core.data.dao.TopicDao
import com.mijandev.com.redditclone.core.data.entity.TopicEntity

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Topic Room Database class instance
 */
@Database(
    entities = [TopicEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topicDao(): TopicDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context, testMode: Boolean = false): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = if (!testMode)
                            buildDatabase(context)
                        else
                            buildTestDatabase(context)
                    }
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DatabaseService.DATABASE_NAME
            ).allowMainThreadQueries().build()
        }

        private fun buildTestDatabase(context: Context): AppDatabase =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
    }
}