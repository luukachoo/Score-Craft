package com.example.tournament.mapper

import com.core.domain.model.match.GetOpponentWrapper
import com.example.tournament.model.OpponentWrapper

fun GetOpponentWrapper.GetOpponent.toPresentationModel() =
    OpponentWrapper.Opponent(
        id = id,
        imageUrl = imageUrl,
        name = name,
        slug = slug
    )

fun GetOpponentWrapper.toPresentationModel() =
    OpponentWrapper(opponent = opponent.toPresentationModel())