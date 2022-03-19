package com.example.coffeeapp.api.repository

import com.example.coffeeapp.api.retrofit.RetrofitInstanceCoffee
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import retrofit2.Response

class Repository {

    suspend fun postRegistration(jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ> = RetrofitInstanceCoffee.apiCoffee.postReg(jsonSend)

    suspend fun postLogin(jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ> = RetrofitInstanceCoffee.apiCoffee.postLogin(jsonSend)

}