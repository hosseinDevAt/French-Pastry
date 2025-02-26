package com.example.frenchpastry.data.remote.ApiService

import com.example.frenchpastry.data.remote.DataModel.DefaultModel
import com.example.frenchpastry.data.remote.DataModel.UserInfoData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserInfoApiService {

    @GET("auth/heartbeat")
    fun getUserInfo(
        @Header("app-api-key") apiKey : String,
        @Header("app-device-uid") id : String,
        @Header("app-public-key") key : String,
    ):Call<UserInfoData>

    @FormUrlEncoded
    @POST("user/profile")
    fun setUserData(
        @Header("app-api-key") apiKey : String,
        @Header("app-device-uid") id : String,
        @Header("app-public-key") key : String,
        @Field("fullname") fullName : String,
        @Field("email") email : String,
        @Field("day") day : String,
        @Field("month") month : String,
        @Field("year") year : String,
        @Field("sex") sex : Int
    ):Call<DefaultModel>

}