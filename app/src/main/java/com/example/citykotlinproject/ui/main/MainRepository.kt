package com.example.citykotlinproject.ui.main

import com.example.citykotlinproject.data.RetrofitClient
import com.example.citykotlinproject.models.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val callback: RequestResult) {

    private var api = RetrofitClient().simpleApi

    fun fetchCity(city: String) {
        api.fetchCity(city).enqueue(object: Callback<MutableList<City>> {

            override fun onFailure(call: Call<MutableList<City>>, t: Throwable) {
                return callback.onFailure(t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<City>>,
                response: Response<MutableList<City>>
            ) {
                return if (response.body() != null) callback.onSuccess(response.body()!!)
                else callback.onFailure("error")
            }
        })
    }

}