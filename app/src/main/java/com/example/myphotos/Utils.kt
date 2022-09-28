package com.example.myphotos

import android.widget.ImageView
import com.bumptech.glide.Glide

const val GRID_SIZE = 4

fun addImageIntoView(imageView: ImageView, stringUrl: String) {
    Glide.with(imageView.context)
        .load(stringUrl)
        .into(imageView)
}