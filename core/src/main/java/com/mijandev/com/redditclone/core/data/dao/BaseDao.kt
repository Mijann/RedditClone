package com.mijandev.com.redditclone.core.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.mijandev.com.redditclone.core.data.entity.BaseEntity
import com.mijandev.com.redditclone.core.data.entity.TopicEntity

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Base class for DAO with constant function (Eg. insert, update, findAll)
 */
@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<T>): List<Long?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: T)

    @RawQuery
    fun findAll(query: SupportSQLiteQuery): List<T>

    @RawQuery
    fun findById(query: SupportSQLiteQuery): T

}