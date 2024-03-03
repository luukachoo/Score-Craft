package com.feature.home.recycler_adapters.category

import androidx.recyclerview.widget.DiffUtil
import com.feature.home.model.League

class CategoryDiffCallback : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean =
        oldItem == newItem
}