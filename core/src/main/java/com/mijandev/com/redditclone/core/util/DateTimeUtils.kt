package com.mijandev.com.redditclone.core.util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
/**
 * Date Time Utils
 * To get current date time in string or date type
 * To calculate time passed for time creation/ time modification of an object/entity
 */
object DateTimeUtils {
    fun getCurrentDateTimeString(): String? {
        val currentDate = Calendar.getInstance().time
        try {
            val spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return spf.format(currentDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getFormattedDateTime(date: String?): Date? {
        val currentFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var dateType: Date? = null
        try {
            dateType = currentFormat.parse(date)
            return dateType
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun getTimeAgo(date: Date?): String = TimeAgo.getTimeAgo(date!!.time)

}