package com.example.foodrecipeapp2.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.model.NutrientX


@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView?, url: String?){
    imageView?.load(url){
        crossfade(true)
        placeholder(R.drawable.paceholder)
        error(R.drawable.image_not_found)
    }
}
//lateinit var na : String
//@BindingAdapter("app:temp")
//fun temp(nutrients: NutrientX): String {
//
//
//    if (nutrients.name  == "Carbohydrates" )
//    {
//        nutrients.let {
//
//            var na = it.amount.toString()
//
//
//        }
//
//    }
//
//    return na
//
//
//}