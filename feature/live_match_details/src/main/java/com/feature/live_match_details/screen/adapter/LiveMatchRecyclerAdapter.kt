package com.feature.live_match_details.screen.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_match_details.R
import com.feature.live_match_details.databinding.ItemPlayerBinding
import com.feature.live_match_details.model.Players

class LiveMatchRecyclerAdapter :
    BaseRecyclerAdapter<Players, ItemPlayerBinding>(ItemPlayerBinding::inflate) {
    override fun onBind(binding: ItemPlayerBinding, position: Int) {
        val players = getItem(position)
        binding.apply {
            ivPlayerFirst.loadImagesWithGlide(
                players.firstTeamPlayer?.imageUrl,
                R.drawable.guest_player
            )
            ivPlayerSecond.loadImagesWithGlide(
                players.secondTeamPlayer?.imageUrl,
                R.drawable.guest_player
            )

            tvPlayerFirstUsername.text = players.firstTeamPlayer?.name ?: "No name"
            tvPlayerFirstNationality.text = players.firstTeamPlayer?.nationality ?: "N/A"

            tvPlayerSecondUsername.text = players.secondTeamPlayer?.name ?: "No name"
            tvPlayerSecondNationality.text = players.secondTeamPlayer?.nationality ?: "N/A"
        }
    }
}