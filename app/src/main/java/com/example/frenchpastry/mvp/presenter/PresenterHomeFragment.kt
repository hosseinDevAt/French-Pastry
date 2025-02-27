package com.example.frenchpastry.mvp.presenter

import android.content.Context
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.NetworkAdapter
import com.example.frenchpastry.data.remote.DataModel.RequestMain
import com.example.frenchpastry.data.remote.ext.CallBackRequest
import com.example.frenchpastry.mvp.ext.BaseLifecycle
import com.example.frenchpastry.mvp.ext.ToastUtils
import com.example.frenchpastry.mvp.model.ModelHomeFragment
import com.example.frenchpastry.mvp.view.ViewHomeFragment

class PresenterHomeFragment(
    private val view: ViewHomeFragment,
    private val model: ModelHomeFragment,
    private val context: Context
) : BaseLifecycle, ActivityUtils {

    override fun onCreate() {
        createSlider()
    }

    private fun createSlider() {

        view.startGetData()

        if (NetworkAdapter.isNetworkState(context, this)) {
            sendRequest()
        }
    }

    override fun activeNetwork() {
        sendRequest()
    }

    private fun sendRequest() {

        model.getContent(
            object : CallBackRequest<RequestMain> {
                override fun onSuccess(code: Int, data: RequestMain) {
                    view.endGetData()
                    view.initialize(data)
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