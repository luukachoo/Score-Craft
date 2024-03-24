package com.example.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.profile.databinding.LeagueItemLayoutBinding
import com.example.profile.model.league.League

class ProfileRecyclerAdapter(private val onRemoveFavouriteClick: (league: League) -> Unit) :
    ListAdapter<League, ProfileRecyclerAdapter.LeagueViewHolder>(ProfileDiffCallBack()) {

    inner class LeagueViewHolder(private val binding: LeagueItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: League

        fun bind() = binding.apply {
            model = currentList[adapterPosition]

            ivAvatar.loadImagesWithGlide(model.imageUrl)
            tvLeagueName.text = model.name

            btnRemoveFavourite.setOnClickListener {
                onRemoveFavouriteClick(model)
            }
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