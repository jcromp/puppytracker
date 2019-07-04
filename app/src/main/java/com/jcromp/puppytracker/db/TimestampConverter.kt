package com.jcromp.puppytracker.db

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimestampConverter {
    internal var df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        if (value != null) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }
        return null
    }

    @TypeConverter
    fun toTimestamp(date: Date?): String? {
        return if (date == null) null else df.format(date)
    }
}