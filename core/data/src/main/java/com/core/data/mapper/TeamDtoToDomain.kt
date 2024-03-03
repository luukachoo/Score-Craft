package com.core.data.mapper

import com.core.data.model.PlayerDto
import com.core.data.model.TeamDto
import com.core.domain.model.GetPlayer
import com.core.domain.model.GetTeam

fun TeamDto.toDomainModel() = GetTeam(
    acronym = acronym,
    id = id,
    imageUrl = imageUrl,
    location = location,
    modifiedAt = modifiedAt,
    name = name,
    slug = slug,
    players = players.map { it.toDomainModel() }
)

fun PlayerDto.toDomainModel() = GetPlayer(
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