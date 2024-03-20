package com.core.data.mapper.league

import com.core.data.mapper.match.toDomainModel
import com.core.data.model.league.TeamDto
import com.core.data.model.league.TournamentDto
import com.core.data.model.league.TournamentMatchDto
import com.core.data.model.match.MatchDto
import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTournament
import com.core.domain.model.league.GetTournamentMatch
import com.core.domain.model.match.GetMatch

fun TournamentDto.toDomainModel() = GetTournament(
    beginAt = beginAt ?: "We do not know yet",
    id = id,
    name = name,
    prizePool = prizePool ?: "No Prize Pool",
    slug = slug,
    matches = matches.map { it.toDomainModel() },
    teams = teams.map { it.toDomainModel() },
    league = league.toDomainModel()
)

fun MatchDto.toDomainModel() = GetMatch(
    results = results.map { it.toDomainModel() },
    id = id,
    opponents = opponents.map { it.toDomainModel() },
    beginAt = beginAt,
    name = name,
    winner = winner?.toDomainModel(),
    videoGame = videoGame.toDomainModel(),
    winnerId = winnerId
)

fun TeamDto.toDomainModel() = GetTeam(
    acronym = acronym ?: "",
    id = id,
    imageUrl = imageUrl ?: "",
    location = location ?: "",
    name = name
)

fun TournamentMatchDto.toDomainModel() = GetTournamentMatch(
    id = id,
    beginAt = beginAt,
    name = name,
    winnerId = winnerId
)