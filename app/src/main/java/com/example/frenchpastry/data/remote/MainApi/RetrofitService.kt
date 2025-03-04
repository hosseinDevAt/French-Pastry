package com.example.frenchpastry.data.remote.MainApi

import com.example.frenchpastry.data.remote.ApiService.LoginApiService
import com.example.frenchpastry.data.remote.ApiService.MainApiService
import com.example.frenchpastry.data.remote.ApiService.PastryListApiService
import com.example.frenchpastry.data.remote.ApiService.UserInfoApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val BASE_URL = "https://pastry.alirezaahmadi.info/api/v1/"

    private val okHttp: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val loginApiService : LoginApiService = retrofit.create(LoginApiService::class.java)
    val userInfoService : UserInfoApiService = retrofit.create(UserInfoApiService::class.java)
    val mainApiService : MainApiService = retrofit.create(MainApiService::class.java)
    val pastryListApiService : PastryListApiService = retrofit.create(PastryListApiService::class.java)
}