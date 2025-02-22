package com.example.frenchpastry.mvp.model

import android.content.Context
import com.example.frenchpastry.AndroidWrapper.DeviceInfo

class ModelLoginActivity(private val context: Context) {

    fun getDeviceInfo() = DeviceInfo()
    fun getDeviceUid() = DeviceInfo.getDeviceID(context)
    fun getPublicKey() = DeviceInfo.publicKeyWithoutApi(context)

}