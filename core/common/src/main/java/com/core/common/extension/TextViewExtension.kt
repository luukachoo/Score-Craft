package com.core.common.extension

import android.graphics.Typeface
import android.widget.TextView

fun TextView.bold() = setTypeface(null, Typeface.BOLD)


fun TextView.normal() = setTypeface(null, Typeface.NORMAL)