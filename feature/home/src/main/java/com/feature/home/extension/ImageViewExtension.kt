package com.feature.home.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImagesWithGlide(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}