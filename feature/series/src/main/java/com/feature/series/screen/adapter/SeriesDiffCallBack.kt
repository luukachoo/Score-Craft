package com.feature.series.screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.feature.series.screen.model.Series

class SeriesDiffCallBack : DiffUtil.ItemCallback<Series>() {
    override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem == newItem
    }
}