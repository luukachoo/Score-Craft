package com.core.data.mapper

import com.core.data.model.SeriesDto
import com.core.domain.model.GetSeries

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


//fun TournamentDtoList.toDomainModel() = GetTournamentsList(getTournaments = tournamentDtos.map { it.toDomainModel() })
//fun TournamentDtoList.TournamentDto.toDomainModel() = GetTournamentsList.GetTournament(
//    beginAt = beginAt,
//    detailedStats = detailedStats,
//    endAt = endAt,
//    hasBracket = hasBracket,
//    id = id,
//    leagueId = leagueId,
//    liveSupported = liveSupported,
//    modifiedAt = modifiedAt,
//    name = name,
//    prizePool = prizePool,
//    serieId = serieId,
//    slug = slug,
//    tier = tier,
//    winnerId = winnerId,
//    winnerType = winnerType
//)