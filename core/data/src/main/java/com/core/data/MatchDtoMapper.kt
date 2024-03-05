package com.core.data

import com.core.data.mapper.toDomainModel
import com.core.data.model.MatchDto
import com.core.domain.model.GetMatch

fun MatchDto.toDomainModel() = GetMatch(
    beginAt = beginAt,
    detailedStats = detailedStats,
    draw = draw,
    endAt = endAt,
    forfeit = forfeit,
    gameAdvantage = gameAdvantage,
    games = games?.map { it?.toDomainModel() },
    id = id,
    matchType = matchType,
    modifiedAt = modifiedAt,
    name = name,
    numberOfGames = numberOfGames,
    opponents = opponents?.map { it?.toDomainModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    results = results?.map { it?.toDomainModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList?.map { it?.toDomainModel() },
    videogame = videogame?.toDomainModel(),
    winner = winner?.toDomainModel(),
    winnerId = winnerId,
    winnerType = winnerType
)

fun MatchDto.StreamDto.toDomainModel() = GetMatch.GetStream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun MatchDto.ResultDto.toDomainModel() = GetMatch.GetResult(score = score, teamId = teamId)

fun MatchDto.GameDto.toDomainModel() = GetMatch.GetGame(
    beginAt = beginAt,
    complete = complete,
    detailedStats = detailedStats,
    endAt = endAt,
    finished = finished,
    forfeit = forfeit,
    id = id,
    length = length,
    matchId = matchId,
    position = position,
    status = status,
    winner = winner?.toDomainModel(),
    winnerType = winnerType
)

fun MatchDto.GameDto.WinnerDto.toDomainModel() = GetMatch.GetGame.GetWinner(id = id, type = type)

fun MatchDto.OpponentDto.toDomainModel() = GetMatch.GetOpponent(opponent = opponent?.toDomainModel(), type = type)