package com.example.frenchpastry.ui.customView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.R
import com.example.frenchpastry.databinding.CustomButtonBinding

class CustomButtonImportant(contextInstance: Context, attr: AttributeSet): FrameLayout(contextInstance, attr) {

    val binding = CustomButtonBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        initButton(attr)

    }

    private fun initButton(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.CustomButtonImportant)
        val text = typeArray.getString(R.styleable.CustomButtonImportant_text)
        val buttonWhite = typeArray.getBoolean(R.styleable.CustomButtonImportant_buttonWhite, false)

        binding.customBtn.text = text

        if (buttonWhite){
            binding.customBtn.setBackgroundResource(R.drawable.back_button_white)
            binding.customBtn.setTextColor(Color.parseColor("#101219"))
        }

        typeArray.recycle()

    }

    fun getBtn() = binding.customBtn

    fun enableProgress(){
        binding.loadingAnim.visibility = VISIBLE
        binding.customBtn.visibility = INVISIBLE
    }

    fun disableProgress(){
        binding.customBtn.visibility = VISIBLE
        binding.loadingAnim.visibility = INVISIBLE
    }


}