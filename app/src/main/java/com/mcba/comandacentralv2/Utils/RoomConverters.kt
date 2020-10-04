package com.mcba.comandacentralv2.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mcba.comandacentralv2.data.model.*
import java.lang.reflect.Type


class RoomConverters {

    @TypeConverter
    fun comandaProductItemToString(app: ComandaProductItemEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToComandaProductItem(string: String): ComandaProductItemEntity = Gson().fromJson(string, ComandaProductItemEntity::class.java)

    @TypeConverter
    fun fromComandaItemToList(countryLang: List<ComandaItemEntity?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ComandaItemEntity?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toComandaItemToList(countryLangString: String?): List<ComandaItemEntity>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<ComandaItemEntity?>?>() {}.type
        return gson.fromJson<List<ComandaItemEntity>>(countryLangString, type)
    }

    @TypeConverter
    fun fromLoteToList(countryLang: List<Lote?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Lote?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toLoteToList(countryLangString: String?): List<Lote>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Lote?>?>() {}.type
        return gson.fromJson<List<Lote>>(countryLangString, type)
    }

    @TypeConverter
    fun fromProductDataToList(countryLang: List<ProductData?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductData?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toProductDataToList(countryLangString: String?): List<ProductData>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<ProductData?>?>() {}.type
        return gson.fromJson<List<ProductData>>(countryLangString, type)
    }

    @TypeConverter
    fun fromProductTypeToList(countryLang: List<ProductType?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductType?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toProductTypeToList(countryLangString: String?): List<ProductType>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<ProductType?>?>() {}.type
        return gson.fromJson<List<ProductType>>(countryLangString, type)
    }
}