package com.core.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImagesWithGlide(url: String?, placeHolder: Int? = null) {
    if (!url.isNullOrBlank()) {
        Glide.with(this.context)
            .load(url)
            .placeholder(placeHolder ?: 0)
            .into(this)
    } else {
        placeHolder?.let { setImageResource(it) }
    }
}