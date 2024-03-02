package com.core.data.mapper

import com.core.data.model.LeagueDto
import com.core.domain.model.GetLeague

fun LeagueDto.toDomainModel() = GetLeague(
    id = id ?: 0,
    imageUrl = imageUrl ?: "",
    modifiedAt = modifiedAt ?: "",
    name = name ?: "",
    getSeriesList = seriesDtoList.map { it.toDomainModel() },
    slug = slug ?: "",
    url = url,
    getVideoGame = videoGameDto.toDomainModel()
)