package com.core.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.core.common.R

fun ImageView.loadImagesWithGlide(url: String?) {
    val finalUrl = if (url.isNullOrEmpty()) {
        R.drawable.ic_error_page
    } else {
        url
    }
    Glide.with(this)
        .load(finalUrl)
        .into(this)
}