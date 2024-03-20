package com.feature.home.mapper

import com.core.domain.model.league.GetSeries
import com.feature.home.model.Series

fun GetSeries.toPresentationModel() = Series(
    beginAt = beginAt,
    endAt = endAt,
    fullName = fullName,
    id = id,
    leagueId = leagueId,
    modifiedAt = modifiedAt,
    name = name,
    season = season,
    slug = slug,
    winnerId = winnerId,
    winnerType = winnerType,
    year = year
)