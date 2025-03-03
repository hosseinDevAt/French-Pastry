package com.example.frenchpastry.mvp.presenter

import android.content.Context
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.NetworkAdapter
import com.example.frenchpastry.adapter.recycler.ListPastriesModel
import com.example.frenchpastry.data.remote.DataModel.AllPastriesModel
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.mvp.ext.BaseLifecycle
import com.example.frenchpastry.mvp.ext.ToastUtils
import com.example.frenchpastry.mvp.model.ModelListPastryActivity
import com.example.frenchpastry.mvp.view.ViewListPastryActivity

class PresenterListPastryActivity(
    private val view : ViewListPastryActivity,
    private val model : ModelListPastryActivity,
    private val context: Context
): BaseLifecycle, ActivityUtils {

    override fun onCreate() {
        showNav()
        getData()
        onBackClick()
    }

    private fun showNav(){
        view.showNavDrawer()
    }

    private fun getData(){

        view.startGetData()

        if (NetworkAdapter.isNetworkState(context, this))
            getPastries()

    }

    override fun activeNetwork() {
        getPastries()
    }

    private fun onBackClick(){
        view.onBack()
    }

    private fun getPastries(){

        model.getPastries(
            object : CallBackRequest<ListPastriesModel>{
                override fun onSuccess(code: Int, data: ListPastriesModel) {
                    view.endGetData()
                    view.setData(data)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProcess()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProcess()
                    ToastUtils.setNetToast(context)
                }
            },
            object : CallBackRequest<AllPastriesModel>{
                override fun onSuccess(code: Int, data: AllPastriesModel) {
                    view.endGetData()
                    view.setData2(data, model.getTitle())
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProcess()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProcess()
                    ToastUtils.setNetToast(context)
                }
            }
        )

    }

}