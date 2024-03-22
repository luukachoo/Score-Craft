package com.example.tournament.mapper

import com.core.domain.model.league.GetResult
import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTeamStanding
import com.core.domain.model.league.GetTournament
import com.core.domain.model.league.GetTournamentMatch
import com.example.tournament.model.Result
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

fun GetTeamStanding.toPresentationModel() = TeamStanding(rank = rank, team = team.toPresentationModel())

fun GetTournament.toPresentationModel() = Tournament(
    beginAt = beginAt,
    id = id,
    name = name,
    prizePool = prizePool,
    slug = slug,
    teams = teams.map { it.toPresentationModel() },
    league = league.toPresentationModel()
)

fun GetTournamentMatch.toPresentationModel() = TournamentMatch(
    results = results.map { it.toPresentationModel() },
    slug = slug,
    opponents = opponents.map { it.toPresentationModel() },
    name = name
)

fun GetResult.toPresentationModel() = Result(
    score = score,
    teamId = teamId
)