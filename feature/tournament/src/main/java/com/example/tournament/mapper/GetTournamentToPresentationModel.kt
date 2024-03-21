package com.example.tournament.mapper

import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTeamStanding
import com.core.domain.model.league.GetTournament
import com.core.domain.model.league.GetTournamentMatch
import com.example.tournament.model.Team
import com.example.tournament.model.TeamStanding
import com.example.tournament.model.Tournament
import com.example.tournament.model.TournamentMatch

fun GetTeam.toPresentationModel() = Team(
    acronym = acronym,
    id = id,
    imageUrl = imageUrl,
    location = location,
    name = name
)

fun GetTeamStanding.toPresentationModel() = TeamStanding(rank = rank, team = team)

fun GetTournament.toPresentationModel() = Tournament(
    beginAt = beginAt,
    id = id,
    name = name,
    prizePool = prizePool,
    slug = slug,
    matches = matches.map { it.toPresentationModel() },
    teams = teams.map { it.toPresentationModel() },
    league = league.toPresentationModel()
)

fun GetTournamentMatch.toPresentationModel() = TournamentMatch(
    id = id,
    beginAt = beginAt,
    name = name,
    winnerId = winnerId
)