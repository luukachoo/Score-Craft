package com.feature.live_matches.screen

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_matches.R
import com.feature.live_matches.databinding.ItemLiveBinding
import com.feature.live_matches.extension.checkForPreview
import com.feature.live_matches.model.MatchWrapper

class LivesRecyclerAdapter :
    ListAdapter<MatchWrapper.Match, LivesRecyclerAdapter.LivesViewHolder>(LivesDiffCallback) {

    inner class LivesViewHolder(private val binding: ItemLiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchWrapper.Match) = with(binding) {
            ivPreview.loadImagesWithGlide(
                match.streamsList.checkForPreview(),
                placeHolder = R.drawable.img_stream_error
            )
            tvLeague.text = match.beginAt
            tvTitle.text = match.name


            d("TestAdapter", match.numberOfGames.toString())


//            d("TestAdapter", match.beginAt)
            d("TestAdapter", "${match.streamsList.checkForPreview()}")
            d("TestAdapter", "${match.id}")
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

    private object LivesDiffCallback : DiffUtil.ItemCallback<MatchWrapper.Match>() {
        override fun areItemsTheSame(
            oldItem: MatchWrapper.Match,
            newItem: MatchWrapper.Match
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MatchWrapper.Match,
            newItem: MatchWrapper.Match
        ): Boolean =
            oldItem == newItem

    }
}