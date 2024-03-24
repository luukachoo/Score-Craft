package com.example.chats.adapters

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.chats.databinding.ChatItemLayoutBinding
import com.example.chats.model.Users

class ChatRecyclerAdapter(private val onChatClicked: (userId: String) -> Unit) :
    BaseRecyclerAdapter<Users, ChatItemLayoutBinding>(
        inflater = { layoutInflater, parent, attachToParent ->
            ChatItemLayoutBinding.inflate(layoutInflater, parent, attachToParent)
        }
    ) {
    override fun onBind(binding: ChatItemLayoutBinding, position: Int) {
        val user = getItem(position)
        binding.apply {
            tvUserName.text = user.userName
            ivAvatar.loadImagesWithGlide(user.avatar)
            root.setOnClickListener {
                onChatClicked(user.userId)
            }
        }
    }
}
