package com.core.data.mapper

import com.core.data.model.live_matches.MatchDetailsDto
import com.core.data.model.live_matches.MatchWrapperDto
import com.core.data.model.StreamDto
import com.core.domain.model.live_matches.GetMatchDetails
import com.core.domain.model.live_matches.GetMatchWrapper
import com.core.domain.model.GetStream


fun MatchWrapperDto.toDomainModel() = GetMatchWrapper(match = match.toDomainModel())

fun MatchWrapperDto.MatchDto.toDomainModel() = GetMatchWrapper.GetMatch(
    slug = slug,
    status = status,
    originalScheduledAt = originalScheduledAt,
    id = id,
    name = name,
    detailedStats = detailedStats,
    scheduledAt = scheduledAt,
    beginAt = beginAt,
    videogame = videogame.toDomainModel(),
    videoGameTitle = videoGameTitle,
    forfeit = forfeit,
    streamsList = streamsList.map { it.toDomainModel() }
)

fun StreamDto.toDomainModel() = GetStream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
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

fun MatchDetailsDto.toDomainModel() = GetMatchDetails(
    slug = slug,
    status = status,
    originalScheduledAt = originalScheduledAt,
    id = id,
    name = name,
    detailedStats = detailedStats,
    scheduledAt = scheduledAt,
    beginAt = beginAt,
    opponents = opponents.map { it.toDomainModel() },
    streamsList = streamsList.map { it.toDomainModel() },
    results = results.map { it.toDomainModel() }
)

fun MatchDetailsDto.ResultDto.toDomainModel() = GetMatchDetails.GetResult(
    score = score,
    teamId = teamId
)