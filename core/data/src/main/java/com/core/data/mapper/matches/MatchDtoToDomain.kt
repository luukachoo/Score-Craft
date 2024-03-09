package com.core.data.mapper.matches

import com.core.data.model.StreamDto
import com.core.data.model.matches.live.MatchDetailsDto
import com.core.data.model.matches.live.MatchWrapperDto
import com.core.domain.model.GetStream
import com.core.domain.model.matches.live.GetMatchDetails
import com.core.domain.model.matches.live.GetMatchWrapper


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