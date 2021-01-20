package com.mijandev.com.redditclone.core.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mijandev.com.redditclone.core.data.entity.TopicEntity

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Topic DAO with custom functions (Eg. deleteTopic, saveAll)
 */
@Dao
interface TopicDao : BaseDao<TopicEntity> {
    @Query("DELETE FROM `TopicEntity` WHERE id = :id")
    fun deleteTopic(id : String)

    @Insert
    fun saveAll(entities: List<TopicEntity>)
}