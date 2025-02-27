package com.example.frenchpastry.mvp.model

import com.example.frenchpastry.data.remote.ApiRepository.MainApiRepository
import com.example.frenchpastry.data.remote.DataModel.RequestMain
import com.example.frenchpastry.data.remote.ext.CallBackRequest

class ModelHomeFragment {

    fun getContent(callBackRequest: CallBackRequest<RequestMain>){
        MainApiRepository.instance.getMainContent(callBackRequest)
    }

}