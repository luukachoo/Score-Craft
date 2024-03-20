package com.core.common.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.visible() = View.VISIBLE

fun View.gone() = View.GONE