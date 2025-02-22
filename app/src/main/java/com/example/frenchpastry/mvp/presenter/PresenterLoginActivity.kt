package com.example.frenchpastry.mvp.presenter

import com.example.frenchpastry.mvp.ext.BaseLifecycle
import com.example.frenchpastry.mvp.model.ModelLoginActivity
import com.example.frenchpastry.mvp.view.ViewLoginActivity

class PresenterLoginActivity(
    private val view : ViewLoginActivity,
    private val model : ModelLoginActivity
): BaseLifecycle {

    override fun onCreate() {
        sendCodeVerify()
        sendDeviceInfo()
    }


    private fun sendCodeVerify(){
        view.pressedSendCode(
            model.getDeviceUid(),
            model.getPublicKey()
        )
    }

    private fun sendDeviceInfo(){
        view.setDeviceInfo(model.getDeviceInfo())
    }


}