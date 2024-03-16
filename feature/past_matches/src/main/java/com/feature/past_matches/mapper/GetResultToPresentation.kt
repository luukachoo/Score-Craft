package com.feature.past_matches.mapper

import com.core.domain.model.matches.live.GetMatchDetails
import com.feature.past_matches.model.Result

fun GetMatchDetails.GetResult.toPresentationModel() = Result(
    score = score,
    teamId = teamId
)