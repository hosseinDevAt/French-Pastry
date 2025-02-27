package com.example.frenchpastry.adapter.recycler.diffUtils

import androidx.recyclerview.widget.DiffUtil

class RecylerDiffUtills(
    private val oldValue : ArrayList<*>,
    private val newValue : ArrayList<*>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldValue.size

    override fun getNewListSize() = newValue.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldValue[oldItemPosition] == newValue[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldValue == newValue

}