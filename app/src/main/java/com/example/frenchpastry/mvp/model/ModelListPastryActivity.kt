package com.example.frenchpastry.mvp.model

import com.example.frenchpastry.adapter.recycler.ListPastriesModel
import com.example.frenchpastry.data.remote.ApiRepository.PastryListApiRepository
import com.example.frenchpastry.data.remote.DataModel.AllPastriesModel
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.ui.activity.ListPastryActivity

class ModelListPastryActivity(
    private val id : Int,
    private val type : String
) {

    fun getPastries(
        callBackRequest: CallBackRequest<ListPastriesModel>,
        callBackRequest2: CallBackRequest<AllPastriesModel>
    ){

        if (id != 0)
            PastryListApiRepository.instance.getPastries(id, callBackRequest)
        else
            PastryListApiRepository.instance.getPastryByType(type, callBackRequest2)

    }

    fun getTitle() = when(type){
        ListPastryActivity.NEW -> "تازه ترین ها"
        ListPastryActivity.RATE -> "محبوب ترین ها"
        ListPastryActivity.SPECIAL_OFFER -> "پیشنهاد ویژه"
        else -> ""
    }

}