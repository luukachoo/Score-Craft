package com.feature.match.mapper.match.live

import com.core.domain.model.match.live.GetLiveMatchWrapper
import com.feature.match.model.match.MatchWrapper

fun GetLiveMatchWrapper.toPresentationModel() = MatchWrapper(match = match.toPresentationModel())

fun GetLiveMatchWrapper.GetMatch.toPresentationModel() =
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

fun GetLiveMatchWrapper.GetMatch.GetWinner.toPresentationModel() =
    MatchWrapper.Match.Winner(id = id, type = type)