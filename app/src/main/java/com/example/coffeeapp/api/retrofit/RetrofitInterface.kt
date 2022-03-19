package com.example.coffeeapp.api.retrofit

import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitInterface {
    //@Header("Authorization") authToken: String

    @POST("/auth/register")
    suspend fun postReg(@Body jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ>

    @POST("/auth/login")
    suspend fun postLogin(@Body jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ>

}