package com.example.frenchpastry.ui.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.R
import com.example.frenchpastry.databinding.CustomAppBarBinding
import com.example.frenchpastry.ui.activity.NavDrawerActivity

class CustomAppBar(
    context: Context,
    attr:AttributeSet
): FrameLayout(context, attr) {

    private val binding = CustomAppBarBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

        initialAppBar(attr)

    }

    private fun initialAppBar(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.CustomAppBar)
        val isBack = typeArray.getBoolean(R.styleable.CustomAppBar_isBack, false)

        if (isBack)
            binding.imgBack.visibility = VISIBLE

        typeArray.recycle()

    }

    fun getBackImg() = binding.imgBack

    fun showCount(){
        binding.groupAppBar.visibility = VISIBLE
    }

    fun hideCount(){
        binding.groupAppBar.visibility = INVISIBLE
    }

    fun setCount(count : String){
        binding.txtAlertCount.text = count
    }

    fun showNavDrawer(context: Context){
        binding.icMenu.setOnClickListener {
            context.startActivity(Intent(context, NavDrawerActivity::class.java))
        }
    }


}