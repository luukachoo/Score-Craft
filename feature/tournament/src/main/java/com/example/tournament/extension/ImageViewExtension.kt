package com.example.tournament.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImagesWithGlide(drawable: Int) {
    Glide.with(this)
        .load(drawable)
        .into(this)
}