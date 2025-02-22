package com.example.frenchpastry.data.remote.DataModel

data class RequestSendPhone(
    val success: Int,
    val message: String,
    val seconds: Int,
    val expire_at: String
)

data class RequestVerifyCode(
    val message: String,
    var api: String
)
