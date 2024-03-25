package com.example.profile.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.profile.databinding.LeagueItemLayoutBinding
import com.example.profile.model.league.League

class ProfileRecyclerAdapter(private val onRemoveFavouriteClick: (league: League) -> Unit) :
    BaseRecyclerAdapter<League, LeagueItemLayoutBinding>(LeagueItemLayoutBinding::inflate) {

    override fun onBind(binding: LeagueItemLayoutBinding, position: Int) {
        val model = getItem(position)
        binding.apply {
            ivAvatar.loadImagesWithGlide(model.imageUrl)
            tvLeagueName.text = model.name

            btnRemoveFavourite.setOnClickListener {
                onRemoveFavouriteClick(model)
            }
        }
    }
}