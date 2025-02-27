package com.example.frenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.frenchpastry.R
import com.example.frenchpastry.databinding.CustomMainRecyclerHorizantalBinding

class CustomRecyclerHorizantal(
    context: Context,
    attributeSet: AttributeSet
): FrameLayout(context, attributeSet) {

    private val binding = CustomMainRecyclerHorizantalBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        initRecyclerTitle(attributeSet)
    }

    private fun initRecyclerTitle(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.CustomRecyclerHorizantal)
        val title = typeArray.getString(R.styleable.CustomRecyclerHorizantal_title)

        binding.txtTitleHorizantal.text = title

        typeArray.recycle()
    }

    fun getRecycler() = binding.recyclerView
    fun getAll() = binding.viewAll

}