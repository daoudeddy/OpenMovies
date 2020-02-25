package com.googy.openmovies.main.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.googy.openmovies.main.data.model.Items

open class ItemTypeConverters {
    @TypeConverter
    fun stringToMeasurements(json: String): List<Items>? {
        val gson = Gson()
        val type = object : TypeToken<List<Items>>() {}.type
        return gson.fromJson<List<Items>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<Items>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Items>>() {}.type
        return gson.toJson(list, type)
    }
}