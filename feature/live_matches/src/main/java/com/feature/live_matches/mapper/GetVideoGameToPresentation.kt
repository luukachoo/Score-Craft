package com.feature.home.mapper

import com.core.domain.model.GetVideoGame
import com.feature.live_matches.model.VideoGame

fun GetVideoGame.toPresentataionModel() = com.feature.live_matches.model.VideoGame(
    currentVersion = currentVersion,
    id = id,
    name = name,
    slug = slug
)