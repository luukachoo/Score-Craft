package com.example.profile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.profile.model.league.League

class ProfileDiffCallBack : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem == newItem
    }
}