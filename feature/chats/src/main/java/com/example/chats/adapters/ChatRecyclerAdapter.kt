package com.example.chats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.chats.databinding.ChatItemLayoutBinding
import com.example.chats.model.Users

class ChatRecyclerAdapter(
    private val onChatClicked: (userId: String) -> Unit,
    private val onRemoveFriendClicked: (userId: String) -> Unit
) : ListAdapter<Users, ChatRecyclerAdapter.ChatViewHolder>(ChatDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ChatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding, onChatClicked)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class ChatViewHolder(
        private val binding: ChatItemLayoutBinding,
        private val onChatClicked: (userId: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: Users) {
            binding.apply {
                tvUserName.text = user.userName
                ivAvatar.loadImagesWithGlide(user.avatar)
                root.setOnClickListener {
                    onChatClicked(user.userId)
                }

                btnRemoveFriend.setOnClickListener {
                    onRemoveFriendClicked(user.userId)
                }
            }
        }
    }
}