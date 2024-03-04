package com.feature.live_matches.mapper.test_mapper

import com.core.domain.test_model.GetMatchListItem
import com.feature.live_matches.test_model.MatchListItem

fun GetMatchListItem.GetMatch.toPresentationModel() = MatchListItem.Match(
    beginAt = beginAt ?: "",
    detailedStats = detailedStats,
    endAt = endAt,
    games = games.map { it.toPresentationModel() },
    id = id,
    league = league.toPresentationModel(),
    leagueId = leagueId,
    matchType = matchType,
    name = name,
    opponents = opponents.map { it.toPresentationModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    results = results.map { it.toPresentationModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList.map { it.toPresentationModel() },
    videogame = videogame.toPresentationModel(),
    winner = winner,
    winnerId = winnerId,
    winnerType = winnerType
)

fun GetMatchListItem.GetMatch.GetLeague.toPresentationModel() = MatchListItem.Match.League(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    url = url
)

fun GetMatchListItem.GetMatch.GetGame.toPresentationModel() = MatchListItem.Match.Game(
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
    winner = winner.toPresentationModel(),
    winnerType = winnerType
)

fun GetMatchListItem.GetMatch.GetGame.GetWinner.toPresentationModel() =
    MatchListItem.Match.Game.Winner(
        id = id,
        type = type
    )

fun GetMatchListItem.GetMatch.GetResult.toPresentationModel() = MatchListItem.Match.Result(
    score = score,
    teamId = teamId
)

fun GetMatchListItem.GetMatch.GetOpponentWrapper.toPresentationModel() =
    MatchListItem.Match.OpponentWrapper(
        opponent = opponent.toPresentationModel(),
        type = type
    )

fun GetMatchListItem.GetMatch.GetOpponentWrapper.GetOpponent.toPresentationModel() =
    MatchListItem.Match.OpponentWrapper.Opponent(
        acronym = acronym,
        id = id,
        imageUrl = imageUrl,
        location = location,
        modifiedAt = modifiedAt,
        name = name,
        slug = slug
    )

fun GetMatchListItem.GetMatch.GetStreams.toPresentationModel() = MatchListItem.Match.Streams(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun GetMatchListItem.GetMatch.GetVideogame.toPresentationModel() = MatchListItem.Match.Videogame(
    id = id,
    name = name,
    slug = slug
)