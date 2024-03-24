package com.example.profile.mapper.league

import com.core.domain.model.league.GetLeague
import com.example.profile.mapper.series.toDomain
import com.example.profile.mapper.series.toPresentationModel
import com.example.profile.model.league.League

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