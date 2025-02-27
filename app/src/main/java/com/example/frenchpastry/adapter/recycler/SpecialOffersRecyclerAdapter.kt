package com.example.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.frenchpastry.AndroidWrapper.PicassoHandler
import com.example.frenchpastry.data.remote.DataModel.PastriesModel
import com.example.frenchpastry.databinding.RecyclerItemMainVerticalBinding
import com.example.frenchpastry.mvp.ext.OtherUtills
import com.example.frenchpastry.ui.activity.DetailPastryActivity
import com.example.frenchpastry.ui.activity.ListPastryActivity

class SpecialOffersRecyclerAdapter(
    private val pastries : ArrayList<PastriesModel>,
    private val context: Context
): RecyclerView.Adapter<SpecialOffersRecyclerAdapter.SpecialOffersViewHolder>() {

    inner class SpecialOffersViewHolder(
        private val binding : RecyclerItemMainVerticalBinding
    ): ViewHolder(binding.root){


        fun setData(data: PastriesModel, position: Int){

            if (position == 0){
                binding.root.visibility = View.INVISIBLE
            }else {
                binding.root.visibility = View.VISIBLE
                binding.txtPastryName.text = data.title
                binding.txtMainPrice.text = OtherUtills().changePrice(data.price)

                if (data.has_discount){
                    binding.txtMainPrice.paintFlags =
                        binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.txtMainPrice.setTextColor(Color.GRAY)


                    binding.txtOffPrice.text = OtherUtills().changePrice(data.sale_price)
                    binding.txtOff.text = data.discount
                }else{
                    binding.offGroup.visibility = View.GONE
                }

                if (data.thumbnail.isNotEmpty())
                    PicassoHandler.setImage(binding.imgPastry, data.thumbnail)

                if (position == pastries.lastIndex){
                    binding.allViews.visibility = View.INVISIBLE
                    binding.others.visibility = View.VISIBLE

                    binding.root.setOnClickListener {
                        val intent = Intent(context, ListPastryActivity::class.java)
                        intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.SPECIAL_OFFER)
                        context.startActivity(intent)
                    }
                }

                binding.root.setOnClickListener {
                    val intent = Intent(context, DetailPastryActivity::class.java)
                    intent.putExtra(DetailPastryActivity.ID, data.ID)
                    context.startActivity(intent)
                }

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SpecialOffersViewHolder(
            RecyclerItemMainVerticalBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: SpecialOffersViewHolder, position: Int) {
        holder.setData(pastries[position], position)
    }
}