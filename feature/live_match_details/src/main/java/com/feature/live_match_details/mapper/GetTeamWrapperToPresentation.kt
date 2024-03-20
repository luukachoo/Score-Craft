package com.feature.live_match_details.mapper

import com.core.domain.model.match.GetTeamWrapper
import com.feature.live_match_details.model.TeamWrapper

fun GetTeamWrapper.toPresentationModel() =
    TeamWrapper(opponents = opponents.map { it.toPresentationModel() })

fun GetTeamWrapper.GetTeam.toPresentationModel() =
    TeamWrapper.Team(id = id, name = name, slug = slug,
        players = players.map { it.toPresentationModel() }
    )

fun GetTeamWrapper.GetTeam.GetPlayer.toPresentationModel() = TeamWrapper.Team.Player(
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