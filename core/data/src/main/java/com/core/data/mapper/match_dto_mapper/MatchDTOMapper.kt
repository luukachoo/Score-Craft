package com.core.data.mapper.match_dto_mapper

import com.core.data.test_models.MatchDtoListItem
import com.core.domain.test_model.GetMatchListItem

fun MatchDtoListItem.toDomainModel() = GetMatchListItem( match = match.toDomainModel())

fun MatchDtoListItem.MatchDTO.toDomainModel() = GetMatchListItem.GetMatch(
    beginAt = beginAt,
    detailedStats = detailedStats,
    endAt = endAt,
    games = games.map { it.toDomainModel() },
    id = id,
    league = league.toDomainModel(),
    leagueId = leagueId,
    matchType = matchType,
    name = name,
    opponents = opponents.map { it.toDomainModel() },
    originalScheduledAt = originalScheduledAt,
    rescheduled = rescheduled,
    results = results.map { it.toDomainModel() },
    scheduledAt = scheduledAt,
    slug = slug,
    status = status,
    streamsList = streamsList.map { it.toDomainModel() },
    videogame = videogame.toDomainModel(),
    winner = winner,
    winnerId = winnerId,
    winnerType = winnerType
)

fun MatchDtoListItem.MatchDTO.LeagueDTO.toDomainModel() = GetMatchListItem.GetMatch.GetLeague(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    url = url
)

fun MatchDtoListItem.MatchDTO.GameDTO.toDomainModel() = GetMatchListItem.GetMatch.GetGame(
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
    winner = winner.toDomainModel(),
    winnerType = winnerType
)

fun MatchDtoListItem.MatchDTO.GameDTO.WinnerDTO.toDomainModel() =
    GetMatchListItem.GetMatch.GetGame.GetWinner(
        id = id,
        type = type
    )

fun MatchDtoListItem.MatchDTO.ResultDTO.toDomainModel() = GetMatchListItem.GetMatch.GetResult(
    score = score,
    teamId = teamId
)

fun MatchDtoListItem.MatchDTO.OpponentDTOWrapper.toDomainModel() =
    GetMatchListItem.GetMatch.GetOpponentWrapper(
        opponent = opponent.toDomainModel(),
        type = type
    )

fun MatchDtoListItem.MatchDTO.OpponentDTOWrapper.OpponentDTO.toDomainModel() =
    GetMatchListItem.GetMatch.GetOpponentWrapper.GetOpponent(
        acronym = acronym,
        id = id,
        imageUrl = imageUrl,
        location = location,
        modifiedAt = modifiedAt,
        name = name,
        slug = slug
    )

fun MatchDtoListItem.MatchDTO.StreamsDTO.toDomainModel() = GetMatchListItem.GetMatch.GetStreams(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun MatchDtoListItem.MatchDTO.VideogameDTO.toDomainModel() = GetMatchListItem.GetMatch.GetVideogame(
    id = id,
    name = name,
    slug = slug
)