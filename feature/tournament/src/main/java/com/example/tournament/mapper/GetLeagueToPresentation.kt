package com.example.tournament.mapper

import com.core.domain.model.league.GetLeague
import com.example.tournament.model.League

fun GetLeague.toPresentationModel() = League(
    id = id,
    imageUrl = imageUrl,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    url = url
)