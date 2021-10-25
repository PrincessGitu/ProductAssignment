package com.product_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.product_assignment.R
import com.product_assignment.databinding.RowProductDataBinding
import com.product_assignment.model.ProductDataItem


/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
class ProductAdapter() :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    class ProductViewHolder(private val binding: RowProductDataBinding):RecyclerView.ViewHolder(
        binding.root){
        fun bind(requestData: ProductDataItem){
            binding.data=requestData
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: RowProductDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_product_data, parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    private val differCallback=object:DiffUtil.ItemCallback<ProductDataItem>(){
        override fun areItemsTheSame(oldItem: ProductDataItem, newItem: ProductDataItem): Boolean {
            return oldItem.productName == newItem.productName
        }

        override fun areContentsTheSame(oldItem: ProductDataItem, newItem: ProductDataItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ=AsyncListDiffer(this, differCallback)


    companion object {
        @JvmStatic
        @BindingAdapter("productUrl")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .into(view)
        }
    }
}