package com.feature.live_match_details.mapper

import com.core.domain.model.live_matches.GetOpponentWrapper
import com.feature.live_match_details.model.OpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() = OpponentWrapper.Opponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun GetOpponentWrapper.toPresentationModel() =
    OpponentWrapper(opponent = opponent.toPresentationModel())