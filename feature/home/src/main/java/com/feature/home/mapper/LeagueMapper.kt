package com.feature.home.mapper

import com.core.domain.model.league.GetLeague
import com.feature.home.model.League

fun GetLeague.toPresentationModel() = League(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    getSeriesList = getSeriesList.map { it.toPresentationModel() },
    slug = slug,
    url = url
)

fun League.toDomain() = GetLeague(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    getSeriesList = getSeriesList.map { it.toDomain() },
    slug = slug,
    url = url
)