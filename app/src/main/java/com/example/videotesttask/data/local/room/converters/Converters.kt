package com.example.taskdive.data.room.converters

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(date: Date?) = date?.time

    @TypeConverter
    fun toDate(millis: Long?) = millis?.let { Date(it) }

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {

        return if (value.isNullOrEmpty()) "" else gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        return if (value.isNullOrEmpty()) emptyList() else gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
    }
}
