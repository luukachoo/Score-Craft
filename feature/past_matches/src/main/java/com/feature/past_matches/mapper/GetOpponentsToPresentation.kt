package com.feature.past_matches.mapper

import com.core.domain.model.matches.live.GetOpponentWrapper
import com.feature.past_matches.model.OpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() = OpponentWrapper.Opponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun GetOpponentWrapper.toPresentationModel() =
    OpponentWrapper(opponent = opponent.toPresentationModel())