package com.feature.live_matches.mapper

import com.core.domain.model.GetMatch
import com.feature.live_matches.model.Match


fun GetMatch.toPresentationModel() = Match(
    beginAt = beginAt,
    endAt = endAt,
    gameDtos = gameDtos.map { it.toPresentationModel() },
    id = id,
    leagueId = leagueId,
    matchType = matchType,
    modifiedAt = modifiedAt,
    name = name,
    numberOfGames = numberOfGames,
    opponents = getOpponents.map { it.toPresentationModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    result = getResults.map { it.toPresentationModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList.map { it.toPresentationModel() },
    videogame = videogame.toPresentataionModel(),
    winner = winner,
    winnerId = winnerId,
    winnerType = winnerType
)

fun GetMatch.GetGame.toPresentationModel() = Match.Game(
    beginAt = beginAt,
    complete = complete,
    detailedStats = detailedStats,
    endAt = endAt,
    finished = finished,
    id = id,
    matchId = matchId,
    position = position,
    status = status,
    winner = winner.toPresentationModel(),
    winnerType = winnerType
)


fun GetMatch.GetStreams.toPresentationModel() = Match.Streams(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun GetMatch.GetResult.toPresentationModel() =
    Match.Result(score = score, teamId = teamId)

fun GetMatch.GetGame.GetWinner.toPresentationModel() =
    Match.Game.Winner(id = id, type = type)

fun GetMatch.GetOpponent.toPresentationModel() =
    Match.Opponent(
        opponent = opponent.toPresentationModel(),
        type = type
    )
