package com.core.data.mapper.match

import com.core.data.model.match.live.StreamDto
import com.core.data.model.match.live.LiveMatchDetailsDto
import com.core.data.model.match.live.LiveMatchWrapperDto
import com.core.domain.model.match.live.GetStream
import com.core.domain.model.match.live.GetLiveMatchDetails
import com.core.domain.model.match.live.GetLiveMatchWrapper


fun LiveMatchWrapperDto.toDomainModel() = GetLiveMatchWrapper(match = match.toDomainModel())

fun LiveMatchWrapperDto.LiveMatchDto.toDomainModel() = GetLiveMatchWrapper.GetMatch(
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

fun LiveMatchWrapperDto.LiveMatchDto.WinnerDto.toDomainModel() = GetLiveMatchWrapper.GetMatch.GetWinner(
    id = id,
    type = type
)

fun LiveMatchDetailsDto.toDomainModel() = GetLiveMatchDetails(
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

fun LiveMatchDetailsDto.ResultDto.toDomainModel() = GetLiveMatchDetails.GetResult(
    score = score,
    teamId = teamId
)