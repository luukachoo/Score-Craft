package com.feature.live_matches.mapper

import com.core.domain.model.GetVideoGame
import com.feature.live_matches.model.VideoGame

fun GetVideoGame.toPresentataionModel() = VideoGame(
    id = id,
    name = name,
    slug = slug
)