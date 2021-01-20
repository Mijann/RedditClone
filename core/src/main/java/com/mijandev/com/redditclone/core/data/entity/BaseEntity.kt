package com.mijandev.com.redditclone.core.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mijandev.com.redditclone.core.data.typeconverter.TimeStampConverter
import java.io.Serializable
import java.util.*

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Base class for Room Entity with constant fields (Eg. id, createdAt, modifiedAt)
 */
@Entity
abstract class BaseEntity<T> : Serializable {
    @PrimaryKey
    @NonNull
    var id: T? = null

    @ColumnInfo(name = "created_at")
    @TypeConverters(TimeStampConverter::class)
    var createdAt: Date? = null

    @ColumnInfo(name = "modified_at")
    @TypeConverters(TimeStampConverter::class)
    var modifiedAt: Date? = null
}