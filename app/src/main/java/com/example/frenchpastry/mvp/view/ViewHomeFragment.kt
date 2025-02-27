package com.example.frenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.PicassoHandler
import com.example.frenchpastry.adapter.recycler.NewPastryRecyclerAdapter
import com.example.frenchpastry.adapter.recycler.SpecialOffersRecyclerAdapter
import com.example.frenchpastry.adapter.recycler.TopRecyclerAdapter
import com.example.frenchpastry.data.remote.DataModel.PastriesModel
import com.example.frenchpastry.data.remote.DataModel.RequestMain
import com.example.frenchpastry.databinding.HomeFragmentBinding
import com.example.frenchpastry.ui.activity.ListPastryActivity
import com.example.frenchpastry.ui.fragment.HomeFragment

class ViewHomeFragment : FrameLayout {

    private lateinit var activityUtils: ActivityUtils

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        actUtil : ActivityUtils
    ) : super(context){
        activityUtils = actUtil
    }

    val binding = HomeFragmentBinding.inflate(LayoutInflater.from(context))

    fun startGetData(){
        binding.progressBar.visibility = VISIBLE
        binding.content.visibility = INVISIBLE
    }

    fun endGetData(){
        binding.progressBar.visibility = INVISIBLE
        binding.content.visibility = VISIBLE
    }

    fun endProcess(){
        binding.progressBar.visibility = INVISIBLE
    }

    fun initialize(data: RequestMain){

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL
        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        binding.newPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.newPastryRecycler.getRecycler().adapter =
            NewPastryRecyclerAdapter(data.pastries[0].pastries, context)

        binding.specialOffersRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        val specialOffersData = data.pastries[1].pastries
        specialOffersData.add(
            0,
            PastriesModel(
                0,
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )


        binding.specialOffersRecycler.getRecycler().adapter =
            SpecialOffersRecyclerAdapter(specialOffersData, context)

        binding.topPastryRecycler.getRecycler().layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.topPastryRecycler.getRecycler().adapter =
            TopRecyclerAdapter(data.pastries[2].pastries, context)

        if (data.banners.isNotEmpty() && data.banners[0].large.isNotEmpty()){
            PicassoHandler.setBanner(binding.imgBanner, data.banners[0].large)
        }

        binding.newPastryRecycler.getRecycler().setOnClickListener {
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.NEW)
            context.startActivity(intent)
        }

        binding.topPastryRecycler.getRecycler().setOnClickListener{
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.RATE)
            context.startActivity(intent)
        }

    }

}