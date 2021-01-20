package com.mijandev.com.redditclone.core.data.typeconverter

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Timestamp Converter for Date type field for Room Entity
 * To convert date type to string or vice versa
 */
object TimeStampConverter {
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                val timeZone = TimeZone.getTimeZone("Asia/Kuala_Lumpur")
                df.timeZone = timeZone
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(value: Date?): String? {
        val timeZone = TimeZone.getTimeZone("Asia/Kuala_Lumpur")
        df.timeZone = timeZone
        return if (value == null) null else df.format(value)
    }
}