package com.feature.upcoming_matches.mapper

import com.core.domain.model.matches.live.GetOpponentWrapper
import com.feature.upcoming_matches.model.OpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() = OpponentWrapper.Opponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun GetOpponentWrapper.toPresentationModel() =
    OpponentWrapper(opponent = opponent.toPresentationModel())