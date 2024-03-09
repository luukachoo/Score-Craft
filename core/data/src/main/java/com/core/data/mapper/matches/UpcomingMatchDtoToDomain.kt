package com.core.data.mapper.matches

import com.core.data.model.matches.upcoming.UpcomingMatchDto
import com.core.domain.model.matches.upcoming.GetUpcomingMatch

fun UpcomingMatchDto.toDomainModel() = GetUpcomingMatch(
    results = results.map { it.toDomainModel() },
    id = id,
    videogame = videogame.toDomainModel(),
    opponents = opponents.map { it.toDomainModel() },
    beginAt = beginAt,
    name = name
)