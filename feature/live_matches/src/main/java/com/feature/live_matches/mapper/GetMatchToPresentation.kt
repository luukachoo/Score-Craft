package com.feature.live_matches.mapper

import com.core.domain.model.matches.live.GetMatchWrapper
import com.feature.live_matches.model.MatchWrapper

fun GetMatchWrapper.toPresentationModel() = MatchWrapper(match = match.toPresentationModel())

fun GetMatchWrapper.GetMatch.toPresentationModel() =
    MatchWrapper.Match(
        slug = slug,
        status = status,
        originalScheduledAt = originalScheduledAt,
        id = id,
        name = name,
        detailedStats = detailedStats,
        scheduledAt = scheduledAt,
        beginAt = beginAt,
        streamsList = streamsList.map { it.toPresentationModel() }
    )

fun GetMatchWrapper.GetMatch.GetWinner.toPresentationModel() =
    MatchWrapper.Match.Winner(id = id, type = type)

