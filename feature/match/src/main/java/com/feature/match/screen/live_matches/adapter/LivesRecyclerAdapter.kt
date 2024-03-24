package com.feature.match.screen.live_matches.adapter

import com.core.common.R
import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.convertDate
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_matches.databinding.ItemLiveBinding
import com.feature.match.extension.checkForPreview
import com.feature.match.model.match.MatchWrapper

class LivesRecyclerAdapter :
    BaseRecyclerAdapter<MatchWrapper.Match, ItemLiveBinding>(ItemLiveBinding::inflate) {
    private var onClick: ((MatchWrapper.Match) -> Unit)? = null

    override fun onBind(binding: ItemLiveBinding, position: Int) {
        val match = getItem(position)
        binding.apply {
            ivPreview.loadImagesWithGlide(
                match.streamsList.checkForPreview(),
                placeHolder = R.drawable.img_stream_error
            )
            tvLeague.text = match.beginAt.convertDate()
            tvTitle.text = match.name

            if (match.status == "running") {
                lottieLiveNowAnimation.isAnimating
            } else {
                !lottieLiveNowAnimation.isAnimating
            }

            root.setOnClickListener { onClick?.invoke(match) }
        }
    }

    fun onClick(click: (MatchWrapper.Match) -> Unit) {
        this.onClick = click
    }
}