package com.feature.match.mapper.match

import com.core.domain.model.match.GetOpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() =
    com.feature.match.model.match.OpponentWrapper.Opponent(
        id = id,
        imageUrl = imageUrl,
        name = name,
        slug = slug
    )

fun GetOpponentWrapper.toPresentationModel() =
    com.feature.match.model.match.OpponentWrapper(opponent = opponent.toPresentationModel())