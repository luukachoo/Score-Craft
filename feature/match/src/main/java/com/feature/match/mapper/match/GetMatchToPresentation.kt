package com.feature.match.mapper.match

import com.core.domain.model.match.GetMatch
import com.feature.match.model.match.Match

fun GetMatch.toPresentationModel() = Match(
    results = results.map { it.toPresentationModel() },
    id = id,
    opponents = opponents.map { it.toPresentationModel() },
    beginAt = beginAt,
    name = name,
    winner = winner?.toPresentationModel(),
    videoGame = videoGame.toPresentationModel(),
    winnerId = winnerId
)