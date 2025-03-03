package com.example.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.frenchpastry.AndroidWrapper.PicassoHandler
import com.example.frenchpastry.adapter.recycler.diffUtils.RecylerDiffUtills
import com.example.frenchpastry.data.remote.DataModel.PastriesModel
import com.example.frenchpastry.data.remote.DataModel.PastryModel
import com.example.frenchpastry.databinding.RecyclerListItemProductBinding
import com.example.frenchpastry.mvp.ext.OtherUtills
import com.example.frenchpastry.ui.activity.DetailPastryActivity

class ListPastryRecyclerAdapter(
    private val pastries: ArrayList<PastryModel>,
    private val context: Context
) : RecyclerView.Adapter<ListPastryRecyclerAdapter.ListPastryViewHolder>(), Filterable {

    private val dataFull = ArrayList<PastryModel>()
    private val dataMain = ArrayList<PastryModel>()

    init {
        dataFull.addAll(pastries)
        dataMain.addAll(pastries)
    }

    inner class ListPastryViewHolder(
        private val binding: RecyclerListItemProductBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: PastryModel) {

            binding.txtTitleList.text = data.title
            binding.txtMainPriceList.text = OtherUtills().changePrice(data.price)

            if (data.thumbnail.isNotEmpty()){
                PicassoHandler.setImage(binding.imgProduct, data.thumbnail)
            }

            if (data.has_discount){
                binding.txtMainPriceList.paintFlags =
                    binding.txtMainPriceList.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtMainPriceList.setTextColor(Color.GRAY)

                binding.txtOffPriceList.text = OtherUtills().changePrice(data.sale_price)
                binding.txtOffList.text = data.discount
            }else{
                binding.offList.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, DetailPastryActivity::class.java)
                intent.putExtra(DetailPastryActivity.ID, data.ID)
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPastryViewHolder {
        return ListPastryViewHolder(
            RecyclerListItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: ListPastryViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    override fun getFilter(): Filter =
        object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val data = ArrayList<PastryModel>()

                if (constraint.isNullOrEmpty()){
                    data.addAll(dataFull)
                }else{
                    val filter = constraint.toString().trim()
                    for (item in dataFull){
                        if (item.title.contains(filter))
                            data.add(item)
                    }
                }

                pastries.clear()
                pastries.addAll(data)

                return FilterResults()
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataUpdate(pastries)
            }
        }

    private fun dataUpdate(pastries: ArrayList<PastryModel>) {
        val diffCallBack = RecylerDiffUtills(dataMain, pastries)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)

        dataMain.clear()
        dataMain.addAll(pastries)

        diffResult.dispatchUpdatesTo(this)
    }
}