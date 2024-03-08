package com.core.data.mapper

import com.core.data.model.GamesDto
import com.core.domain.model.GetGames

fun GamesDto.toDomainModel() = GetGames(
    id = id,
    beginAt = beginAt,
    complete = complete,
    detailedStats = detailedStats,
    endAt = endAt,
    finished = finished,
    forfeit = forfeit,
    length = length,
    matchId = matchId,
    position = position,
    status = status,
    winner = winner.toDomainModel(),
    winnerType = winnerType,
)