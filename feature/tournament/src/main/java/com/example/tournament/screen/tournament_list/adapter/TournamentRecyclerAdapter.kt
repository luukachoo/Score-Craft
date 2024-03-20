package com.example.tournament.screen.tournament_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.convertDate
import com.example.tournament.model.Tournament
import com.feature.tournament_details.databinding.ItemTournamentBinding

class TournamentRecyclerAdapter :
    ListAdapter<Tournament, TournamentRecyclerAdapter.TournamentViewHolder>(DiffUtilCallback) {

    private var onClick: ((Tournament) -> Unit)? = null

    inner class TournamentViewHolder(private val binding: ItemTournamentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tournament: Tournament) = binding.apply {
            tvName.text = tournament.name
            tvBeginAt.text = tournament.beginAt.convertDate()
            tvPrizePoolAmount.text = tournament.prizePool
            root.setOnClickListener { onClick?.invoke(tournament) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TournamentViewHolder(
        ItemTournamentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onClick(click: (Tournament) -> Unit) {
        this.onClick = click
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Tournament>() {
        override fun areItemsTheSame(oldItem: Tournament, newItem: Tournament): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Tournament, newItem: Tournament): Boolean =
            oldItem == newItem
    }
}