package com.example.coffeeapp.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceCoffee {

    private val retrofitCoffee by lazy {
        Retrofit.Builder()
            .baseUrl("http://185.244.172.108:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiCoffee: RetrofitInterface by lazy {
        retrofitCoffee.create(RetrofitInterface::class.java)
    }

}