package com.feature.live_match_details.screen


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_match_details.R
import com.feature.live_match_details.databinding.ItemPlayersBinding
import com.feature.live_match_details.model.Players

class MatchPlayersRecyclerAdapter :
    ListAdapter<Players, MatchPlayersRecyclerAdapter.PlayersViewHolder>(PlayersDiffCallback) {

    inner class PlayersViewHolder(private val binding: ItemPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(players: Players) = with(binding) {
            ivPlayerFirst.loadImagesWithGlide(
                players.firstTeamPlayer.imageUrl,
                R.drawable.guest_player
            )
            ivPlayerSecond.loadImagesWithGlide(
                players.secondTeamPlayer.imageUrl,
                R.drawable.guest_player
            )

            tvPlayerFirstUsername.text = players.firstTeamPlayer.name
            tvPlayerFirstNationality.text = players.firstTeamPlayer.nationality

            tvPlayerFirstUsername.text = players.secondTeamPlayer.name
            tvPlayerSecondNationality.text = players.secondTeamPlayer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayersViewHolder(
        ItemPlayersBinding.inflate(
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
            oldItem.firstTeamPlayer.id == newItem.secondTeamPlayer.id

        override fun areContentsTheSame(oldItem: Players, newItem: Players): Boolean =
            oldItem == newItem

    }
}