package com.example.friend_request.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.friend_request.model.Users

class FriendRequestDiffCallBack : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem == newItem
    }
}