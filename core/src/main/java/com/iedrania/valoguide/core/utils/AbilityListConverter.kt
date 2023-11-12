package com.iedrania.valoguide.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iedrania.valoguide.core.data.source.local.entity.AbilityEntity

class AbilityListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<AbilityEntity> {
        val listType = object : TypeToken<List<AbilityEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<AbilityEntity>): String {
        return gson.toJson(list)
    }
}