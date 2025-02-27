package com.example.frenchpastry.data.remote.ApiRepository

import com.example.frenchpastry.data.remote.DataModel.RequestMain
import com.example.frenchpastry.data.remote.MainApi.RetrofitService
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.data.remote.ext.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class MainApiRepository private constructor() {

    companion object {

        private var mainApiRepository: MainApiRepository? = null

        val instance: MainApiRepository
            get() {
                if (mainApiRepository == null) mainApiRepository = MainApiRepository()
                return mainApiRepository!!
            }
    }

    fun getMainContent(
        callBackRequest: CallBackRequest<RequestMain>
    ){

        RetrofitService.mainApiService.getContent().enqueue(
            object : Callback<RequestMain>{
                override fun onResponse(call: Call<RequestMain>, response: Response<RequestMain>) {
                    if (response.isSuccessful){
                        val data = response.body() as RequestMain
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

                override fun onFailure(call: Call<RequestMain>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )

    }

}