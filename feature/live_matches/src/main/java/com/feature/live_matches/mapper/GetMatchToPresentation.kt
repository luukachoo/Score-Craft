package com.feature.live_matches.mapper

import com.core.domain.model.GetMatch
import com.feature.live_matches.model.Match


fun GetMatch.toPresentationModel() = Match(
    beginAt = beginAt,
    detailedStats = detailedStats,
    draw = draw,
    endAt = endAt,
    forfeit = forfeit,
    gameAdvantage = gameAdvantage,
    games = games?.map { it?.toPresentationModel() },
    id = id,
    matchType = matchType,
    modifiedAt = modifiedAt,
    name = name,
    numberOfGames = numberOfGames,
    opponents = opponents?.map { it?.toPresentationModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    results = results?.map { it?.toPresentationModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList?.map { it?.toPresentationModel() },
    videogame = videogame,
    winner = winner,
    winnerId = winnerId,
    winnerType = winnerType
)

fun GetMatch.GetOpponent.toPresentationModel() = Match.Opponent(opponent = opponent, type = type)

fun GetMatch.GetStream.toPresentationModel() = Match.Stream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun GetMatch.GetGame.toPresentationModel() = Match.Game(
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
    winner = winner?.toPresentationModel(),
    winnerType = winnerType
)

fun GetMatch.GetResult.toPresentationModel() =
    Match.Result(score = score, teamId = teamId)

fun GetMatch.GetGame.GetWinner.toPresentationModel() =
    Match.Game.Winner(id = id, type = type)
