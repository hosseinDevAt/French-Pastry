package com.example.frenchpastry.mvp.presenter

import com.example.frenchpastry.mvp.ext.BaseLifecycle
import com.example.frenchpastry.mvp.model.ModelMainActivity
import com.example.frenchpastry.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view : ViewMainActivity,
    private val model : ModelMainActivity
) : BaseLifecycle {


    override fun onCreate() {

        view.initialize()
        initShowNavDrawer()
        initShowBottomNav()

    }

    private fun initShowNavDrawer(){

        view.showNavDrawer()

    }

    private fun initShowBottomNav(){

        view.initBottomNav()

    }

}