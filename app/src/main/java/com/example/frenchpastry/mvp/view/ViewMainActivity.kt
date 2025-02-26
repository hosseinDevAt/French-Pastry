package com.example.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.databinding.ActivityMainBinding
import com.example.frenchpastry.ui.customView.bottomNav.ActiveFragment
import com.example.frenchpastry.ui.customView.bottomNav.FragmentType
import com.example.frenchpastry.ui.fragment.CakeFragment
import com.example.frenchpastry.ui.fragment.HomeFragment
import com.example.frenchpastry.ui.fragment.PastryFragment
import com.example.frenchpastry.ui.fragment.ProfileFragment

class ViewMainActivity : FrameLayout, ActiveFragment {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityMainBinding.inflate(inflater)


    fun initialize() {
        activityUtils.setFragment(HomeFragment())
    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun initBottomNav() {

        binding.customBottomNav.onClickFragment(this)

    }


    override fun setFragment(type: FragmentType) {
        val fragment = when (type) {

            FragmentType.HOME -> HomeFragment()
            FragmentType.CAKE -> CakeFragment()
            FragmentType.PASTRY -> PastryFragment()
            FragmentType.PROFILE -> ProfileFragment()
        }
        activityUtils.setFragment(fragment)
    }


}