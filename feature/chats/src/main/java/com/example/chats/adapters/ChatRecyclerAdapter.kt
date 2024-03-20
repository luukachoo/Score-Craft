package com.example.chats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.chats.R
import com.example.chats.databinding.ChatItemLayoutBinding
import com.example.chats.model.Users


class ChatRecyclerAdapter : ListAdapter<Users, ChatRecyclerAdapter.ChatViewHolder>(ChatDiffCallBack()) {

    inner class ChatViewHolder(private val binding: ChatItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Users

        fun bind() = binding.apply {
            model = currentList[adapterPosition]
            tvUserName.text = model.userName
            ivAvatar.loadImagesWithGlide(model.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatViewHolder(
        ChatItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind()
    }
}