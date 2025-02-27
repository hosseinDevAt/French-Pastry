package com.example.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.frenchpastry.AndroidWrapper.PicassoHandler
import com.example.frenchpastry.data.remote.DataModel.PastriesModel
import com.example.frenchpastry.databinding.RecyclerItemMainHorizantalBinding
import com.example.frenchpastry.mvp.ext.OtherUtills
import com.example.frenchpastry.ui.activity.DetailPastryActivity

class NewPastryRecyclerAdapter(
    private val pastries : ArrayList<PastriesModel>,
    private val context: Context
): RecyclerView.Adapter<NewPastryRecyclerAdapter.NewPastryViewHolder>() {

    inner class NewPastryViewHolder(
        private val binding : RecyclerItemMainHorizantalBinding
    ): ViewHolder(binding.root){

        fun setData(data: PastriesModel){

            binding.txtPastryName.text = data.title
            binding.txtMainPrice.text = OtherUtills().changePrice(data.price)

            if (data.has_discount){
                binding.txtMainPrice.paintFlags =
                    binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtMainPrice.setTextColor(Color.GRAY)

                binding.txtOffPrice.text = OtherUtills().changePrice(data.sale_price)
                binding.txtOff.text = data.discount
            }else{
                binding.off.visibility = View.GONE
            }

            if (data.thumbnail.isNotEmpty()){
                PicassoHandler.setImage(binding.imgPastry, data.thumbnail)
            }

            binding.root.setOnClickListener{
                val intent = Intent(context, DetailPastryActivity::class.java)
                intent.putExtra(DetailPastryActivity.ID, data.ID)
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPastryViewHolder {
        return NewPastryViewHolder(
            RecyclerItemMainHorizantalBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: NewPastryViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

}