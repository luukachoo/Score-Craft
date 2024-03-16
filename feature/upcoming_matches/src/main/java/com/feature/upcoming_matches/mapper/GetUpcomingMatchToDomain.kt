package com.feature.upcoming_matches.mapper

import com.core.domain.model.matches.upcoming.GetUpcomingMatch
import com.feature.upcoming_matches.model.UpcomingMatch

fun GetUpcomingMatch.toPresentationModel() = UpcomingMatch(
    results = results.map { it.toPresentationModel() },
    id = id,
    videogame = videogame.toPresenetationModel(),
    opponents = opponents,
    beginAt = beginAt,
    name = name
)