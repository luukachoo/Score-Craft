package com.example.tournament.screen.standings.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.tournament.model.TeamStanding
import com.feature.tournament_details.databinding.ItemTeamBinding

class TeamStandingsAdapter : BaseRecyclerAdapter<TeamStanding, ItemTeamBinding>(
    inflater = { layoutInflater, parent, attachToParent ->
        ItemTeamBinding.inflate(layoutInflater, parent, attachToParent)
    }
) {
    override fun onBind(binding: ItemTeamBinding, position: Int) {
        val item = getItem(position)
        binding.apply {
            tvLocation.text = item.team.location
            tvTeamName.text = item.team.name
            ivTeamLogo.loadImagesWithGlide(item.team.imageUrl)
            tvPosition.text = item.rank.toString()
        }
    }
}