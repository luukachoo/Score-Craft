package com.feature.live_matches.mapper

import com.core.domain.model.GetLeague
import com.core.domain.model.GetSeries
import com.feature.live_matches.model.League
import com.feature.live_matches.model.Series

fun GetLeague.toPresentationModel() = League(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    url = url,
    getSeriesList = getSeriesList.map { it.toPresentationModel() }
)

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