package com.feature.match.screen.past_matches.adapter

import com.core.common.R
import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.bold
import com.core.common.extension.convertDate
import com.core.common.extension.convertTime
import com.core.common.extension.loadImagesWithGlide
import com.core.common.extension.normal
import com.feature.live_matches.databinding.ItemMatchBinding
import com.feature.match.model.match.Match

class PastMatchesRecyclerAdapter :
    BaseRecyclerAdapter<Match, ItemMatchBinding>(
        inflater = { layoutInflater, parent, attachToParent ->
            ItemMatchBinding.inflate(layoutInflater, parent, attachToParent)
        }
    ) {
    override fun onBind(binding: ItemMatchBinding, position: Int) {
        val match = getItem(position)
        binding.apply {
            val winnerName = match.winner?.name

            tvName.text = match.name
            tvDate.text = match.beginAt?.convertDate()
            tvTime.text = match.beginAt?.convertTime()
            tvVideoGameTitle.text = match.videoGame.name

            tvTeamOneName.text = match.opponents.firstOrNull()?.opponent?.name
            tvTeamOneScore.text = match.results.firstOrNull()?.score.toString()
            ivTeamOneLogo.loadImagesWithGlide(
                match.opponents.firstOrNull()?.opponent?.imageUrl,
                R.drawable.placeholder
            )

            tvTeamTwoName.text = match.opponents.lastOrNull()?.opponent?.name
            tvTeamTwoScore.text = match.results.lastOrNull()?.score.toString()
            ivTeamTwoLogo.loadImagesWithGlide(
                match.opponents.lastOrNull()?.opponent?.imageUrl,
                R.drawable.placeholder
            )

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
}