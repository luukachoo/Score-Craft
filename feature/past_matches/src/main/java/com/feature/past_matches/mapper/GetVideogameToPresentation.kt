package com.feature.past_matches.mapper

import com.core.domain.model.matches.upcoming.GetVideoGame
import com.feature.past_matches.model.VideoGame

fun GetVideoGame.toPresenetationModel() = VideoGame(id = id, name = name, slug = slug)