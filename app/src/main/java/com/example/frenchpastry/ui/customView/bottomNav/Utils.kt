package com.example.frenchpastry.ui.customView.bottomNav

enum class FragmentType(){
    HOME,CAKE,PASTRY,PROFILE
}

interface ActiveFragment {

    fun setFragment(type: FragmentType)

}
