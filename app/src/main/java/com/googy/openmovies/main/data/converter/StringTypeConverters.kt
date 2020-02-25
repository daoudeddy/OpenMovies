package com.googy.openmovies.main.data.converter

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

open class StringTypeConverters {
    @TypeConverter
    fun stringToMeasurements(json: String): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<List<String>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(list, type)
    }
}