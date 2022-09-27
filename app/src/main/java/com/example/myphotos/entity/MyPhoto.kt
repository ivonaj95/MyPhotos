package com.example.myphotos.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyPhoto(
    @SerializedName("id") var id: Int,
    @SerializedName("author") var author: String,
    @SerializedName("width") var width: Int,
    @SerializedName("height") var height: Int,
    @SerializedName("url") var url: String,
    @SerializedName("download_url") var downloadUrl: String
) : Parcelable