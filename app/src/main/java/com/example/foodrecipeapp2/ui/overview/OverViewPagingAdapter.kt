package com.example.foodrecipeapp2.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.databinding.ItemOverviewBinding
import com.example.foodrecipeapp2.model.Recipe

class OverViewPagingAdapter(
    val onMainClick: (Recipe) -> Unit
) : PagingDataAdapter<Recipe,OverViewPagingAdapter.OverviewViewHolder>(
    COMPARATOR) {
    class OverviewViewHolder(private val binding : ItemOverviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem : Recipe,
                onMainClick: (Recipe) -> Unit){
            binding.girdModel = currentItem
            binding.root.setOnClickListener {
                onMainClick(currentItem)
            }
        }
        companion object{
            fun from(parent: ViewGroup): OverviewViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                return OverviewViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
       return OverviewViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it,onMainClick)
        }
    }


    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Recipe>(){
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }

        }
    }


}