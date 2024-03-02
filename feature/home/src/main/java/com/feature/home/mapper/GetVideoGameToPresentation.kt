package com.feature.home.mapper

import com.core.domain.model.GetVideoGame
import com.feature.home.model.VideoGame

fun GetVideoGame.toPresentataionModel() = VideoGame(
    currentVersion = currentVersion,
    id = id,
    name = name,
    slug = slug
)