package com.core.data.mapper.match

import com.core.data.model.match.VideoGameDto
import com.core.domain.model.match.GetVideoGame

fun VideoGameDto.toDomainModel() = GetVideoGame(id = id, name = name, slug = slug)