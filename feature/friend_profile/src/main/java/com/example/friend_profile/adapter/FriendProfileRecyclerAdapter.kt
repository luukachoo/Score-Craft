package com.example.friend_profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.friend_profile.databinding.LeagueItemLayoutBinding
import com.example.friend_profile.model.league.League

class FriendProfileRecyclerAdapter() :
    ListAdapter<League, FriendProfileRecyclerAdapter.LeagueViewHolder>(FriendProfileDiffCallBack()) {

    inner class LeagueViewHolder(private val binding: LeagueItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: League

        fun bind() = binding.apply {
            model = currentList[adapterPosition]

            ivAvatar.loadImagesWithGlide(model.imageUrl)
            tvLeagueName.text = model.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LeagueViewHolder(
        LeagueItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind()
    }
}