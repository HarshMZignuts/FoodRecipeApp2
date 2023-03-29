package com.example.foodrecipeapp2.util

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse : Parcelable {
    @SerializedName("MESSAGE")
    val message: String? = null
    @SerializedName("STATUS")
    val status: String? = null
}