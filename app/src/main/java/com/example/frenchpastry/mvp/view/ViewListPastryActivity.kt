package com.example.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.adapter.recycler.ListPastriesModel
import com.example.frenchpastry.adapter.recycler.ListPastryRecyclerAdapter
import com.example.frenchpastry.data.remote.DataModel.AllPastriesModel
import com.example.frenchpastry.data.remote.DataModel.PastriesModel
import com.example.frenchpastry.data.remote.DataModel.PastryModel
import com.example.frenchpastry.databinding.ActivityListPastryBinding

class ViewListPastryActivity : FrameLayout{

    private lateinit var activityUtils: ActivityUtils

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        actUtils : ActivityUtils
    ) : super(context){
        activityUtils = actUtils
    }


    val binding = ActivityListPastryBinding.inflate(
        LayoutInflater.from(context)
    )

    fun showNavDrawer(){
        binding.appBar.showNavDrawer(context)
    }

    fun onBack(){
        binding.appBar.getBackImg().setOnClickListener{
         activityUtils.finished()
        }
    }

    fun setData(data: ListPastriesModel){

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ListPastryRecyclerAdapter(data.category.pastries, context)
        binding.recyclerViewPastry.adapter = adapter

        binding.txtTitleListLogo.text = data.category.title

        binding.edtSearch.getEditText().doOnTextChanged{text,_,_,_ ->

            adapter.filter.filter(text)

        }

    }

    fun setData2(data: AllPastriesModel, title: String){

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ListPastryRecyclerAdapter(data.pastries, context)
        binding.recyclerViewPastry.adapter = adapter

        binding.edtSearch.getEditText().doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }
        binding.txtTitleListLogo.text = title

    }

    fun startGetData(){
        binding.progressBar.visibility = View.VISIBLE
        binding.allView.visibility = View.INVISIBLE
    }

    fun endGetData(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.allView.visibility = View.VISIBLE
    }

    fun endProcess(){
        binding.progressBar.visibility = View.INVISIBLE
    }


}