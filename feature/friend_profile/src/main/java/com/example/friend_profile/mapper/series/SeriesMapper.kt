package com.example.friend_profile.mapper.series

import com.core.domain.model.league.GetSeries
import com.example.friend_profile.model.series.Series

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