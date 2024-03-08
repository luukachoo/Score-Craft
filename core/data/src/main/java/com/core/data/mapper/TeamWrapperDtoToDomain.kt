package com.core.data.mapper

import com.core.data.model.live_matches.TeamWrapperDto
import com.core.domain.model.live_matches.GetTeamWrapper

fun TeamWrapperDto.toDomainModel() =
    GetTeamWrapper(opponents = opponents.map { it.toDomainModel() })

fun TeamWrapperDto.TeamDto.toDomainModel() = GetTeamWrapper.GetTeam(
    id = id,
    name = name,
    slug = slug,
    players = players.map { it.toDomainModel() }
)

fun TeamWrapperDto.TeamDto.PlayerDto.toDomainModel() = GetTeamWrapper.GetTeam.GetPlayer(
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