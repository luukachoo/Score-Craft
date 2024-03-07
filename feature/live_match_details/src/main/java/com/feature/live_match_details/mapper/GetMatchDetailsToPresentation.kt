package com.feature.live_match_details.mapper

import com.core.domain.model.GetMatchDetails
import com.core.domain.model.GetStream
import com.feature.live_match_details.model.MatchDetails
import com.feature.live_match_details.model.Stream

fun GetMatchDetails.toPresentationModel() = MatchDetails(
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


fun GetMatchDetails.GetResult.toPresentationModel() =
    MatchDetails.Result(
        score = score, teamId = teamId
    )

fun GetStream.toPresentationModel() = Stream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)