package com.feature.past_matches.mapper

import com.core.domain.model.matches.past.GetPastMatch
import com.feature.past_matches.model.PastMatch

fun GetPastMatch.toPresentationModel() = PastMatch(
    results = results.map { it.toPresentationModel() },
    id = id,
    opponents = opponents.map { it.toPresentationModel() },
    beginAt = beginAt,
    name = name,
    winner = winner.toPresentationModel(),
    videoGame = videoGame.toPresenetationModel(),
    winnerId = winnerId
)