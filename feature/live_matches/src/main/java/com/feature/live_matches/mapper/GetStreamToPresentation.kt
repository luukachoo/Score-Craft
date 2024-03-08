package com.feature.live_matches.mapper

import com.core.domain.model.GetStream
import com.feature.live_matches.model.Stream

fun GetStream.toPresentationModel() = Stream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)