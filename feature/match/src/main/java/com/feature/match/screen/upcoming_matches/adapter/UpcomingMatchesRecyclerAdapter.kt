package com.feature.match.screen.upcoming_matches.adapter

import com.ui.core_ui.R
import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.convertDate
import com.core.common.extension.convertTime
import com.core.common.extension.loadImagesWithGlide
import com.feature.live_matches.databinding.ItemMatchBinding
import com.feature.match.model.match.Match

class UpcomingMatchesRecyclerAdapter :
    BaseRecyclerAdapter<Match, ItemMatchBinding>(ItemMatchBinding::inflate) {
    override fun onBind(binding: ItemMatchBinding, position: Int) {
        val match = getItem(position)
        binding.apply {
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
}