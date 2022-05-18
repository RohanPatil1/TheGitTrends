package com.rohan.thegittrends.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rohan.thegittrends.data.models.BuiltBy
import java.lang.reflect.Type
import java.util.*


class Convertors {

    @TypeConverter
    fun stringToListBuiltBy(data: String?): List<BuiltBy> {
        if (data == null) {
            return Collections.emptyList()
        }
        val gson = Gson()
        val listType: Type = object :
            TypeToken<List<BuiltBy>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listBuiltByToString(someObjects: List<BuiltBy>): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }
}