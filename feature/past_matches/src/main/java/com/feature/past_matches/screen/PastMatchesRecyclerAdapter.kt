package com.feature.past_matches.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.R
import com.core.common.extension.bold
import com.core.common.extension.loadImagesWithGlide
import com.core.common.extension.normal
import com.feature.past_matches.databinding.ItemPastMatchBinding
import com.feature.past_matches.model.PastMatch

class PastMatchesRecyclerAdapter :
    ListAdapter<PastMatch, PastMatchesRecyclerAdapter.PastMatchViewHolder>(DiffutilCallback) {

    inner class PastMatchViewHolder(private val binding: ItemPastMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pastMatch: PastMatch) = with(binding) {

            val winnerName = pastMatch.winner?.name

            tvName.text = pastMatch.name
            tvDate.text = pastMatch.beginAt?.dropLast(10)
            tvTime.text = pastMatch.beginAt?.drop(11)?.dropLast(4)
            tvVideoGameTitle.text = pastMatch.videoGame.name

            tvTeamOneName.text = pastMatch.opponents.firstOrNull()?.opponent?.name
            tvTeamOneScore.text = pastMatch.results.firstOrNull()?.score.toString()
            ivTeamOneLogo.loadImagesWithGlide(pastMatch.opponents.firstOrNull()?.opponent?.imageUrl, R.drawable.placeholder)

            tvTeamTwoName.text = pastMatch.opponents.lastOrNull()?.opponent?.name
            tvTeamTwoScore.text = pastMatch.results.lastOrNull()?.score.toString()
            ivTeamTwoLogo.loadImagesWithGlide(pastMatch.opponents.lastOrNull()?.opponent?.imageUrl, R.drawable.placeholder)


            if (tvTeamOneName.text == winnerName) {
                tvTeamOneName.bold()
                tvTeamOneScore.bold()
            } else {
                tvTeamOneName.normal()
                tvTeamOneScore.normal()
            }

            if (tvTeamTwoName.text == winnerName) {
                tvTeamTwoName.bold()
                tvTeamTwoScore.bold()
            } else {
                tvTeamTwoName.normal()
                tvTeamTwoScore.normal()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PastMatchViewHolder(
        ItemPastMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PastMatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffutilCallback : DiffUtil.ItemCallback<PastMatch>() {
        override fun areItemsTheSame(oldItem: PastMatch, newItem: PastMatch): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PastMatch, newItem: PastMatch): Boolean =
            oldItem == newItem

    }
}