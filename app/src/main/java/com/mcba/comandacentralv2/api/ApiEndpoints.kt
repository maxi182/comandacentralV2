package com.mcba.comandacentralv2.api

import com.mcba.comandacentralv2.data.model.DrinkList
import com.mcba.comandacentralv2.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {

    @GET("search.php")
    suspend fun getTragoByName(@Query(value = "s") tragoName:String): DrinkList

    @GET("search.php")
    suspend fun getUser(@Query(value = "s") tragoName:String): User
}