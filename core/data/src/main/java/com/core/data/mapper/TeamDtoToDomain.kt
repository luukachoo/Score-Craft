package com.core.data.mapper

import com.core.data.model.PlayerDto
import com.core.data.model.TeamDto
import com.core.domain.model.GetPlayer
import com.core.domain.model.GetTeam

fun TeamDto.toDomainModel() = GetTeam(
    score = score, teamId = teamId
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