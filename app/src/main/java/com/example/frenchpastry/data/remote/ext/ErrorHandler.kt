package com.example.frenchpastry.data.remote.ext

import com.example.frenchpastry.data.remote.DataModel.ErrorResponse
import com.example.frenchpastry.data.remote.MainApi.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

object ErrorHandler {

    fun parseError(response: Response<*>) : String {

        val converter : Converter<ResponseBody, ErrorResponse> =
            RetrofitService.retrofit.responseBodyConverter(
                ErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )

        var error : String? = null

        try {
            response.errorBody()?.let {
                val errorResponse : ErrorResponse? = converter.convert(it)
                error = errorResponse?.message
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return error ?: "ارتباط با سرور امکان پذیر نمیباشد."
    }

}