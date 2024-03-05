package com.core.data.mapper

import com.core.data.model.MatchWrapperDto
import com.core.data.model.OpponentWithTypeDto
import com.core.data.model.StreamDto
import com.core.domain.model.GetMatchWrapper
import com.core.domain.model.GetOpponentWithType
import com.core.domain.model.GetStream


fun MatchWrapperDto.toDomainModel() = GetMatchWrapper(match = match.toDomainModel())

fun MatchWrapperDto.MatchDto.toDomainModel() = GetMatchWrapper.GetMatch(
    slug = slug,
    status = status,
    originalScheduledAt = originalScheduledAt,
    games = games.map { it.toDomainModel() },
    serieId = serieId,
    videoGameVersion = videoGameVersion,
    numberOfGames = numberOfGames,
    id = id,
    name = name,
    detailedStats = detailedStats,
    scheduledAt = scheduledAt,
    beginAt = beginAt,
    videogame = videogame.toDomainModel(),
    results = results.map { it.toDomainModel() },
    videoGameTitle = videoGameTitle,
    forfeit = forfeit,
    opponents = opponents.map { it.toDomainModel() },
    streamsList = streamsList.map { it.toDomainModel() }
)

fun StreamDto.toDomainModel() = GetStream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)

fun OpponentWithTypeDto.toDomainModel() = GetOpponentWithType(opponent = opponent.toDomainModel(), type = type)

fun OpponentWithTypeDto.OpponentDto.toDomainModel() = GetOpponentWithType.Opponent(
    acronym = acronym,
    id = id,
    imageUrl = imageUrl,
    location = location,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug
)

fun MatchWrapperDto.MatchDto.WinnerDto.toDomainModel() = GetMatchWrapper.GetMatch.GetWinner(
    id = id,
    type = type
)

fun MatchWrapperDto.MatchDto.VideoGameDto.toDomainModel() = GetMatchWrapper.GetMatch.GetVideoGame(
    id = id,
    name = name,
    slug = slug
)