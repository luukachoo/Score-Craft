package com.example.tournament.mapper

import com.core.domain.model.league.GetResult
import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTeamStanding
import com.core.domain.model.league.GetTournament
import com.core.domain.model.league.GetTournamentMatch
import com.core.domain.model.match.GetWinner
import com.example.tournament.model.Result
import com.example.tournament.model.Team
import com.example.tournament.model.TeamStanding
import com.example.tournament.model.Tournament
import com.example.tournament.model.TournamentMatch
import com.example.tournament.model.Winner

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
    winner = winner.toPresentationModel(),
    opponents = opponents.map { it.toPresentationModel() },
    winnerId = winnerId,
    name = name
)

fun GetResult.toPresentationModel() = Result(
    score = score,
    teamId = teamId
)

fun GetWinner.toPresentationModel() = Winner(acronym = acronym, winnerId = winnerId, name = name)