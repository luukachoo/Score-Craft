package com.example.friend_profile.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.friend_profile.databinding.ItemLeagueLayoutBinding
import com.example.friend_profile.model.league.League

class FriendProfileRecyclerAdapter :
    BaseRecyclerAdapter<League, ItemLeagueLayoutBinding>(ItemLeagueLayoutBinding::inflate) {

    override fun onBind(binding: ItemLeagueLayoutBinding, position: Int) {
        val model = getItem(position)
        binding.apply {
            ivAvatar.loadImagesWithGlide(model.imageUrl)
            tvLeagueName.text = model.name
        }
    }
}