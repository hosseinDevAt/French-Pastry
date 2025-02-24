package com.example.frenchpastry.ui.customView.bottomNav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.databinding.CustomBottomNavBinding

class CustomBottomNav(
    context: Context,
    attr: AttributeSet
) : FrameLayout(context, attr) {

    private var binding = CustomBottomNavBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        binding.txtShoppingCount.text = "0"

    }

    fun onClickFragment(activeFragment: ActiveFragment){

        binding.homes.setOnClickListener {
            activeHome()
            activeFragment.setFragment(FragmentType.HOME)
        }

        binding.cake.setOnClickListener {
            activeCake()
            activeFragment.setFragment(FragmentType.CAKE)
        }

        binding.pastry.setOnClickListener {
            activePastry()
            activeFragment.setFragment(FragmentType.PASTRY)
        }

        binding.profile.setOnClickListener {
            activeProfile()
            activeFragment.setFragment(FragmentType.PROFILE)
        }

    }

    private fun activeHome(){

        binding.indicatorHome.visibility = VISIBLE
        binding.indicatorCake.visibility = GONE
        binding.indicatorPastry.visibility = GONE
        binding.indicatorUser.visibility = GONE

    }

    private fun activeCake(){

        binding.indicatorCake.visibility = VISIBLE
        binding.indicatorHome.visibility = GONE
        binding.indicatorPastry.visibility = GONE
        binding.indicatorUser.visibility = GONE

    }

    private fun activePastry(){

        binding.indicatorPastry.visibility = VISIBLE
        binding.indicatorHome.visibility = GONE
        binding.indicatorCake.visibility = GONE
        binding.indicatorUser.visibility = GONE

    }

    private fun activeProfile(){

        binding.indicatorUser.visibility = VISIBLE
        binding.indicatorHome.visibility = GONE
        binding.indicatorCake.visibility = GONE
        binding.indicatorPastry.visibility = GONE

    }

}