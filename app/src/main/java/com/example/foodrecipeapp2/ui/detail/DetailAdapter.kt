package com.example.foodrecipeapp2.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.databinding.ItemIngidiantsBinding
import com.example.foodrecipeapp2.model.ExtendedIngredient
import com.example.foodrecipeapp2.util.DiffUtill

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.MyViewHolder>(){

    private var recipeList = emptyList<ExtendedIngredient>()
    private val limit = 5

    class MyViewHolder(private val binding : ItemIngidiantsBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(currentItem: ExtendedIngredient) {
            binding.ideModel = currentItem
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIngidiantsBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipeList.getOrNull(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        if (recipeList.size > limit) {
            return limit
        } else {
            return recipeList.size
        }
    }
    fun setData(recipeResponse: List<ExtendedIngredient>) {
        val diffUtil = DiffUtill(recipeList, recipeResponse)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        recipeList = recipeResponse
        diffResult.dispatchUpdatesTo(this)

    }
}