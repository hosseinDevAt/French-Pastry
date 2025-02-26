package com.example.frenchpastry.data.remote.ext

interface StartSetUserInfo {

    fun startSetUser(
        name: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int
    )

}