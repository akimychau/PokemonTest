package com.example.pokemontest.mvp.model.database.converter

import androidx.room.TypeConverter
import com.example.pokemontest.mvp.model.entity.api.details.Type
import com.example.pokemontest.utils.fromJson
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun fromTypeList(value: List<Type>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTypeList(value: String): List<Type> {
        return try {
            Gson().fromJson<List<Type>>(value) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }
}