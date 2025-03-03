package com.example.frenchpastry.data.remote.ApiRepository

import com.example.frenchpastry.adapter.recycler.ListPastriesModel
import com.example.frenchpastry.data.remote.DataModel.AllPastriesModel
import com.example.frenchpastry.data.remote.MainApi.RetrofitService
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.data.remote.ext.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PastryListApiRepository private constructor() {

    companion object {
        private var apiRepository: PastryListApiRepository? = null

        val instance: PastryListApiRepository
            get() {
                if (apiRepository == null) apiRepository = PastryListApiRepository()
                return apiRepository!!
            }
    }

    fun getPastries(
        id : Int,
        callBackRequest: CallBackRequest<ListPastriesModel>
    ){
        RetrofitService.pastryListApiService.getPastries(id, true).enqueue(
            object : Callback<ListPastriesModel>{
                override fun onResponse(
                    call: Call<ListPastriesModel>,
                    response: Response<ListPastriesModel>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as ListPastriesModel
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

                override fun onFailure(call: Call<ListPastriesModel>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )
    }

    fun getPastryByType(
        type : String,
        callBackRequest: CallBackRequest<AllPastriesModel>
    ){
        RetrofitService.pastryListApiService.getPastriesByType(type).enqueue(
            object : Callback<AllPastriesModel>{
                override fun onResponse(
                    call: Call<AllPastriesModel>,
                    response: Response<AllPastriesModel>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as AllPastriesModel
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

                override fun onFailure(call: Call<AllPastriesModel>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )
    }


    fun getFavoritePastry(
        apikey : String,
        id: String,
        pubKey : String,
        favorite : Boolean,
        callBackRequest: CallBackRequest<AllPastriesModel>
    ){
        RetrofitService.pastryListApiService.getFavoritePastries(
            apikey,
            id,
            pubKey,
            favorite
        ).enqueue(
            object : Callback<AllPastriesModel>{
                override fun onResponse(
                    call: Call<AllPastriesModel>,
                    response: Response<AllPastriesModel>
                ) {
                    if (response.isSuccessful){
                        val data = response.body() as AllPastriesModel
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

                override fun onFailure(call: Call<AllPastriesModel>, t: Throwable) {
                    if (t is SocketTimeoutException)
                        callBackRequest.onError("تایم اوت")
                    else
                        callBackRequest.onError(t.message.toString())
                }
            }
        )
    }


}