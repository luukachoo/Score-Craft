package com.example.friend_request.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.friend_request.databinding.FriendItemLayoutBinding
import com.example.friend_request.model.Users

class FriendRequestRecyclerAdapter(
    private val onAccept: (Users) -> Unit,
    private val onReject: (Users) -> Unit
) : ListAdapter<Users, FriendRequestRecyclerAdapter.FriendRequestViewHolder>(
    FriendRequestDiffCallBack()
) {

    inner class FriendRequestViewHolder(private val binding: FriendItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Users

        fun bind() = binding.apply {
            model = currentList[adapterPosition]
            tvUserName.text = model.userName
            ivAvatar.loadImagesWithGlide(model.avatar)

            btnAccept.setOnClickListener { onAccept(model) }
            btnReject.setOnClickListener { onReject(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendRequestViewHolder(
        FriendItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
        holder.bind()
    }
}