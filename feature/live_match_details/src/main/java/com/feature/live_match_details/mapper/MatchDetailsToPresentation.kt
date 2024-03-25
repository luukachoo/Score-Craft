package com.feature.live_match_details.mapper

import com.core.domain.model.match.live.GetLiveMatchDetails
import com.feature.live_match_details.model.MatchDetails

fun GetLiveMatchDetails.toPresentationModel() = MatchDetails(
    slug = slug,
    status = status,
    originalScheduledAt = originalScheduledAt,
    id = id,
    name = name,
    detailedStats = detailedStats,
    scheduledAt = scheduledAt,
    beginAt = beginAt,
    opponents = opponents.map { it.toPresentationModel() },
    streamsList = streamsList.map { it.toPresentationModel() },
    results = results.map { it.toPresentationModel() }
)

fun GetLiveMatchDetails.GetResult.toPresentationModel() = MatchDetails.Result(
    score = score,
    teamId = teamId
)