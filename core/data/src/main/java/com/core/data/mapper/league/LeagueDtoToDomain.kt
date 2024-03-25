package com.core.data.mapper.league

import com.core.data.model.league.LeagueDto
import com.core.domain.model.league.GetLeague

fun LeagueDto.toDomainModel() = GetLeague(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    getSeriesList = seriesDtoList?.map { it.toDomainModel() } ?: emptyList(),
    slug = slug,
    url = url,
)