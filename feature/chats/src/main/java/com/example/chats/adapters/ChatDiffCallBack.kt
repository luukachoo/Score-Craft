package com.example.chats.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.chats.model.Users

class ChatDiffCallBack : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem == newItem
    }
}