package com.mijandev.com.redditclone.core.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.mijandev.com.redditclone.core.data.DatabaseService
import com.mijandev.com.redditclone.core.data.dao.BaseDao

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Base class for Repository with constant function (Eg. insert, update, findAll)
 */
abstract class BaseRepository<T>(private val entityClass: Class<T>) {

    abstract val dao: BaseDao<T>

    suspend fun insert(entity: T): Long = dao.insert(entity)

    suspend fun update(entity: T) = dao.update(entity)

    fun findAll(
        column_name: String = "modified_at",
        sort_type: String = "DESC",
        limit: Int = 0
    ): List<T> {
        var sql =
            "SELECT * FROM ${DatabaseService.getTableName(entityClass)} ORDER BY $column_name $sort_type"
        if (limit > 0) sql += " LIMIT $limit"
        return dao.findAll(SimpleSQLiteQuery(sql))
    }

    fun findById(id: String): T {
        return dao.findById(
            SimpleSQLiteQuery(
                "SELECT * FROM ${
                    DatabaseService.getTableName(
                        entityClass
                    )
                } WHERE id = :id"
            )
        )
    }
}