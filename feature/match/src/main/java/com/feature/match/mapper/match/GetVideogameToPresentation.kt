package com.feature.match.mapper.match

import com.core.domain.model.match.GetVideoGame
import com.feature.match.model.match.VideoGame

fun GetVideoGame.toPresentationModel() =
    VideoGame(id = id, name = name, slug = slug)