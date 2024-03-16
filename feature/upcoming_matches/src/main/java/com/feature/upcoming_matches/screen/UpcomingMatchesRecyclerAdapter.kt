package com.feature.upcoming_matches.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.convertDate
import com.core.common.extension.convertTime
import com.core.common.extension.loadImagesWithGlide
import com.feature.upcoming_matches.databinding.ItemUpcomingMatchBinding
import com.feature.upcoming_matches.model.UpcomingMatch

class UpcomingMatchesRecyclerAdapter :
    ListAdapter<UpcomingMatch, UpcomingMatchesRecyclerAdapter.UpcomingMatchViewHolder>(
        DiffUtilCallback
    ) {
    inner class UpcomingMatchViewHolder(private val binding: ItemUpcomingMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingMatch: UpcomingMatch?) {
            upcomingMatch?.let {
                with(binding) {
                    tvName.text = it.name
                    tvDate.text = it.beginAt?.convertDate()
                    tvTime.text = it.beginAt?.convertTime()

                    ivTeamOneLogo.loadImagesWithGlide(it.opponents.firstOrNull()?.opponent?.imageUrl)
                    tvTeamOneName.text = it.opponents.firstOrNull()?.opponent?.name
                    tvTeamOneScore.text = it.results.firstOrNull()?.score.toString()

                    ivTeamTwoLogo.loadImagesWithGlide(it.opponents.lastOrNull()?.opponent?.imageUrl)
                    tvTeamTwoName.text = it.opponents.lastOrNull()?.opponent?.name
                    tvTeamTwoScore.text = it.results.lastOrNull()?.score.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UpcomingMatchViewHolder(
        ItemUpcomingMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UpcomingMatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<UpcomingMatch>() {
        override fun areItemsTheSame(oldItem: UpcomingMatch, newItem: UpcomingMatch): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UpcomingMatch, newItem: UpcomingMatch): Boolean =
            oldItem == newItem
    }
}
