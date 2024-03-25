package com.core.data.mapper.league

import com.core.data.mapper.match.toDomainModel
import com.core.data.model.league.TournamentMatchDto
import com.core.domain.model.league.GetTournamentMatch

fun TournamentMatchDto.toDomainModel() = GetTournamentMatch(
    results = results.map { it.toDomainModel() },
    slug = slug,
    opponents = opponents.map { it.toDomainModel() },
    name = name
)