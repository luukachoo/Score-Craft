package com.core.data.mapper.league

import com.core.data.model.league.SeriesDto
import com.core.domain.model.league.GetSeries

fun SeriesDto.toDomainModel() = GetSeries(
    beginAt = beginAt,
    endAt = endAt ?: "",
    fullName = fullName,
    id = id,
    leagueId = leagueId,
    modifiedAt = modifiedAt,
    name = name ?: "",
    season = season ?: "",
    slug = slug,
    winnerId = winnerId,
    winnerType = winnerType,
    year = year
)