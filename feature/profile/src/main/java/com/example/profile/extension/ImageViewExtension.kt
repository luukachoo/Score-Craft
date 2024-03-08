package com.example.profile.extension

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.profile.R

fun ImageView.loadImageWithUri(uri: Uri) {
    Glide.with(this.context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
        )
        .into(this)
}