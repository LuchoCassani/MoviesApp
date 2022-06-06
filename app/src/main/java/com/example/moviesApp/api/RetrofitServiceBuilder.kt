package com.example.moviesApp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitServiceBuilder (baseurl:String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>):T{
        return retrofit.create(service)
    }

}
