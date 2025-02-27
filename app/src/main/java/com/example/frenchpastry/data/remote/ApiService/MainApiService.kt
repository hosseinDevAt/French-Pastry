package com.example.frenchpastry.data.remote.ApiService

import com.example.frenchpastry.data.remote.DataModel.RequestMain
import retrofit2.Call
import retrofit2.http.GET

interface MainApiService {

    @GET("main")
    fun getContent():Call<RequestMain>

}