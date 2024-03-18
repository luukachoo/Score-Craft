package com.feature.match.screen.upcoming_matches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.R
import com.core.common.extension.convertDate
import com.core.common.extension.convertTime
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_matches.databinding.ItemMatchBinding
import com.feature.match.model.match.Match

class UpcomingMatchesRecyclerAdapter :
    ListAdapter<Match, UpcomingMatchesRecyclerAdapter.UpcomingMatchViewHolder>(
        DiffUtilCallback
    ) {
    inner class UpcomingMatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match?) {
            match?.let {
                with(binding) {
                    tvName.text = it.name
                    tvDate.text = it.beginAt?.convertDate()
                    tvTime.text = it.beginAt?.convertTime()
                    tvVideoGameTitle.text = it.videoGame.name

                    ivTeamOneLogo.loadImagesWithGlide(
                        it.opponents.firstOrNull()?.opponent?.imageUrl,
                        R.drawable.placeholder
                    )
                    tvTeamOneName.text = it.opponents.firstOrNull()?.opponent?.name
                    tvTeamOneScore.text = it.results.firstOrNull()?.score.toString()

                    ivTeamTwoLogo.loadImagesWithGlide(
                        it.opponents.lastOrNull()?.opponent?.imageUrl,
                        R.drawable.placeholder
                    )
                    tvTeamTwoName.text = it.opponents.lastOrNull()?.opponent?.name
                    tvTeamTwoScore.text = it.results.lastOrNull()?.score.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UpcomingMatchViewHolder(
        ItemMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UpcomingMatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean =
            oldItem == newItem
    }
}
