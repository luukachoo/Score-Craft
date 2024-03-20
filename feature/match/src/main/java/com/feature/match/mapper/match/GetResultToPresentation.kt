package com.feature.match.mapper.match

import com.core.domain.model.match.live.GetLiveMatchDetails

fun GetLiveMatchDetails.GetResult.toPresentationModel() = com.feature.match.model.match.Result(
    score = score,
    teamId = teamId
)