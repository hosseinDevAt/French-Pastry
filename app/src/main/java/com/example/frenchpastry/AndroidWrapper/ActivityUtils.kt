package com.example.frenchpastry.AndroidWrapper

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

interface ActivityUtils {

    fun activeNetwork() {}

    fun setFragment(fragment: Fragment) {}

    fun setViewPagerFragment(viewPager2: ViewPager2, data: ArrayList<String>) {}



}