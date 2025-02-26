package com.example.frenchpastry.data.remote.ApiRepository

import com.example.frenchpastry.data.remote.ApiService.UserInfoApiService
import com.example.frenchpastry.data.remote.DataModel.DefaultModel
import com.example.frenchpastry.data.remote.DataModel.UserInfoData
import com.example.frenchpastry.data.remote.MainApi.RetrofitService
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.data.remote.ext.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class UserInfoApiRepository private constructor(){

    companion object {

        private var userInfoApiRepository: UserInfoApiRepository? = null

        val instance: UserInfoApiRepository
            get() {
                if (userInfoApiRepository == null) userInfoApiRepository = UserInfoApiRepository()
                return userInfoApiRepository!!
            }

    }

    fun getUserInfo(
        apiKey : String,
        id : String,
        key : String,
        callBackRequest: CallBackRequest<UserInfoData>
    ){

        RetrofitService.userInfoService.getUserInfo(apiKey, id, key).enqueue(
            object : Callback<UserInfoData>{
                override fun onResponse(
                    call: Call<UserInfoData>,
                    response: Response<UserInfoData>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as UserInfoData
                        callBackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    }else{
                        val error = ErrorHandler.parseError(response)
                        callBackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<UserInfoData>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )

    }

    fun setUserInfo(
        apiKey : String,
        id : String,
        key : String,
        fullName : String,
        email : String,
        day : String,
        month : String,
        year : String,
        sex : Int,
        callBackRequest: CallBackRequest<DefaultModel>
    ){

        RetrofitService.userInfoService.setUserData(
            apiKey,
            id,
            key,
            fullName,
            email,
            day,
            month,
            year,
            sex,
        ).enqueue(
            object : Callback<DefaultModel>{
                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as DefaultModel
                        callBackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    }else{
                        val error = ErrorHandler.parseError(response)
                        callBackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<DefaultModel>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )

    }


}