package com.feature.live_match_details.mapper

import com.core.domain.model.match.live.GetStream
import com.feature.live_match_details.model.Stream

fun GetStream.toPresentationModel() = Stream(
    embedUrl = embedUrl,
    main = main,
    official = official,
    rawUrl = rawUrl
)