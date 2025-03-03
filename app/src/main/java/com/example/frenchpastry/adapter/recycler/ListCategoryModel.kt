package com.example.frenchpastry.adapter.recycler

import com.example.frenchpastry.data.remote.DataModel.PastryModel

data class ListPastriesModel(
    val message: String,
    val category: CategoryModel
)

data class CategoryModel(
    val ID: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int,
    val pastries: ArrayList<PastryModel>
)