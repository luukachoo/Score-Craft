package com.core.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomFloatingButton : FloatingActionButton {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val surfaceColor = ContextCompat.getColor(
            context,
            com.google.android.material.R.color.design_default_color_surface
        )
        backgroundTintList = ColorStateList.valueOf(surfaceColor)

        // Set icon
        setImageResource(R.drawable.ic_back_btn)
    }
}