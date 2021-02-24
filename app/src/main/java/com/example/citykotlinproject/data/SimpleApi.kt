package com.example.citykotlinproject.data

import com.example.citykotlinproject.models.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("name/{country}")
    fun fetchCity(@Path("country") city: String): Call<MutableList<City>>

    @GET("all")
    fun fetchAll(): Call<MutableList<City>>
}