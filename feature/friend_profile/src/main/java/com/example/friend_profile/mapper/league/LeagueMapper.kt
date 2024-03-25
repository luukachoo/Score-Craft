package com.example.friend_profile.mapper.league

import com.core.domain.model.league.GetLeague
import com.example.friend_profile.mapper.series.toPresentationModel
import com.example.friend_profile.model.league.League

fun GetLeague.toPresentationModel() = League(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    getSeriesList = getSeriesList.map { it.toPresentationModel() },
    slug = slug,
    url = url
)