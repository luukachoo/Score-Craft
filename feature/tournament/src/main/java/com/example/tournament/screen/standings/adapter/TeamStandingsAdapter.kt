package com.example.tournament.screen.standings.adapter

import android.annotation.SuppressLint
import com.core.common.R
import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.tournament.model.TeamStanding
import com.feature.tournament_details.databinding.ItemTeamBinding

class TeamStandingsAdapter :
    BaseRecyclerAdapter<TeamStanding, ItemTeamBinding>(ItemTeamBinding::inflate) {
    @SuppressLint("SetTextI18n")
    override fun onBind(binding: ItemTeamBinding, position: Int) {
        val item = getItem(position)
        binding.apply {
            if (item.team.location.isEmpty()) {
                tvLocation.text = "N/A"
            } else {
                tvLocation.text = item.team.location
            }
            tvTeamName.text = item.team.name
            ivTeamLogo.loadImagesWithGlide(item.team.imageUrl, R.drawable.ic_controller)
            tvPosition.text = "${item.rank}."
        }
    }
}