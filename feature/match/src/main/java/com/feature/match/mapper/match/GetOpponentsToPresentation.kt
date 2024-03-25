package com.feature.match.mapper.match

import com.core.domain.model.match.GetOpponentWrapper
import com.feature.match.model.match.OpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() =
    OpponentWrapper.Opponent(
        id = id,
        imageUrl = imageUrl,
        name = name,
        slug = slug
    )

fun GetOpponentWrapper.toPresentationModel() =
    OpponentWrapper(opponent = opponent.toPresentationModel())