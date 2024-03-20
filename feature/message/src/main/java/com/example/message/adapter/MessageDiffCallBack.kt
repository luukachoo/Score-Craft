package com.example.message.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.message.model.Message
import com.example.message.model.Users

class MessageDiffCallBack : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}