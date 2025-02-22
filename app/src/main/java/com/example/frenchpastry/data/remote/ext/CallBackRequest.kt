package com.example.frenchpastry.data.remote.ext

interface CallBackRequest<T> {

    fun onSuccess(code: Int, data: T)

    fun onNotSuccess(code: Int, error: String)

    fun onError(error: String)

}