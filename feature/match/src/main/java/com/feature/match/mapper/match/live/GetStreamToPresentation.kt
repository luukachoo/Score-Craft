package com.feature.match.mapper.match.live

import com.core.domain.model.match.live.GetStream
import com.feature.match.model.match.Stream

fun GetStream.toPresentationModel() = Stream(
    embedUrl = embedUrl,
    language = language,
    main = main,
    official = official,
    rawUrl = rawUrl
)