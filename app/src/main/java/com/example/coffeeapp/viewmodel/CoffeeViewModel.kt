package com.example.coffeeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.api.repository.Repository
import com.example.coffeeapp.api.retrofit.json.Locations_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Answ
import com.example.coffeeapp.api.retrofit.json.LogRegJSON_Send
import com.example.coffeeapp.api.retrofit.json.Menu_Answ
import com.example.coffeeapp.const.Consts
import com.example.coffeeapp.const.Consts.YANDEX_TOKEN_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoffeeViewModel: ViewModel() {

    var authToken: String = ""
    var latUser: Double? = null
    var lonUser: Double? = null
    val loginRequestLivedata by lazy {
        MutableLiveData<Response<LogRegJSON_Answ>>()
    }
    val regRequestLivedata by lazy {
        MutableLiveData<Response<LogRegJSON_Answ>>()
    }
    val locationsRequestLivadata by lazy {
        MutableLiveData<Response<Locations_Answ>>()
    }
    val menuRequestLivadata by lazy {
        MutableLiveData<Response<Menu_Answ>>()
    }
    private val repository by lazy {
        Repository()
    }

    fun postRequestRegistration(jsonSend: LogRegJSON_Send){
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repository.postRegistration(jsonSend)
            withContext(Dispatchers.Main){
                regRequestLivedata.value = resp
            }

        }
    }

    fun postRequestLogin(jsonSend: LogRegJSON_Send){
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repository.postLogin(jsonSend)
            withContext(Dispatchers.Main){
                loginRequestLivedata.value = resp
            }

        }
    }

    fun getLocations(){
        viewModelScope.launch(Dispatchers.IO) {
            val token = authToken
            val resp = repository.getLocations(token)
            withContext(Dispatchers.Main){
                Log.d("TAG_t", resp.raw().toString())
                Log.d("TAG_t", resp.raw().request().method().toString())
                Log.d("TAG_t", resp.raw().request().headers().toString())
                Log.d("TAG_t", resp.raw().request().body().toString())
                Log.d("TAG_t", resp.raw().request().url().toString())


                locationsRequestLivadata.value = resp
            }

        }
    }

    fun getMenu(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repository.getMenu(authToken, id)
            withContext(Dispatchers.Main){
                menuRequestLivadata.value = resp
            }

        }
    }

}