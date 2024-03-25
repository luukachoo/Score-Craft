package com.core.data.mapper.match

import com.core.data.model.match.MatchDto
import com.core.domain.model.match.GetMatch

fun MatchDto.toDomainModel() = GetMatch(
    results = results.map { it.toDomainModel() },
    id = id,
    opponents = opponents.map { it.toDomainModel() },
    beginAt = beginAt,
    name = name,
    winner = winner?.toDomainModel(),
    videoGame = videoGame.toDomainModel(),
    winnerId = winnerId
)