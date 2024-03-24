package com.example.tournament.screen.test.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.example.tournament.databinding.ItemTournamentMatchBinding
import com.example.tournament.model.TournamentMatch

class TournamentMatchesAdapter :
    BaseRecyclerAdapter<TournamentMatch, ItemTournamentMatchBinding>(ItemTournamentMatchBinding::inflate) {
    override fun onBind(binding: ItemTournamentMatchBinding, position: Int) {
        val item = getItem(position)
        binding.apply {
            tvName.text = item.name

            tvTeamOneScore.text = item.results.first().score.toString()
            tvTeamOneName.text = item.opponents.first().opponent.name
            ivTeamOneLogo.loadImagesWithGlide(item.opponents.first().opponent.imageUrl)


            tvTeamTwoScore.text = item.results.last().score.toString()
            tvTeamTwoName.text = item.opponents.last().opponent.name
            ivTeamTwoLogo.loadImagesWithGlide(item.opponents.last().opponent.imageUrl)
        }
    }
}