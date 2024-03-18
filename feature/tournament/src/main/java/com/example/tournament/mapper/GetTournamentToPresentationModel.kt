package com.example.tournament.mapper

import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTournament
import com.example.tournament.model.Team
import com.example.tournament.model.Tournament

fun GetTeam.toPresentationModel() = Team(
    acronym = acronym,
    id = id,
    imageUrl = imageUrl,
    location = location,
    name = name
)

fun GetTournament.toPresentationModel() = Tournament(
    beginAt = beginAt,
    id = id,
    name = name,
    prizePool = prizePool,
    slug = slug,
    matches = matches,
    teams = teams.map { it.toPresentationModel() }
)