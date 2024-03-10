package com.core.data.mapper.matches.past

import com.core.data.mapper.matches.live.toDomainModel
import com.core.data.mapper.matches.toDomainModel
import com.core.data.model.matches.past.PastMatchDto
import com.core.domain.model.matches.past.GetPastMatch

fun PastMatchDto.toDomainModel() = GetPastMatch(
    results = results.map { it.toDomainModel() },
    id = id,
    opponents = opponents.map { it.toDomainModel() },
    beginAt = beginAt,
    name = name,
    winner = winner.toDomainModel(),
    videoGame = videoGame.toDomainModel(),
    winnerId = winnerId
)