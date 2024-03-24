package com.example.friend_profile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.friend_profile.model.league.League

class FriendProfileDiffCallBack : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
        return oldItem == newItem
    }
}