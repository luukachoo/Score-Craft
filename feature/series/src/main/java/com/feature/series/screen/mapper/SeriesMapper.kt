package com.feature.series.screen.mapper

import com.core.domain.model.league.GetSeries
import com.feature.series.screen.model.Series

fun GetSeries.toPresenter() = Series(
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