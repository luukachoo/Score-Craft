package com.core.data.mapper

import com.core.data.model.MatchDto
import com.core.domain.model.GetMatch

fun MatchDto.toDomainModel() = GetMatch(
    beginAt = beginAt,
    endAt = endAt,
    gameDtos = gameDtoDtos.map { it.toDomainModel() },
    id = id,
    league = league.toDomainModel(),
    leagueId = leagueId,
    matchType = matchType,
    modifiedAt = modifiedAt,
    name = name,
    numberOfGames = numberOfGames,
    getOpponents = opponentsDtos.map { it.toDomainModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    getResults = resultDtos.map { it.toDomainModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList.map { it.toDomainModel() },
    videogame = videogame.toDomainModel(),
    winner = winner,
    winnerId = winnerId,
    winnerType = winnerType
)

fun MatchDto.GameDto.toDomainModel() = GetMatch.GetGame(
    beginAt = beginAt,
    complete = complete,
    detailedStats = detailedStats,
    endAt = endAt,
    finished = finished,
    id = id,
    matchId = matchId,
    position = position,
    status = status,
    winner = winner.toDomainModel(),
    winnerType = winnerType
)


fun MatchDto.StreamsDto.toDomainModel() = GetMatch.GetStreams(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun MatchDto.ResultDto.toDomainModel() = GetMatch.GetResult(score = score, teamId = teamId)

fun MatchDto.GameDto.WinnerDto.toDomainModel() = GetMatch.GetGame.GetWinner(id = id, type = type)

fun MatchDto.OpponentDto.toDomainModel() =
    GetMatch.GetOpponent(opponent = opponent.toDomainModel(), type = type)
