package com.feature.series.adapter

import androidx.recyclerview.widget.DiffUtil
import com.feature.series.model.Series

class SeriesDiffCallBack : DiffUtil.ItemCallback<Series>() {
    override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
        return oldItem == newItem
    }
}