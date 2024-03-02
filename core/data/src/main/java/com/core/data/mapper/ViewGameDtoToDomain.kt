package com.core.data.mapper

import com.core.data.model.VideoGameDto
import com.core.domain.model.GetVideoGame

fun VideoGameDto.toDomainModel() = GetVideoGame(
    currentVersion = currentVersion,
    id = id,
    name = name,
    slug = slug
)