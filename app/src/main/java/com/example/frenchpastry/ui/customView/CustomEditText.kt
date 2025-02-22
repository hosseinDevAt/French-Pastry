package com.example.frenchpastry.ui.customView

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.R
import com.example.frenchpastry.databinding.CustomEditTextBinding
import java.util.Locale

class CustomEditText(
    context: Context,
    attr:AttributeSet
): FrameLayout(context, attr) {

    val binding = CustomEditTextBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

        initEditText(attr)
    }

    private fun initEditText(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.CustomEditText)
        val hint = typeArray.getString(R.styleable.CustomEditText_hint)
        val type = typeArray.getInt(R.styleable.CustomEditText_type, 0)
        val rtlSupport = typeArray.getBoolean(R.styleable.CustomEditText_rtlSupport, false)
        val max = typeArray.getInt(R.styleable.CustomEditText_max, 0)
        val centerGravity = typeArray.getBoolean(R.styleable.CustomEditText_centerGravity, false)

        binding.textInputEditText.hint = hint
        binding.textInputEditText.inputType = type
        if (rtlSupport){
            binding.textInputEditText.textDirection = TEXT_DIRECTION_RTL
            binding.textInputEditText.layoutDirection = LAYOUT_DIRECTION_RTL
            binding.textInputEditText.gravity = Gravity.END
        }else{
            binding.textInputEditText.textDirection = TEXT_DIRECTION_LTR
            binding.textInputEditText.layoutDirection = LAYOUT_DIRECTION_LTR
            binding.textInputEditText.gravity = Gravity.START
        }
        if (max > 0)
            binding.textInputEditText.filters = arrayOf(InputFilter.LengthFilter(max))

        if (centerGravity)
            binding.textInputEditText.gravity = Gravity.CENTER

        typeArray.recycle()

    }

    fun getEditText() = binding.textInputEditText

    fun setText(text: String){
        binding.textInputEditText.text = Editable.Factory().newEditable(text)
    }

    fun getText() = binding.textInputEditText.text.toString()

    fun nullError(){
        binding.textInputEditText.error = null
    }

    fun setError(error: String?){
        binding.textInputEditText.error = error
    }

}