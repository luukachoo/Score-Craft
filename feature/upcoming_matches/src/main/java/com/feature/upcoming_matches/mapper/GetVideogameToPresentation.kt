package com.feature.upcoming_matches.mapper

import com.core.domain.model.matches.upcoming.GetVideoGame
import com.feature.upcoming_matches.model.VideoGame

fun GetVideoGame.toPresenetationModel() = VideoGame(id = id, name = name, slug = slug)