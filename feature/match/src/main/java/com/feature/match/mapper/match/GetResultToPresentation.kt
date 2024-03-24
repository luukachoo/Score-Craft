package com.feature.match.mapper.match

import com.core.domain.model.match.live.GetLiveMatchDetails
import com.feature.match.model.match.Result

fun GetLiveMatchDetails.GetResult.toPresentationModel() = Result(
    score = score,
    teamId = teamId
)