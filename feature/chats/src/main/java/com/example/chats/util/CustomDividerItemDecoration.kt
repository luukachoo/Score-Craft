package com.example.chats.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val paint: Paint = Paint()
    private val thickness: Int = 1
    private val margin: Int = (context.resources.displayMetrics.density * 30).toInt()

    init {
        paint.color = ContextCompat.getColor(context, android.R.color.darker_gray)
        paint.strokeWidth = context.resources.displayMetrics.density * thickness
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
        }
    }
}