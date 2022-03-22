package com.example.coffeeapp.api.repository

import com.example.coffeeapp.api.retrofit.RetrofitInstanceCoffee
import com.example.coffeeapp.api.retrofit.json.Locations_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import com.example.coffeeapp.api.retrofit.json.Menu_Answ
import retrofit2.Response

class Repository {

    suspend fun postRegistration(jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ> = RetrofitInstanceCoffee.apiCoffee.postReg(jsonSend)

    suspend fun postLogin(jsonSend: LogRegJSON_Send): Response<LogRegJSON_Answ> = RetrofitInstanceCoffee.apiCoffee.postLogin(jsonSend)

    suspend fun getLocations(authToken: String): Response<Locations_Answ> = RetrofitInstanceCoffee.apiCoffee.getLocations(authToken)

    suspend fun getMenu(authToken: String, id: Int): Response<Menu_Answ> = RetrofitInstanceCoffee.apiCoffee.getMenu(authToken, id)

}