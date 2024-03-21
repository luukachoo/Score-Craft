package com.example.tournament.screen.tournament_list.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.convertDate
import com.example.tournament.model.Tournament
import com.feature.tournament_details.databinding.ItemTournamentBinding

class TournamentRecyclerAdapter :
    BaseRecyclerAdapter<Tournament, ItemTournamentBinding>(ItemTournamentBinding::inflate) {
    private var onClick: ((Tournament) -> Unit)? = null

    override fun onBind(binding: ItemTournamentBinding, position: Int) {
        val tournament = getItem(position)
        binding.apply {
            tvName.text = tournament.name
            tvBeginAt.text = tournament.beginAt.convertDate()
            tvPrizePoolAmount.text = tournament.prizePool
            root.setOnClickListener { onClick?.invoke(tournament) }
        }
    }

    fun onClick(click: (Tournament) -> Unit) {
        this.onClick = click
    }
}