package com.feature.live_matches.mapper

import com.core.domain.model.GetPlayer
import com.core.domain.model.GetTeam
import com.feature.live_matches.model.Player
import com.feature.live_matches.model.Team

fun GetTeam.toPresentationModel() = Team(
    acronym = acronym,
    id = id,
    imageUrl = imageUrl,
    location = location,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    players = players.map { it.toPresentationModel() }
)

fun GetPlayer.toPresentationModel() = Player(
    active = active,
    age = age,
    birthday = birthday,
    firstName = firstName,
    id = id,
    imageUrl = imageUrl,
    lastName = lastName,
    name = name,
    nationality = nationality,
    role = role,
    slug = slug
)