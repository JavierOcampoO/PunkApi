package com.test.punkapi.data.database.dataconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class BeerDataConverter {

    @TypeConverter
    fun fromFoodPairList(foods: List<String>?): String?{
        if (foods == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.type
        return gson.toJson(foods, type)
    }

    @TypeConverter
    fun toFoodPairList(foods: String?): List<String>? {
        if (foods == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson<List<String>?>(foods, type)
    }

}