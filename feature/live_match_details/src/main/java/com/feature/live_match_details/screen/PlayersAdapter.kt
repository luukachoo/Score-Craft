package com.feature.live_match_details.screen

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feature.live_match_details.databinding.ItemPlayerBinding
import com.feature.live_match_details.model.TeamWrapper

class PlayersAdapter : ListAdapter<TeamWrapper.Team.Player, PlayersAdapter.PlayerViewHolder>(DiffUtilCallback) {

    inner class PlayerViewHolder(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: TeamWrapper.Team.Player) = with(binding) {
            tvPlayerFirstNationality.text = player.nationality
            tvPlayerFirstUsername.text = player.name
        }
    }


    private object DiffUtilCallback : DiffUtil.ItemCallback<TeamWrapper.Team.Player>() {
        override fun areItemsTheSame(
            oldItem: TeamWrapper.Team.Player,
            newItem: TeamWrapper.Team.Player
        ): Boolean  = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TeamWrapper.Team.Player,
            newItem: TeamWrapper.Team.Player
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}