package com.feature.live_matches.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_matches.databinding.ItemLiveBinding
import com.feature.live_matches.extension.checkPreview
import com.feature.live_matches.test_model.MatchListItem

class LivesRecyclerAdapter :
    ListAdapter<MatchListItem.Match, LivesRecyclerAdapter.LivesViewHolder>(LivesDiffCallback) {

    inner class LivesViewHolder(private val binding: ItemLiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchListItem.Match) = with(binding) {
            ivPreview.loadImagesWithGlide(match.streamsList.checkPreview())
            tvLeague.text = match.status
            tvTitle.text = match.beginAt.dropLast(10)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LivesViewHolder(
        ItemLiveBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LivesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object LivesDiffCallback : DiffUtil.ItemCallback<MatchListItem.Match>() {
        override fun areItemsTheSame(oldItem: MatchListItem.Match, newItem: MatchListItem.Match): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MatchListItem.Match, newItem: MatchListItem.Match): Boolean =
            oldItem == newItem

    }
}