package com.example.foodrecipeapp2.ui.listingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.databinding.ItemIndilistBinding
import com.example.foodrecipeapp2.model.ExtendedIngredient
import com.example.foodrecipeapp2.util.DiffUtill

class ListIngredientsAdapter: RecyclerView.Adapter<ListIngredientsAdapter.MyViewHolder>() {
    private var recipeList = emptyList<ExtendedIngredient>()
    class MyViewHolder(private val binding: ItemIndilistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: ExtendedIngredient){
            binding.indiList = currentItem
        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIndilistBinding.inflate(layoutInflater, parent, false)
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
        return recipeList.size
    }

    fun setData(recipeResponse: List<ExtendedIngredient>) {
        val diffUtil = DiffUtill(recipeList, recipeResponse)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        recipeList = recipeResponse
        diffResult.dispatchUpdatesTo(this)

    }
}