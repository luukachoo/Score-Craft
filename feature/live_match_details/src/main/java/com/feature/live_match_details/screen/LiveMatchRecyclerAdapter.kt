package com.feature.live_match_details.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_match_details.R
import com.feature.live_match_details.databinding.ItemPlayerBinding
import com.feature.live_match_details.model.Players

class LiveMatchRecyclerAdapter :
    ListAdapter<Players, LiveMatchRecyclerAdapter.PlayersViewHolder>(PlayersDiffCallback) {

    inner class PlayersViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(players: Players) = with(binding) {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayersViewHolder(
        ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object PlayersDiffCallback : DiffUtil.ItemCallback<Players>() {
        override fun areItemsTheSame(oldItem: Players, newItem: Players): Boolean =
            oldItem.firstTeamPlayer?.id == newItem.firstTeamPlayer?.id

        override fun areContentsTheSame(oldItem: Players, newItem: Players): Boolean =
            oldItem == newItem

    }
}