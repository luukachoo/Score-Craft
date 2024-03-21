package com.example.friend_request.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.friend_request.databinding.FriendItemLayoutBinding
import com.example.friend_request.model.Users

class FriendRequestRecyclerAdapter(
    private val onAccept: (Users) -> Unit,
    private val onReject: (Users) -> Unit
) : BaseRecyclerAdapter<Users, FriendItemLayoutBinding>(
    inflater = { layoutInflater, parent, attachToParent ->
        FriendItemLayoutBinding.inflate(layoutInflater, parent, attachToParent)
    }
) {
    override fun onBind(binding: FriendItemLayoutBinding, position: Int) {
        val model = getItem(position)
        binding.apply {
            tvUserName.text = model.userName
            ivAvatar.loadImagesWithGlide(model.avatar)

            btnAccept.setOnClickListener { onAccept(model) }
            btnReject.setOnClickListener { onReject(model) }
        }
    }
}