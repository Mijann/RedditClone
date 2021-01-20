package com.mijandev.com.redditclone.core.data

import com.mijandev.com.redditclone.core.data.entity.ErrorEntity

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Database Service
 * To retrieve entity class to cater for SQL Query
 * To retrieve table name to cater for SQL Query
 */
object DatabaseService {

    val DATABASE_NAME = "redditclone.db"

    fun getEntityClass(tableName: String): Class<*>? {
        return when (tableName) {
            "SomeTableThatDoesntMatchClassName" -> ErrorEntity::class.java
            else -> Class.forName(tableName)
        }
    }

    fun getTableName(entityClass: Class<*>): String? {
        return when (entityClass) {
            ErrorEntity::class.java -> "SomeTableThatDoesntMatchClassName"
            else -> entityClass.simpleName
        }
    }
}