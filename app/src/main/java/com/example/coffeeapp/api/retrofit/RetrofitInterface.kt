package com.example.coffeeapp.api.retrofit

import com.example.coffeeapp.api.retrofit.json.Locations_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import com.example.coffeeapp.api.retrofit.json.Menu_Answ
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {
    //@Header("Authorization") authToken: String

    @POST("auth/register")
    suspend fun postReg(@Body jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ>

    @POST("auth/login")
    suspend fun postLogin(@Body jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ>

    @GET("locations")
    suspend fun getLocations(@Header("Authorization") authToken: String): Response<Locations_Answ>

    @GET("location/{id}/menu")
    suspend fun getMenu(@Header("Authorization") authToken: String, @Path("id") id: Int): Response<Menu_Answ>

}