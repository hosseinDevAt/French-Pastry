package com.example.frenchpastry.data.remote.ApiRepository

import com.example.frenchpastry.data.remote.DataModel.DefaultModel
import com.example.frenchpastry.data.remote.DataModel.RequestSendPhone
import com.example.frenchpastry.data.remote.DataModel.RequestVerifyCode
import com.example.frenchpastry.data.remote.MainApi.RetrofitService
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.data.remote.ext.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class LoginApiRepository private constructor(){

    companion object {

        private var loginApiRepository : LoginApiRepository? = null

        val instance : LoginApiRepository
            get() {
                if (loginApiRepository == null) loginApiRepository = LoginApiRepository()
                return loginApiRepository!!
            }
    }

    fun sendPhoneAuth(
        id : String,
        key : String,
        phone : String,
        callBackRequest: CallBackRequest<RequestSendPhone>
    ){

        RetrofitService.loginApiService.sendCode(
            id,
            key,
            phone
        ).enqueue(
            object : Callback<RequestSendPhone>{
                override fun onResponse(
                    call: Call<RequestSendPhone>,
                    response: Response<RequestSendPhone>
                ) {
                    if (response.isSuccessful){
                        callBackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestSendPhone
                        )
                    }else {
                        val error = ErrorHandler.parseError(response)
                        callBackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<RequestSendPhone>, t: Throwable) {
                    if (t is SocketTimeoutException){
                        callBackRequest.onError("تایم اوت")
                    }else{
                        callBackRequest.onError(t.message.toString())
                    }
                }
            }
        )

    }


    fun verifyCode(
        code :String,
        phone: String,
        callBackRequest: CallBackRequest<RequestVerifyCode>
    ){

        RetrofitService.loginApiService.verifyCode(
            code,
            phone
        ).enqueue(
            object : Callback<RequestVerifyCode>{
                override fun onResponse(
                    call: Call<RequestVerifyCode>,
                    response: Response<RequestVerifyCode>
                ) {
                    if (response.isSuccessful){
                        callBackRequest.onSuccess(
                            response.code(),
                            response.body() as RequestVerifyCode
                        )
                    }else {
                        val error = ErrorHandler.parseError(response)
                        callBackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<RequestVerifyCode>, t: Throwable) {
                    if (t is SocketTimeoutException){
                        callBackRequest.onError("تایم اوت")
                    }else{
                        callBackRequest.onError(t.message.toString())
                    }
                }
            }
        )

    }

    fun editUser(
        apiKey : String,
        id: String,
        key: String,
        fullName : String,
        callBackRequest: CallBackRequest<DefaultModel>
    ){

        RetrofitService.loginApiService.editUser(
            apiKey,
            id,
            key,
            fullName
        ).enqueue(
            object : Callback<DefaultModel>{
                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {
                    if (response.isSuccessful){
                        callBackRequest.onSuccess(
                            response.code(),
                            response.body() as DefaultModel
                        )
                    }else {
                        var error = ErrorHandler.parseError(response)
                        callBackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }
                }

                override fun onFailure(call: Call<DefaultModel>, t: Throwable) {
                    if (t is SocketTimeoutException){
                        callBackRequest.onError("تایم اوت")
                    }else{
                        callBackRequest.onError(t.message.toString())
                    }
                }
            }
        )

    }


}