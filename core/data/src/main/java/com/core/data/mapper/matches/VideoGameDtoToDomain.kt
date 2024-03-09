package com.core.data.mapper.matches

import com.core.data.model.matches.upcoming.VideoGameDto
import com.core.domain.model.matches.upcoming.GetVideoGame

fun VideoGameDto.toDomainModel()= GetVideoGame(id = id, name = name, slug = slug)