package com.example.coffeeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.api.repository.Repository
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import kotlinx.coroutines.launch
import retrofit2.Response

class CoffeeViewModel: ViewModel() {

    var authToken: String = ""
    val loginRequestLivedata by lazy {
        MutableLiveData<Response<LogRegJSON_Answ>>()
    }
    val regRequestLivedata by lazy {
        MutableLiveData<Response<LogRegJSON_Answ>>()
    }
    private val repository by lazy {
        Repository()
    }

    fun postRequestRegistration(jsonSend: LogRegJSON_Send){
        viewModelScope.launch {
            val resp = repository.postRegistration(jsonSend)
            regRequestLivedata.value = resp
        }
    }

    fun postRequestLogin(jsonSend: LogRegJSON_Send){
        viewModelScope.launch {
            val resp = repository.postLogin(jsonSend)
            loginRequestLivedata.value = resp
        }
    }

}