package com.example.frenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.databinding.CustomMainRecyclerVerticalBinding

class CustomRecyclerVertical(
    context: Context,
    attributeSet: AttributeSet
): FrameLayout(context, attributeSet) {

    private val binding = CustomMainRecyclerVerticalBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun getRecycler() = binding.recyclerView

}